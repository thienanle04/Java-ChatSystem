package user.component;

import com.formdev.flatlaf.FlatClientProperties;

import user.event.PublicEvent;

public class Friend_Request_Search_Bar extends javax.swing.JPanel {

    public Friend_Request_Search_Bar() {
        initComponents();
        cmdFilter.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+3;");
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchByName = new javax.swing.JTextField();
        cmdFilter = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        searchByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByNameActionPerformed(evt);
            }
        });

        cmdFilter.setText("Filter");
        cmdFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cmdFilter)
                .addGap(5, 5, 5)
                .addComponent(searchByName, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchByName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmdFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByNameActionPerformed
        PublicEvent.getInstance().getEventFriendRequest().searchFriendRequest(searchByName.getText());
    }//GEN-LAST:event_searchByNameActionPerformed

    private void cmdFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFilterActionPerformed
        PublicEvent.getInstance().getEventFriendRequest().searchFriendRequest(searchByName.getText());
    }//GEN-LAST:event_cmdFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdFilter;
    private javax.swing.JTextField searchByName;
    // End of variables declaration//GEN-END:variables
}
