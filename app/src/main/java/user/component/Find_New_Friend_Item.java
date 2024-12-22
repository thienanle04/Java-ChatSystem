package user.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import user.event.PublicEvent;
import user.model.Model_Friend_Request;
import user.service.Service;

public class Find_New_Friend_Item extends javax.swing.JPanel {
    private Model_Friend_Request friend;
    private boolean mouseOver;
    
    public Find_New_Friend_Item(Model_Friend_Request data) {
        initComponents();
        friend = data;
        username.setText(data.getName());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(242,242,242));
                mouseOver = true;
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(255,255,255));
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.getButton() == 1 && mouseOver) { // Left mouse button
                    // Open chat
                    PublicEvent.getInstance().getEventMenuLeft().selectChat(friend);
                }
            }
        });
    }

    public Model_Friend_Request getFriendRequest() {
        return friend;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdAddFriend = new javax.swing.JButton();
        cmdBlock = new javax.swing.JButton();
        username = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        cmdAddFriend.setText("Add Friend");
        cmdAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddFriendActionPerformed(evt);
            }
        });

        cmdBlock.setText("Block");
        cmdBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBlockActionPerformed(evt);
            }
        });

        username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        username.setText("Name");
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                .addComponent(cmdBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseClicked
        if (evt.getButton() == 1) { // Left mouse button
            // Open chat
            PublicEvent.getInstance().getEventMenuLeft().selectChat(friend);
        }
    }//GEN-LAST:event_usernameMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (evt.getButton() == 1) { // Left mouse button
            // Open chat
            PublicEvent.getInstance().getEventMenuLeft().selectChat(friend);
        }
    }//GEN-LAST:event_formMouseClicked

    private void cmdBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBlockActionPerformed
        PublicEvent.getInstance().getEventFindNewFriend().blockFriend(friend);
    }//GEN-LAST:event_cmdBlockActionPerformed

    private void cmdAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddFriendActionPerformed
        PublicEvent.getInstance().getEventFindNewFriend().addFriend(friend);
    }//GEN-LAST:event_cmdAddFriendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAddFriend;
    private javax.swing.JButton cmdBlock;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
