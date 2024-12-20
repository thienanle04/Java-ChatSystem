package user.component;

import com.formdev.flatlaf.FlatClientProperties;
import user.event.PublicEvent;
import user.model.Model_Delete_Message;
import user.model.Model_Group_Chat;
import user.service.Service;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class Item_People extends javax.swing.JPanel {

    public Model_Group_Chat getChat() {
        return chat;
    }
    private boolean mouseOver;
    private final Model_Group_Chat chat;

    public Item_People(Model_Group_Chat group_Chat) {
        this.chat = group_Chat;
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1;");
        lb.setText(chat.getName());
        lbStatus.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:-1 italic;");
        updateStatus();
        activeStatus.setActive(chat.isOnline());
        init();
    }

    public void updateStatus() {
        if (chat.getGroupType() == user.app.GroupType.TWO) {
            activeStatus.setActive(chat.isOnline());
            if (chat.isOnline()) {
                lbStatus.setText("Online");
                lbStatus.setForeground(new Color(40, 147, 59));
            } else {
                lbStatus.setText("Offline");
                lbStatus.setForeground(new Color(160, 160, 160));
            }
        }
    }

    public void setName(String name) {
        chat.setName(name);
        lb.setText(name);
    }

    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(230, 230, 230));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(242, 242, 242));
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (mouseOver) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        PublicEvent.getInstance().getEventMain().selectChat(chat);
                    } else if (me.getButton() == MouseEvent.BUTTON3) {
                        // Show popup menu
                        // Create a popup menu
                        JPopupMenu popupMenu = new JPopupMenu();

                        // Add menu items to the popup menu
                        JMenuItem menuItem1 = new JMenuItem("Delete all messages");
                        
                        popupMenu.add(menuItem1);

                        // Add action listeners for menu items
                        menuItem1.addActionListener(_ -> {
                            PublicEvent.getInstance().getEventChat().deleteAllMessages(new Model_Delete_Message(0, chat.getGroupId(), Service.getInstance().getUser().getUserID()));
                        });

                        popupMenu.show(me.getComponent(), me.getX(), me.getY());

                    }

                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new user.swing.ImageAvatar();
        lb = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        activeStatus = new user.swing.ActiveStatus();

        setBackground(new java.awt.Color(242, 242, 242));

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/user/user.png"))); // NOI18N

        lb.setText("Name");

        lbStatus.setForeground(new java.awt.Color(117, 117, 117));
        lbStatus.setText("");

        activeStatus.setActive(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbStatus)
                        .addGap(3, 3, 3)
                        .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activeStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private user.swing.ActiveStatus activeStatus;
    private user.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
