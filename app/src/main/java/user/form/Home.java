package user.form;

import user.event.EventHome;
import user.event.PublicEvent;
import user.model.Model_Group_Chat;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;

public class Home extends javax.swing.JLayeredPane {

    private javax.swing.JLayeredPane content;
    private Chat chat;
    private Find_New_Friend find_new_friend;
    private Friend_List friend_list;
    private Friend_Request friend_request;
    private CardLayout cardLayout;
    private Menu_Left menuLeft;
    private Menu_Right menuRight;
    private Find_All_Messages find_all_messages;


    public Home() {
        initComponents();
        init();
    }
    
    private void initCardLayout() {
        cardLayout = new CardLayout();
        content = new javax.swing.JLayeredPane();
        
        content.setLayout(cardLayout);
        
        chat = new Chat();
        friend_list = new Friend_List();
        friend_request = new Friend_Request();
        find_new_friend = new Find_New_Friend();
        find_all_messages = new Find_All_Messages();
        
        content.add(chat, "chat");
        content.add(friend_list, "friend_list");
        content.add(friend_request, "friend_request");
        content.add(find_new_friend, "find_new_friend");
        content.add(find_all_messages, "find_all_messages");
        
        cardLayout.show(content, "chat");
        this.add(content);
    }

    private void init() {
        PublicEvent.getInstance().addEventHome(new EventHome() {
            @Override
            public void renameGroupChat(Model_Group_Chat groupChat) {
                menuLeft.renameGroupChat(groupChat);
                chat.renameGroupChat(groupChat);
            }
        });

        setLayout(new MigLayout("fill, hidemode 3", "0[fill,230!][fill, grow][fill, 230!]0", "0[fill]0"));
        menuLeft = new Menu_Left();
        this.add(menuLeft);
        initCardLayout();
        menuRight = new Menu_Right();
        this.add(menuRight);
        menuRight.setVisible(false);
        refreshUI();
    }

    public void searchAllMessages(String key) {
        find_all_messages.showResults(chat.searchAllMessages(key));
        setLayout(new MigLayout("fill, hidemode 3", "0[fill,230!][fill, grow]0", "0[fill]0"));
        cardLayout.show(content, "find_all_messages");
        menuRight.setVisible(false);
        refreshUI();
    }

    public void setChat(Model_Group_Chat groupChat) {
        setLayout(new MigLayout("fill, hidemode 3", "0[fill,230!][fill, grow][fill, 230!]0", "0[fill]0"));
        menuRight.setVisible(true);
        cardLayout.show(content, "chat");
        chat.setChat(groupChat);
        menuRight.setChat(groupChat);
        menuRight.setVisible(true);
        refreshUI();
    }

    public void updateChat(Model_Group_Chat groupChat) {
        chat.updateUser(groupChat);
    }

    public void showFriendList() {
        setLayout(new MigLayout("fill, hidemode 3", "0[fill,230!][fill, grow]0", "0[fill]0"));
        cardLayout.show(content, "friend_list");
        menuRight.setVisible(false);
        refreshUI();
    }

    public void showFriendRequest() {
        setLayout(new MigLayout("fill, hidemode 3", "0[fill,230!][fill, grow]0", "0[fill]0"));
        cardLayout.show(content, "friend_request");
        menuRight.setVisible(false);
        refreshUI();
    }
    
    public void showFindNewFriend() {
        setLayout(new MigLayout("fill, hidemode 3", "0[fill,230!][fill, grow]0", "0[fill]0"));
        cardLayout.show(content, "find_new_friend");
        menuRight.setVisible(false);
        refreshUI();
    }

    private void refreshUI() {
        this.revalidate();
        this.repaint();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
