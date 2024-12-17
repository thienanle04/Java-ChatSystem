package user.form;

import com.formdev.flatlaf.FlatClientProperties;

import user.event.EventMessage;
import user.event.PublicEvent;
import user.model.Model_Message;
import user.model.Model_Reset_Password;

public class P_ResetPassword extends javax.swing.JPanel {

    public P_ResetPassword() {
        initComponents();
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+17;");
        cmdBackLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+0;");

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        otp = new javax.swing.JLabel();
        cmdResetPassword = new javax.swing.JButton();
        cmdBackLogin = new javax.swing.JButton();
        newPass = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JLabel();
        txtOTP = new javax.swing.JTextField();
        cmdSendOTP = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Reset password");

        email.setText("Email");

        otp.setText("OTP");

        cmdResetPassword.setText("Reset password");
        cmdResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdResetPasswordActionPerformed(evt);
            }
        });

        cmdBackLogin.setForeground(new java.awt.Color(15, 128, 206));
        cmdBackLogin.setText("Back to login");
        cmdBackLogin.setContentAreaFilled(false);
        cmdBackLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBackLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackLoginActionPerformed(evt);
            }
        });

        newPassword.setText("New password");

        cmdSendOTP.setText("Send OTP");
        cmdSendOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSendOTPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdSendOTP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdBackLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdResetPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(otp, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(newPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(txtOTP, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbTitle)
                .addGap(20, 20, 20)
                .addComponent(email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSendOTP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdResetPassword)
                .addGap(0, 0, 0)
                .addComponent(cmdBackLogin)
                .addGap(0, 41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackLoginActionPerformed
        PublicEvent.getInstance().getEventLogin().goLogin();
    }//GEN-LAST:event_cmdBackLoginActionPerformed

    private void cmdResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdResetPasswordActionPerformed
        String email = txtEmail.getText().trim();
        String otp = txtOTP.getText().trim();
        String newPassword = String.valueOf(newPass.getPassword());
        if (email.equals("")) {
           txtEmail.grabFocus();
        } else if (otp.equals("")) {
           txtOTP.grabFocus();
        } else if (newPassword.equals("")) {
           newPass.grabFocus();
        } else {
            Model_Reset_Password data = new Model_Reset_Password(email, otp, newPassword);
            PublicEvent.getInstance().getEventLogin().resetPassword(data, new EventMessage() {
                @Override
                public void callMessage(Model_Message message) {
                    if (message.isAction()) {
                        PublicEvent.getInstance().getEventMain().showNotification("Your password has been reset successfully.\nPlease login with your new password.");
                        PublicEvent.getInstance().getEventLogin().goLogin(); 
                        txtEmail.setText("");
                        txtOTP.setText("");
                        newPass.setText("");
                    } else {
                        PublicEvent.getInstance().getEventMain().showNotification(message.getMessage());
                    }
                }
            });
        }
    }//GEN-LAST:event_cmdResetPasswordActionPerformed

    private void cmdSendOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSendOTPActionPerformed
        PublicEvent.getInstance().getEventLogin().requestOTP(txtEmail.getText().trim(), new EventMessage() {
            @Override
            public void callMessage(Model_Message message) {
                
                if (message.isAction()) {
                    PublicEvent.getInstance().getEventMain().showNotification(message.getMessage() + "\nYou can request a new OTP once every 3 minutes.");
                    cmdSendOTP.setEnabled(false);
                    txtEmail.setEnabled(false);
                    
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(180000);
                                txtEmail.setEnabled(true);
                                cmdSendOTP.setEnabled(true);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    PublicEvent.getInstance().getEventMain().showNotification(message.getMessage());
                }
            }
        });
    }//GEN-LAST:event_cmdSendOTPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBackLogin;
    private javax.swing.JButton cmdResetPassword;
    private javax.swing.JButton cmdSendOTP;
    private javax.swing.JLabel email;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPasswordField newPass;
    private javax.swing.JLabel newPassword;
    private javax.swing.JLabel otp;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtOTP;
    // End of variables declaration//GEN-END:variables
}
