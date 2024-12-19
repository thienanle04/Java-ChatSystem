package user.form;

import net.miginfocom.swing.MigLayout;

import java.util.LinkedList;
import java.util.HashMap;

import io.socket.client.Ack;
import user.component.Chat_Body;
import user.component.Chat_Bottom;
import user.component.Chat_Title;
import user.component.Message_Search_Bar;
import user.event.EventChat;
import user.event.PublicEvent;

import user.service.Service;
import user.model.Model_Group_Chat;
import user.model.Model_Chat_Message;
import user.model.Model_Delete_Message;
import user.model.Model_Spam_Report;

public class Chat extends javax.swing.JPanel {
    HashMap<Integer, LinkedList<Model_Chat_Message>> chats_data;

    public Chat() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[]0[100%, fill]0[shrink 0]0"));
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
                                        message.setMessage("Can not send message");
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

            @Override
            public void newChat(Model_Group_Chat groupChat) {
                chats_data.put(groupChat.getGroupId(), new LinkedList<>());
            }

            @Override
            public void deleteAllMessages(Model_Delete_Message req) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("delete_all_messages", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    
                                    if (action) {
                                        chats_data.get(req.getGroupID()).clear();
                                        chatBody.clearChat();
                                    } else {
                                        PublicEvent.getInstance().getEventMain().showNotification("Can not delete all messages. Please try again later");
                                    }
                                }
                            }
                        });

                    }
                }).start();
            }

            @Override
            public int searchMessage(int groupID, String key) {
                if (chat_Title1.getChat() != null && chat_Title1.getChat().getGroupId() == groupID) {
                    return chatBody.searchMessages(key);
                }
                return 0;
            }
            
            @Override
            public void navigateToMatch(int direction) {
                chatBody.navigateMatches(direction);
            }

            @Override
            public void cancelSearch() {
                chatBody.cancelSearch();
            }

            @Override
            public void searchAllMessage(String key) {
                // TODO Search all messages

            }

            @Override
            public void deleteMessageForMe(Model_Delete_Message req) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("delete_message_for_me", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    
                                    if (action) {
                                        chatBody.removeMessage(req.getMessageID());

                                        // Remove message from chat data of this group
                                        if (chats_data.containsKey(req.getGroupID())) {
                                            // Remove message from chat data of this group
                                            LinkedList<Model_Chat_Message> messages = chats_data.get(req.getGroupID());
                                            messages.removeIf(msg -> msg.getMessageID() == req.getMessageID());
                                        }
                                    } else {

                                    }
                                }
                            }
                        });

                    }
                }).start();
            }

            @Override
            public void deleteMessageForEveryone(Model_Delete_Message req) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("delete_message_for_everyone", req.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    
                                    if (action) {
                                        // Set message text to "This message has been deleted"
                                        chatBody.deleteMessage(req.getMessageID());

                                        // Set message text to "This message has been deleted"
                                        if (chats_data.containsKey(req.getGroupID())) {
                                            // Set message text to "This message has been deleted"
                                            for (Model_Chat_Message message : chats_data.get(req.getGroupID())) {
                                                if (message.getMessageID() == req.getMessageID()) {
                                                    message.setMessage("This message has been deleted");
                                                }
                                            }
                                        }
                                            
                                    } else {
                                        
                                    }
                                }
                            }
                        });

                    }
                }).start();
            }

            @Override
            public void reportSpam(Model_Spam_Report report) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Service.getInstance().getClient().emit("report_spam", report.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    
                                    if (action) {
                                        PublicEvent.getInstance().getEventMain().showNotification("Report spam success");
                                    } else {
                                        PublicEvent.getInstance().getEventMain().showNotification("Report spam failed. Please try again later");
                                    }
                                }
                            }
                        });

                    }
                }).start();
            }

        });
        add(chat_Title1, "wrap");
        add(search_bar, "wrap");
        add(chatBody, "wrap");
        add(chatBottom, "h ::50%");

        chat_Title1.setVisible(false);
        search_bar.setVisible(false);
        chatBody.setVisible(false);
        chatBottom.setVisible(false);
    }

    private void selected(Model_Group_Chat groupChat) {
        chat_Title1.setChatName(groupChat);
        chatBottom.setChat(groupChat);
        chatBody.clearChat();
        search_bar.setChat(groupChat);

        chat_Title1.setVisible(true);
        chatBody.setVisible(true);
        chatBottom.setVisible(true);
        search_bar.setVisible(true);
    }

    public void setChat(Model_Group_Chat groupChat) {
        selected(groupChat);

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
        search_bar = new Message_Search_Bar();

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
    private user.component.Message_Search_Bar search_bar;
    // End of variables declaration//GEN-END:variables
}