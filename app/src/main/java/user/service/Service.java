package user.service;

import user.event.PublicEvent;
import user.model.Model_Chat_Message;
import user.model.Model_Friend_Request;
import user.model.Model_User_Profile;
import user.model.Model_Group_Chat;
import user.model.Model_Friend;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
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

            client.on("new_chat", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_Group_Chat chat = new Model_Group_Chat(os[0]);
                    PublicEvent.getInstance().getEventMenuLeft().newChat(chat);
                }
            });

            client.on("get_all_data", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    // Get all chat and messages
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

                    // Get all friend requests
                    JSONArray arr = (JSONArray) os[1];
                    for (Object o : arr) {
                        Model_Friend_Request fr = new Model_Friend_Request(o);
                        PublicEvent.getInstance().getEventFriendRequest().addNewFriendRequest(fr);
                    }

                    // Get all friends
                    JSONArray arr2 = (JSONArray) os[2];
                    for (Object o : arr2) {
                        Model_Friend friend = new Model_Friend(o);
                        PublicEvent.getInstance().getEventFriendList().addFriend(friend);;
                    }
                }
            });

            client.on("chat_status", new Emitter.Listener() {
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

            client.on("new_friend_request", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_Friend_Request fr = new Model_Friend_Request(os[0]);
                    PublicEvent.getInstance().getEventFriendRequest().addNewFriendRequest(fr);
                }
            });

            client.on("user_status", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    int userID = (Integer) os[0];
                    boolean status = (Boolean) os[1];
                    if (status) {
                        //  connect
                        PublicEvent.getInstance().getEventFriendList().userConnect(userID);
                    } else {
                        //  disconnect
                        PublicEvent.getInstance().getEventFriendList().userDisconnect(userID);
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
