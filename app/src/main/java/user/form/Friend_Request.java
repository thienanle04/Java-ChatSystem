package user.form;

import io.socket.client.Ack;
import net.miginfocom.swing.MigLayout;
import user.component.Friend_Title;
import user.event.PublicEvent;
import user.service.Service;
import user.component.Friend_Request_Search_Bar;
import user.component.Friend_Request_Body;
import user.model.Model_Friend_Request;
import user.event.EventFriendRequest;

public class Friend_Request extends javax.swing.JPanel {
    Friend_Title title;
    Friend_Request_Search_Bar search_bar;
    Friend_Request_Body body;

    public Friend_Request() {
        initComponents();
        init();
    }

    private void init() {
        PublicEvent.getInstance().addEventFriendRequest(new EventFriendRequest() {
            @Override
            public void acceptFriendRequest(Model_Friend_Request response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("accept_friend_request", response.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... args) {
                                if ((boolean) args[0]) {
                                    body.removeFriendRequest(response);
                                }
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void rejectFriendRequest(Model_Friend_Request response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("reject_friend_request", response.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... args) {
                                if ((boolean) args[0]) {
                                    body.removeFriendRequest(response);
                                }
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void addNewFriendRequest(Model_Friend_Request request) {
                body.addFriendRequest(request);
            }
        });

        setLayout(new MigLayout("fillx", "0[fill]0", "0[200]10[150]10[fill]0"));
        title = new Friend_Title();
        search_bar = new Friend_Request_Search_Bar();
        body = new Friend_Request_Body();
        add(title, "wrap");
        add(search_bar, "wrap");
        title.setTitle("Friend request");
        add(body, "wrap");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 727, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 681, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}