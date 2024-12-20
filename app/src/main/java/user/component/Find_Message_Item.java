package user.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;

import user.event.PublicEvent;
import user.model.Model_Chat_Message;

public class Find_Message_Item extends javax.swing.JPanel {
    private Model_Chat_Message message;
    private boolean mouseOver;
    
    public Find_Message_Item(Model_Chat_Message data) {
        initComponents();
        message = data;
        groupName.setText(PublicEvent.getInstance().getEventMenuLeft().getChat(data.getGroupID()).getName());
        userName.setText(data.getUserName() + ":");
        txtMessage.setText(data.getMessage());
        time.setText("at " + data.getTime().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
        addMouseListenerToInsideComponents(new MouseAdapter() {
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
                    PublicEvent.getInstance().getEventMenuLeft().clickMessageItem(message);
                }
            }
        }); 
    }
    
    private void addMouseListenerToInsideComponents(MouseAdapter adapter) {
        txtMessage.addMouseListener(adapter);
        groupName.addMouseListener(adapter);
        userName.addMouseListener(adapter);
        time.addMouseListener(adapter);
        addMouseListener(adapter);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupName = new javax.swing.JLabel();
        txtMessage = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        groupName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        groupName.setText("Name");

        txtMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMessage.setForeground(new java.awt.Color(51, 102, 255));
        txtMessage.setText("Message");

        userName.setBackground(new java.awt.Color(255, 153, 153));
        userName.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        userName.setText("username:");
        userName.setPreferredSize(new java.awt.Dimension(10, 20));

        time.setBackground(new java.awt.Color(255, 153, 153));
        time.setForeground(new java.awt.Color(204, 204, 204));
        time.setText("at");
        time.setPreferredSize(new java.awt.Dimension(10, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(groupName, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGap(80, 80, 80))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtMessage)
                        .addGap(5, 5, 5)
                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)))
                .addContainerGap(337, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMessage)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel groupName;
    private javax.swing.JLabel time;
    private javax.swing.JLabel txtMessage;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}
