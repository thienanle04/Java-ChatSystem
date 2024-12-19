package user.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import user.event.PublicEvent;
import user.model.Model_Chat_Message;
import user.model.Model_Delete_Message;
import user.model.Model_Spam_Report;
import user.service.Service;

public class Chat_Left_With_Profile extends javax.swing.JLayeredPane {
    private Model_Chat_Message message;
    private boolean mouseOverAvatar;
    
    public Chat_Left_With_Profile(Model_Chat_Message message) {
        initComponents();

        this.message = message;
        setUserProfile(message.getUserName());
        setText(message.getMessage());
        setTime(message.getTime());
        
        init();
        txt.setBackground(new Color(242, 242, 242));
    }
    
    private void init () {
        IaImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOverAvatar = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOverAvatar = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (mouseOverAvatar) {
                    if (me.getButton() == MouseEvent.BUTTON3) {
                        // Show popup menu
                        // Create a popup menu
                        JPopupMenu popupMenu = new JPopupMenu();

                        // Add menu items to the popup menu
                        JMenuItem menuItem1 = new JMenuItem("Spam report");
                        
                        popupMenu.add(menuItem1);

                        // Add action listeners for menu items
                        menuItem1.addActionListener(_ -> {
                            PublicEvent.getInstance().getEventChat().reportSpam(new Model_Spam_Report(message.getSenderID(), Service.getInstance().getUser().getUserID()));
                        });

                        popupMenu.show(me.getComponent(), me.getX(), me.getY());

                    }

                }
            }
        });

        txt.addMouseListenerToTxt(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.isPopupTrigger()) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem deleteItem = new JMenuItem("Delete message for me");
        
                    deleteItem.addActionListener(_ -> {
                        PublicEvent.getInstance().getEventChat().deleteMessageForMe(new Model_Delete_Message(message.getMessageID(), message.getGroupID(), Service.getInstance().getUser().getUserID()));
                    });
        
                    popupMenu.add(deleteItem);
                    popupMenu.show(me.getComponent(), me.getX(), me.getY());
                }
            }
        });
    }

    public Model_Chat_Message getMessage() {
        return message;
    }

    public void setUserProfile(String user) {
        txt.setUserProfile(user);
    }

    public void setImageProfile(Icon image) {
        IaImage.setImage(image);
    }

    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }
    }

    public String getText() {
        return txt.getText();
    }

    public void setHighLight() {
        txt.setHighLight();
    }

    public void setNormal() {
        txt.setNormal();
    }

    public void setTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        txt.setTime(time.format(formatter));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        IaImage = new user.swing.ImageAvatar();
        txt = new user.component.Chat_Item();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        IaImage.setBorderSize(0);
        IaImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/user/default_avatar.png"))); // NOI18N
        IaImage.setMaximumSize(new java.awt.Dimension(31, 31));
        IaImage.setMinimumSize(new java.awt.Dimension(31, 31));

        jLayeredPane1.setLayer(IaImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(IaImage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(IaImage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jLayeredPane1);
        add(txt);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private user.swing.ImageAvatar IaImage;
    private javax.swing.JLayeredPane jLayeredPane1;
    private user.component.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
