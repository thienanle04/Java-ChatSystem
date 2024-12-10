package server.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import server.model.Model_Client;
import server.model.Model_Group_Chat;
import server.model.Model_Login;
import server.model.Model_Message;
import server.model.Model_Register;
import server.model.Model_User_Profile;
import server.model.Model_Chat_Message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collections;

public class Service {
    
    private static Service instance;
    private SocketIOServer server;
    private ServiceMessage serviceMessage;
    private ServiceUser serviceUser;
    private final List<Model_Client> listClient;
    private final int PORT_NUMBER = 9999;
    
    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
    
    private Service() {
        serviceUser = new ServiceUser();
        serviceMessage = new ServiceMessage();
        listClient = Collections.synchronizedList(new ArrayList<>());
    }
    
    public void startServer() {
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient sioc) {
                System.out.println("One client connected\n");
            }
        });
        server.addEventListener("register", Model_Register.class, new DataListener<Model_Register>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Register t, AckRequest ar) throws Exception {
                Model_Message message = serviceUser.register(t);
                ar.sendAckData(message.isAction(), message.getMessage(), message.getData());
                if (message.isAction()) {
                    System.out.println("User has Register :" + t.getUserName() + " Pass :" + t.getPassword() + "\n");
                    addClient(sioc, (Model_User_Profile) message.getData());
                }
            }
        });
        server.addEventListener("login", Model_Login.class, new DataListener<Model_Login>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Login t, AckRequest ar) throws Exception {
                Model_User_Profile login = serviceUser.login(t);
                if (login != null) {
                    String role = serviceUser.getRole(login.getUserID());
                    ar.sendAckData(true, login, role);
                    addClient(sioc, login);
                    userConnect(login.getUserID());
                } else {
                    ar.sendAckData(false);
                }
            }
        });
        server.addEventListener("update_profile", Object.class, new DataListener<Object>() {
            @Override
            public void onData(SocketIOClient sioc, Object t, AckRequest ar) throws Exception {
                try {
                    Model_User_Profile newUserInfo = new Model_User_Profile(t);
                    boolean ok = serviceUser.updateProfile(newUserInfo);
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("list_chat", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer userID, AckRequest ar) throws Exception {
                try {
                    List<Model_Group_Chat> list = serviceMessage.getChatListId(userID);
                    sioc.sendEvent("list_chat", list.toArray());
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });

        server.addEventListener("get_all_chats", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer userID, AckRequest ar) throws Exception {
                try {
                    HashMap<Integer, LinkedList<Model_Chat_Message>> data = serviceMessage.getAllChat(userID);
                    sioc.sendEvent("get_all_chats", data);
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });

        server.addEventListener("send_to_user", Model_Chat_Message.class, new DataListener<Model_Chat_Message>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Chat_Message t, AckRequest ar) throws Exception {
                try {
                    // Save the message to the database
                    Model_Chat_Message receive_Message = serviceMessage.saveMessage(t);

                    if (receive_Message != null) {
                        ar.sendAckData(true, receive_Message.getMessageID());

                        // Get the list of users in the group chat
                        List<Integer> list = serviceMessage.getUserListInGroupChat(t.getGroupID());
                        
                        // Send the message to the user
                        for (Model_Client c : listClient) {
                            if (list.contains(c.getUser().getUserID())) {
                                if (c.getUser().getUserID() == t.getSenderID()) {
                                    continue;
                                }
                                c.getClient().sendEvent("receive_ms", receive_Message);
                                break;
                            }
                        }
                        
                    } else {
                        ar.sendAckData(false);
                    }
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });
        
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient sioc) {
                int userID = removeClient(sioc);
                if (userID != 0) {
                    //  removed
                    userDisconnect(userID);
                }
            }
        });
        server.start();
        System.out.println("Server has Start on port : " + PORT_NUMBER + "\n");
    }
    
    private void userConnect(int userID) {
        try {
            List<Integer> chatIds = serviceMessage.getChatPrivateOfUser(userID);
            for (Integer chatId : chatIds) {
                server.getBroadcastOperations().sendEvent("user_status", chatId, true);
            }
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    private void userDisconnect(int userID) {
        try {
            List<Integer> chatIds = serviceMessage.getChatPrivateOfUser(userID);
            for (Integer chatId : chatIds) {
                server.getBroadcastOperations().sendEvent("user_status", chatId, false);
                serviceUser.userDisconnect(userID);
            }
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    private void addClient(SocketIOClient client, Model_User_Profile user) {
        listClient.add(new Model_Client(client, user));
    }
    
    public int removeClient(SocketIOClient client) {
        for (Model_Client d : listClient) {
            if (d.getClient() == client) {
                listClient.remove(d);
                return d.getUser().getUserID();
            }
        }
        return 0;
    }
    
    public List<Model_Client> getListClient() {
        return listClient;
    }
}
