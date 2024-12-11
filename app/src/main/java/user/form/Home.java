package user.form;

import user.model.Model_Group_Chat;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;

public class Home extends javax.swing.JLayeredPane {

    private javax.swing.JLayeredPane content;
    private Chat chat;
    private Friend_List friend_list;
    private Friend_Request friend_request;
    private CardLayout cardLayout;

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
        
        content.add(chat, "chat");
        content.add(friend_list, "friend_list");
        content.add(friend_request, "friend_request");
        
        cardLayout.show(content, "friend_list");
        this.add(content);
    }

    private void init() {
        setLayout(new MigLayout("fill", "0[fill,230!][fill, grow][fill, 230!]0", "0[fill]0"));
        this.add(new Menu_Left());
        initCardLayout();
        this.add(new Menu_Right());
    }

    public void setChat(Model_Group_Chat groupChat) {
        cardLayout.show(content, "chat");
        chat.setChat(groupChat);
    }

    public void updateChat(Model_Group_Chat groupChat) {
        chat.updateUser(groupChat);
    }

    public void showFriendList() {
        cardLayout.show(content, "friend_list");
    }

    public void showFriendRequest() {
        cardLayout.show(content, "friend_request");
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
