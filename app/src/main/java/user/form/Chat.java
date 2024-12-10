package user.form;

import net.miginfocom.swing.MigLayout;

import java.util.LinkedList;
import java.util.HashMap;

import io.socket.client.Ack;
import user.component.Chat_Body;
import user.component.Chat_Bottom;
import user.component.Chat_Title;
import user.event.EventChat;
import user.event.PublicEvent;

import user.service.Service;
import user.model.Model_Group_Chat;
import user.model.Model_Chat_Message;

public class Chat extends javax.swing.JPanel {
    HashMap<Integer, LinkedList<Model_Chat_Message>> chats_data;

    public Chat() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, fill]0[shrink 0]0"));
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(Model_Chat_Message message) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("send_to_user", message.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    
                                    if (action) {
                                        message.setMessageID((int) os[1]);
                                    } else {
                                        message.setMessage("Failed to send message");
                                    }

                                    if (chats_data.containsKey(chat_Title1.getChat().getGroupId())) {
                                        // Add message to chat data of this group
                                        chats_data.get(chat_Title1.getChat().getGroupId()).add(message);
                                    } else {
                                        // Create new chat data of this group
                                        chats_data.put(chat_Title1.getChat().getGroupId(), new LinkedList<>());
                                        chats_data.get(chat_Title1.getChat().getGroupId()).add(message);
                                    }

                                    chatBody.addItemRight(message);
                                } else {
                                    // Send message failed
                                }
                            }
                        });

                    }
                }).start();
            }

            @Override
            public void receiveMessage(Model_Chat_Message message) {
                // Check is chat title null
                if (chat_Title1.getChat() != null) {
                    if (chat_Title1.getChat().getGroupId() == message.getGroupID()) {
                        chatBody.addItemLeft(message);
                    }
                }

                if (chats_data.containsKey(message.getGroupID())) {
                    // Add message to chat data of this group
                    chats_data.get(message.getGroupID()).add(message);
                } else {
                    // Create new chat data of this group
                    chats_data.put(message.getGroupID(), new LinkedList<>());
                    chats_data.get(message.getGroupID()).add(message);
                }
            }

            @Override
            public void initAllChat(HashMap<Integer, LinkedList<Model_Chat_Message>> data) {
                chats_data = data;
            }
        });
        add(chat_Title1, "wrap");
        add(chatBody, "wrap");
        add(chatBottom, "h ::50%");

        chat_Title1.setVisible(false);
        chatBody.setVisible(false);
        chatBottom.setVisible(false);
    }

    public void setChat(Model_Group_Chat groupChat) {
        chat_Title1.setChatName(groupChat);
        chatBottom.setChat(groupChat);
        chatBody.clearChat();

        chat_Title1.setVisible(true);
        chatBody.setVisible(true);
        chatBottom.setVisible(true);

        if (chats_data.containsKey(groupChat.getGroupId())) {
            LinkedList<Model_Chat_Message> messages = chats_data.get(groupChat.getGroupId());
            for (Model_Chat_Message message : messages) {
                if (message.getSenderID() == Service.getInstance().getUser().getUserID()) {
                    chatBody.addItemRight(message);
                } else {
                    chatBody.addItemLeft(message);
                }
            }
        }
    }

    public void updateUser(Model_Group_Chat groupChat) {
        chat_Title1.updateUser(groupChat);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        chat_Title1 = new Chat_Title();
        chatBody = new Chat_Body();
        chatBottom = new Chat_Bottom();

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
    private user.component.Chat_Body chatBody;
    private user.component.Chat_Bottom chatBottom;
    private user.component.Chat_Title chat_Title1;
    // End of variables declaration//GEN-END:variables
}