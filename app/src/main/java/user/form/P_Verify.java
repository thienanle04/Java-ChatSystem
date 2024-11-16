package user.form;

import com.formdev.flatlaf.FlatClientProperties;
import user.event.PublicEvent;

public class P_Verify extends javax.swing.JPanel {

    public P_Verify() {
        initComponents();
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+17;");
        goBackToResetPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:-2;");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        cmdVerify = new javax.swing.JButton();
        goBackToResetPassword = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(281, 328));
        setMinimumSize(new java.awt.Dimension(281, 328));

        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Verify");

        jLabel1.setText("Enter your verification code");

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        cmdVerify.setText("Verify");
        cmdVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVerifyActionPerformed(evt);
            }
        });

        goBackToResetPassword.setForeground(new java.awt.Color(15, 128, 206));
        goBackToResetPassword.setText("Go back");
        goBackToResetPassword.setContentAreaFilled(false);
        goBackToResetPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        goBackToResetPassword.setMargin(new java.awt.Insets(3, 0, 3, 0));
        goBackToResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackToResetPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goBackToResetPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdVerify, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(txtUser)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbTitle)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdVerify)
                .addGap(0, 0, 0)
                .addComponent(goBackToResetPassword)
                .addContainerGap(169, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void goBackToResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackToResetPasswordActionPerformed
        PublicEvent.getInstance().getEventLogin().goBackResetPassword();
    }//GEN-LAST:event_goBackToResetPasswordActionPerformed

    private void cmdVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVerifyActionPerformed
        //TODO: Check verification code
    }//GEN-LAST:event_cmdVerifyActionPerformed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdVerify;
    private javax.swing.JButton goBackToResetPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
