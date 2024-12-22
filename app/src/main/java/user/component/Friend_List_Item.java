package user.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import user.event.PublicEvent;
import user.model.Model_Friend;
import user.model.Model_New_Group;
import user.service.Service;

public class Friend_List_Item extends javax.swing.JPanel {
    private Model_Friend friend;
    private boolean mouseOver;
    
    public Friend_List_Item(Model_Friend data) {
        initComponents();
        friend = data;
        activeStatus.setActive(data.isOnline());
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

    public Model_Friend getFriend() {
        return friend;
    }

    public void updateUserStatus(String status) {
        friend.setStatus(status);
        activeStatus.setActive(friend.isOnline());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdBlock = new javax.swing.JButton();
        cmdUnfriend = new javax.swing.JButton();
        username = new javax.swing.JLabel();
        cmdNewGroup = new javax.swing.JButton();
        activeStatus = new user.swing.ActiveStatus();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        cmdBlock.setText("Block");
        cmdBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBlockActionPerformed(evt);
            }
        });

        cmdUnfriend.setText("Unfriend");
        cmdUnfriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUnfriendActionPerformed(evt);
            }
        });

        username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        username.setText("Name");
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameMouseClicked(evt);
            }
        });

        cmdNewGroup.setText("New group");
        cmdNewGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNewGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(cmdNewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdUnfriend, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdUnfriend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdNewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void cmdNewGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewGroupActionPerformed
        // Open jdialog to enter new group name
        
        // Open JDialog to enter new group name
        String groupName = JOptionPane.showInputDialog(this, "Enter Group Name:", "New Group", JOptionPane.PLAIN_MESSAGE);

        if (groupName != null && !groupName.trim().isEmpty()) {
            // Create a new group chat
            PublicEvent.getInstance().getEventFriendList().newGroupChat(
                new Model_New_Group(Service.getInstance().getUser().getUserID(), friend.getUserID(), groupName.trim())
            );
        } else {

        }
    }//GEN-LAST:event_cmdNewGroupActionPerformed

    private void cmdUnfriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUnfriendActionPerformed
        PublicEvent.getInstance().getEventFriendList().unFriend(friend);
    }//GEN-LAST:event_cmdUnfriendActionPerformed

    private void cmdBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBlockActionPerformed
        PublicEvent.getInstance().getEventFriendList().blockFriend(friend);
    }//GEN-LAST:event_cmdBlockActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private user.swing.ActiveStatus activeStatus;
    private javax.swing.JButton cmdBlock;
    private javax.swing.JButton cmdNewGroup;
    private javax.swing.JButton cmdUnfriend;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
