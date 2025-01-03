package server.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.protocol.JacksonJsonSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import server.model.Model_Client;
import server.model.Model_Delete_Message;
import server.model.Model_Group_Chat;
import server.model.Model_Login;
import server.model.Model_Message;
import server.model.Model_Register;
import server.model.Model_Reset_Password;
import server.model.Model_Spam_Report;
import server.model.Model_User_Profile;
import server.model.Model_Chat_Message;
import server.model.Model_Friend_Request;
import server.model.Model_Friend;
import server.model.Model_New_Group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collections;

public class Service {
    ObjectMapper objectMapper;
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

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  
    }
    
    public void startServer() {
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        config.setJsonSupport(new JacksonJsonSupport(new JavaTimeModule()));
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

        server.addEventListener("create_new_group", Model_New_Group.class, new DataListener<Model_New_Group>() {
            @Override
            public void onData(SocketIOClient sioc, Model_New_Group t, AckRequest ar) throws Exception {
                try {
                    Model_Group_Chat chat = serviceMessage.createNewGroup(t);
                    ar.sendAckData(true);

                    for (Model_Client c : listClient) {
                        if (c.getUser().getUserID() == t.getUserID1() || c.getUser().getUserID() == t.getUserID2()) {
                            c.getClient().sendEvent("new_chat", chat);
                        }
                    }
                } catch (SQLException e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("request_otp", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient sioc, String t, AckRequest ar) throws Exception {
                Model_Message ms = serviceUser.generateOTP(t);
                ar.sendAckData(ms.isAction(), ms.getMessage());
            }
        });

        server.addEventListener("reset_password", Model_Reset_Password.class, new DataListener<Model_Reset_Password>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Reset_Password t, AckRequest ar) throws Exception {
                Model_Message ms = serviceUser.resetPassword(t);
                ar.sendAckData(ms.isAction(), ms.getMessage());
            }
        });

        server.addEventListener("update_profile", Model_User_Profile.class, new DataListener<Model_User_Profile>() {
            @Override
            public void onData(SocketIOClient sioc, Model_User_Profile t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.updateProfile(t);
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("update_password", Model_Reset_Password.class, new DataListener<Model_Reset_Password>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Reset_Password t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.updatePassword(t);
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        // From: the user who sent the friend request
        server.addEventListener("accept_friend_request", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.responseFriendRequest(t, "friends");
                    Model_Group_Chat chat = serviceMessage.getPrivateChat(t.getToUserID(), t.getFromUserID());
                    Model_Client toUser = null;
                    Model_Client fromUser = null;

                    for (Model_Client c : listClient) {
                        if (c.getUser().getUserID() == t.getToUserID()) {
                            // Send the chat to the user who accepted the friend request
                            c.getClient().sendEvent("new_chat", chat);
                            toUser = c;
                        }

                        if (c.getUser().getUserID() == t.getFromUserID()) {
                            // Send the chat to the user who sent the friend request
                            Model_Group_Chat chat2 = serviceMessage.getPrivateChat(t.getFromUserID(), t.getToUserID());
                            c.getClient().sendEvent("new_chat", chat2);
                            fromUser = c;
                        }
                    }

                    if (toUser != null) {
                        Model_User_Profile user = serviceUser.getUserProfile(t.getFromUserID());
                        if (fromUser != null) {
                            toUser.getClient().sendEvent("new_friend", new Model_Friend(t.getFromUserID(), user.getName(), "online"));
                            fromUser.getClient().sendEvent("new_friend", new Model_Friend(t.getToUserID(), t.getName(), "online"));
                        } else {
                            toUser.getClient().sendEvent("new_friend", new Model_Friend(t.getFromUserID(), user.getName(), "offline"));
                        }
                    }
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("reject_friend_request", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.responseFriendRequest(t, "reject");
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("unfriend", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.responseFriendRequest(t, "unfriend");
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("find_new_friend", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    ArrayList<Model_Friend_Request> list = serviceUser.findNewFriend(t);
                    ar.sendAckData(true, list.toArray());
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });        

        server.addEventListener("new_chat", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    Model_Group_Chat chat = serviceMessage.getPrivateChat(t.getFromUserID(), t.getToUserID());
                    ar.sendAckData(true, chat);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });   

        server.addEventListener("report_spam", Model_Spam_Report.class, new DataListener<Model_Spam_Report>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Spam_Report t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.spamReport(t);
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("add_friend", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.addFriend(t);
                    ar.sendAckData(ok);
                    for (Model_Client c : listClient) {
                        if (c.getUser().getUserID() == t.getToUserID()) {
                            c.getClient().sendEvent("new_friend_request", t);
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("block_stranger", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    boolean ok = serviceUser.blockStranger(t);
                    ar.sendAckData(ok);
                } catch (Exception e) {
                    System.err.println(e);
                    ar.sendAckData(false);
                }
            }
        });

        server.addEventListener("block_friend", Model_Friend_Request.class, new DataListener<Model_Friend_Request>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Friend_Request t, AckRequest ar) throws Exception {
                try {
                    boolean ok;
                    if (t.getFromUserID() > t.getToUserID()) { 
                        ok = serviceUser.responseFriendRequest(t, "block_2_1");
                    }
                    else {
                        ok = serviceUser.responseFriendRequest(t, "block_1_2");
                    }
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

        // Get all history chats and friend request, friend list
        server.addEventListener("get_all_data", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer userID, AckRequest ar) throws Exception {
                try {
                    // Get all history chats
                    HashMap<Integer, LinkedList<Model_Chat_Message>> data = serviceMessage.getAllChat(userID);
                    // Get all friend request
                    ArrayList<Model_Friend_Request> list = serviceUser.getFriendRequestsReceived(userID);
                    // Get all friend list
                    ArrayList<Model_Friend> friends = serviceUser.getFriendList(userID);
                    sioc.sendEvent("get_all_data", data, list.toArray(), friends.toArray());
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });

        server.addEventListener("delete_all_messages", Model_Delete_Message.class, new DataListener<Model_Delete_Message>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Delete_Message t, AckRequest ar) throws Exception {
                try {
                    serviceMessage.deleteAllMessages(t.getGroupID(), t.getUserID());
                    ar.sendAckData(true);
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });

        server.addEventListener("delete_message_for_me", Model_Delete_Message.class, new DataListener<Model_Delete_Message>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Delete_Message t, AckRequest ar) throws Exception {
                try {
                    serviceMessage.deleteMessageForOne(t);
                    ar.sendAckData(true);
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });

        server.addEventListener("delete_message_for_everyone", Model_Delete_Message.class, new DataListener<Model_Delete_Message>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Delete_Message t, AckRequest ar) throws Exception {
                try {
                    serviceMessage.deleteMessageForAll(t);
                    ar.sendAckData(true);
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
                server.getBroadcastOperations().sendEvent("chat_status", chatId, true);
                server.getBroadcastOperations().sendEvent("user_status", userID, true);
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
                server.getBroadcastOperations().sendEvent("chat_status", chatId, false);
                server.getBroadcastOperations().sendEvent("user_status", userID, false);
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
