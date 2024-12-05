package user.component;

import com.formdev.flatlaf.FlatClientProperties;
import user.model.Model_Group_Chat;
import java.awt.Color;

import user.app.GroupType;

public class Chat_Title extends javax.swing.JPanel {

    public Model_Group_Chat getChat() {
        return chat;
    }

    private Model_Group_Chat chat;

    public Chat_Title() {
        initComponents();
        lbName.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1 bold;");
    }

    public void setChatName(Model_Group_Chat chat) {
        this.chat = chat;
        lbName.setText(chat.getName());
        if (chat.getGroupType() == GroupType.TWO) {
            if (chat.isOnline()) {
                statusActive();
            } else {
                setStatusText("Offline");
            }
        } else {
            setStatusText("");
        }
    }

    public void updateUser(Model_Group_Chat groupChat) {
        if (this.chat == groupChat) {
            lbName.setText(groupChat.getName());
            if (groupChat.getGroupType() == GroupType.TWO) {
                if (groupChat.isOnline()) {
                    statusActive();
                } else {
                    setStatusText("Offline");
                }
            } else {
                setStatusText("");
            }
        }
    }

    private void statusActive() {
        lbStatus.setText("Active now");
        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
    }

    private void setStatusText(String text) {
        lbStatus.setText(text);
        lbStatus.setForeground(new Color(160, 160, 160));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(229, 229, 229));

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbName.setForeground(new java.awt.Color(66, 66, 66));
        lbName.setText("Name");
        layer.add(lbName);

        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
        lbStatus.setText("");
        layer.add(lbStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(410, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
