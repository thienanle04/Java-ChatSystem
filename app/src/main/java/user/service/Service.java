package user.service;

import user.event.PublicEvent;
import user.model.Model_Chat_Message;
import user.model.Model_User_Profile;
import user.model.Model_Group_Chat;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.LinkedList;
import java.util.Iterator;

public class Service {

    private static Service instance;
    private Socket client;
    private final int PORT_NUMBER = 9999;
    private final String IP = "localhost";
    private Model_User_Profile user;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    private Service() {
    }

    public void startServer() {
        try {
            client = IO.socket("http://" + IP + ":" + PORT_NUMBER);
            client.on("list_chat", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    //  list user
                    List<Model_Group_Chat> chats = new ArrayList<>();
                    for (Object o : os) {
                        Model_Group_Chat u = new Model_Group_Chat(o);
                        chats.add(u);
                    }
                    PublicEvent.getInstance().getEventMenuLeft().newChat(chats);
                }
            });

            client.on("get_all_chats", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    HashMap<Integer, LinkedList<Model_Chat_Message>> chats = new HashMap<>();
                    JSONObject obj = (JSONObject) os[0];
                    Iterator<String> keys = obj.keys();
                    while (keys.hasNext()) {
                        int key = Integer.valueOf(keys.next());
                        try {
                            chats.put(key, new LinkedList<>());
                            for (Object o : obj.getJSONArray(String.valueOf(key))) {
                                Model_Chat_Message message = new Model_Chat_Message(o);
                                chats.get(key).add(message);
                            }
                        } catch (JSONException e) {
                            error(e);
                        }

                    }
                    PublicEvent.getInstance().getEventChat().initAllChat(chats);
                }
            });

            client.on("user_status", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    int userID = (Integer) os[0];
                    boolean status = (Boolean) os[1];
                    if (status) {
                        //  connect
                        PublicEvent.getInstance().getEventMenuLeft().userConnect(userID);
                    } else {
                        //  disconnect
                        PublicEvent.getInstance().getEventMenuLeft().userDisconnect(userID);
                    }
                }
            });
            client.on("receive_ms", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_Chat_Message message = new Model_Chat_Message(os[0]);
                    PublicEvent.getInstance().getEventChat().receiveMessage(message);
                }
            });
            client.open();
        } catch (URISyntaxException e) {
            error(e);
        }
    }

    public Socket getClient() {
        return client;
    }

    public Model_User_Profile getUser() {
        return user;
    }

    public void setUser(Model_User_Profile user) {
        this.user = user;
    }

    private void error(Exception e) {
        System.err.println(e);
    }
}
