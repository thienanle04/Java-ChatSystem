package user.form;

import java.util.ArrayList;

import org.json.JSONArray;

import io.socket.client.Ack;
import net.miginfocom.swing.MigLayout;
import user.component.Find_New_Friend_Body;
import user.component.Find_New_Friend_Search_Bar;
import user.component.Friend_Title;
import user.event.EventFindNewFriend;
import user.event.PublicEvent;
import user.model.Model_Friend_Request;
import user.service.Service;

public class Find_New_Friend extends javax.swing.JPanel {
    Friend_Title title;
    Find_New_Friend_Search_Bar search_bar;
    Find_New_Friend_Body body;

    public Find_New_Friend() {
        initComponents();
        init();
    }

    private void init() {
        PublicEvent.getInstance().addEventFindNewFriend(new EventFindNewFriend() {
            @Override
            public void addFriend(Model_Friend_Request friend) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Model_Friend_Request req = new Model_Friend_Request(friend.getToUserID(), Service.getInstance().getUser().getUserID(), friend.getName());
                        Service.getInstance().getClient().emit("add_friend", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... args) {
                                if ((boolean) args[0]) {
                                    body.removeFriend(friend.getToUserID());
                                    PublicEvent.getInstance().getEventMain().showNotification("Your friend request has been sent to " + friend.getName());
                                }
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void blockFriend(Model_Friend_Request friend) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Model_Friend_Request req = new Model_Friend_Request(friend.getToUserID(), Service.getInstance().getUser().getUserID(), friend.getName());
                        Service.getInstance().getClient().emit("block_stranger", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... args) {
                                if ((boolean) args[0]) {
                                    body.removeFriend(friend.getToUserID());
                                    PublicEvent.getInstance().getEventMain().showNotification("You have blocked " + friend.getName());
                                }
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void findNewFriend(Model_Friend_Request req) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("find_new_friend", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if ((boolean) os[0]) {
                                    body.clearFriends();
                                    ArrayList<Model_Friend_Request> people = new ArrayList<>();
                                    JSONArray arr = (JSONArray) os[1];
                                    for (Object o : arr) {
                                        Model_Friend_Request fr = new Model_Friend_Request(o);
                                        people.add(fr);
                                    }
                                    body.addFriends(people);
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        setLayout(new MigLayout("fillx", "0[fill]0", "0[200]10[100]10[fill]0"));
        title = new Friend_Title();
        search_bar = new Find_New_Friend_Search_Bar();
        body = new Find_New_Friend_Body();
        add(title, "wrap");
        add(search_bar, "wrap");
        title.setTitle("Find new friend");
        add(body, "wrap");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}