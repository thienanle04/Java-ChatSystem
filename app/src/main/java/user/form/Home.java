package user.form;

import user.model.Model_Group_Chat;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;

public class Home extends javax.swing.JLayeredPane {

    private javax.swing.JLayeredPane content;
    private Chat chat;
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
        
        content.add(chat, "chat");
        
        cardLayout.show(content, "chat");
        this.add(content);
    }

    private void init() {
        setLayout(new MigLayout("fill", "0[fill,230!][fill, grow][fill, 230!]0", "0[fill]0"));
        this.add(new Menu_Left());
        initCardLayout();
    }

    public void setChat(Model_Group_Chat groupChat) {
        chat.setChat(groupChat);
    }

    public void updateChat(Model_Group_Chat groupChat) {
        chat.updateUser(groupChat);
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
