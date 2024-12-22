package user.form;

import io.socket.client.Ack;
import net.miginfocom.swing.MigLayout;
import user.model.Model_Friend_Request;
import user.component.Friend_Title;
import user.event.EventFriendList;
import user.event.PublicEvent;
import user.component.Friend_List_Search_Bar;
import user.component.Friend_List_Body;
import user.model.Model_Friend;
import user.service.Service;
import user.model.Model_New_Group;

public class Friend_List extends javax.swing.JPanel {
    Friend_Title title;
    Friend_List_Search_Bar search_bar;
    Friend_List_Body body;

    public Friend_List() {
        initComponents();
        init();
    }

    private void init() {
        PublicEvent.getInstance().addEventFriendList(new EventFriendList() {
            @Override
            public void addFriend(Model_Friend friend) {
                body.addFriend(friend);
            }

            @Override
            public void userConnect(int userID) {
                body.updateUserStatus(userID, "online");
            }

            @Override
            public void userDisconnect(int userID) {
                body.updateUserStatus(userID, "offline");
            }

            @Override
            public void filterFriend(String name, String status) {
                body.filterFriend(name, status);
            }

            @Override
            public void unFriend(Model_Friend friend) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Model_Friend_Request req = new Model_Friend_Request(friend.getUserID(), Service.getInstance().getUser().getUserID(), friend.getName());
                        Service.getInstance().getClient().emit("unfriend", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... args) {
                                if ((boolean) args[0]) {
                                    body.removeFriend(friend.getUserID());
                                    PublicEvent.getInstance().getEventMain().showNotification("Unfriend successful");
                                }
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void blockFriend(Model_Friend friend) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Model_Friend_Request req = new Model_Friend_Request(friend.getUserID(), Service.getInstance().getUser().getUserID(), friend.getName());
                        Service.getInstance().getClient().emit("block_friend", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... args) {
                                if ((boolean) args[0]) {
                                    body.removeFriend(friend.getUserID());
                                    PublicEvent.getInstance().getEventMain().showNotification("You have blocked " + friend.getName());
                                }
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void newGroupChat(Model_New_Group group) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("create_new_group", group.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... args) {
                                if ((boolean) args[0]) {
                                    PublicEvent.getInstance().getEventMain().showNotification("Create group chat successful");
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        setLayout(new MigLayout("fillx", "0[fill]0", "0[200]10[100]10[fill]0"));
        title = new Friend_Title();
        search_bar = new Friend_List_Search_Bar();
        body = new Friend_List_Body();
        add(title, "wrap");
        add(search_bar, "wrap");
        title.setTitle("Friend list");
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