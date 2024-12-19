package user.component;

import com.formdev.flatlaf.FlatClientProperties;

import user.event.PublicEvent;
import user.model.Model_Friend_Request;
import user.service.Service;

public class Find_New_Friend_Search_Bar extends javax.swing.JPanel {

    public Find_New_Friend_Search_Bar() {
        initComponents();
        cmdSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+3;");
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterName = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(504, 100));

        filterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterNameActionPerformed(evt);
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
                .addComponent(filterName, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filterName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        PublicEvent.getInstance().getEventFindNewFriend().findNewFriend(new Model_Friend_Request(0, Service.getInstance().getUser().getUserID(), filterName.getText()));
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void filterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterNameActionPerformed
        PublicEvent.getInstance().getEventFindNewFriend().findNewFriend(new Model_Friend_Request(0, Service.getInstance().getUser().getUserID(), filterName.getText()));
    }//GEN-LAST:event_filterNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSearch;
    private javax.swing.JTextField filterName;
    // End of variables declaration//GEN-END:variables
}