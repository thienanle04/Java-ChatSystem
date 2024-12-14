package user.component;

import com.formdev.flatlaf.FlatClientProperties;

import user.event.PublicEvent;

public class Friend_Search_Bar extends javax.swing.JPanel {

    public Friend_Search_Bar() {
        initComponents();
        cmdSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+3;");
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameFilter = new javax.swing.JTextField();
        statusFilter = new javax.swing.JComboBox<>();
        cmdSearch = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(504, 100));

        nameFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFilterActionPerformed(evt);
            }
        });

        statusFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Online", "Offline" }));
        statusFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusFilterActionPerformed(evt);
            }
        });

        cmdSearch.setText("Search");
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cmdSearch)
                .addGap(5, 5, 5)
                .addComponent(nameFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(statusFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(nameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(statusFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFilterActionPerformed
        PublicEvent.getInstance().getEventFriendList().filterFriend(nameFilter.getText(), statusFilter.getSelectedItem().toString().toLowerCase());
    }//GEN-LAST:event_nameFilterActionPerformed

    private void statusFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusFilterActionPerformed
        PublicEvent.getInstance().getEventFriendList().filterFriend(nameFilter.getText(), statusFilter.getSelectedItem().toString().toLowerCase());
    }//GEN-LAST:event_statusFilterActionPerformed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        PublicEvent.getInstance().getEventFriendList().filterFriend(nameFilter.getText(), statusFilter.getSelectedItem().toString().toLowerCase());
    }//GEN-LAST:event_cmdSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSearch;
    private javax.swing.JTextField nameFilter;
    private javax.swing.JComboBox<String> statusFilter;
    // End of variables declaration//GEN-END:variables
}
