package user.form;

import java.util.ArrayList;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import java.util.List;

import user.model.Model_Friend;
import user.model.Model_Group_Chat;
import user.component.Friend_List_Menu_Button;
import user.component.Friend_Request_Menu_Button;
import user.component.Friend_Search;
import user.component.Item_People;
import user.event.EventMenuLeft;
import user.event.PublicEvent;
import user.app.GroupType;

public class Menu_Left extends javax.swing.JPanel {

    private List<Model_Group_Chat> chats;

    public Menu_Left() {
        initComponents();
        init();
    }
    
    private void init() {
        sp.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "width:5;"
                + "background:null;"
                + "trackArc:$ScrollBar.thumbArc;"
                + "thumbInsets:0,0,0,0;");
        sp.getVerticalScrollBar().setUnitIncrement(10);
        menuList.setLayout(new MigLayout("fillx", "0[fill]0", "0[]0"));
        chats = new ArrayList<>();
        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
            @Override
            public void newChat(List<Model_Group_Chat> groupChats) {
                for (Model_Group_Chat d : groupChats) {
                    chats.add(d);
                    menuList.add(new Item_People(d), "wrap");
                    refreshMenuList();
                }
            }

            @Override
            public void newChat(Model_Group_Chat groupChat) {
                if (menuMessage.isSelected()) {
                    menuList.add(new Item_People(groupChat), "wrap");
                    refreshMenuList();
                }

                for (Model_Group_Chat d : chats) {
                    if (groupChat.getName().equals(d.getName())) {
                        return;
                    }
                }
                chats.add(groupChat);
            }

            @Override
            public void selectChat(Model_Friend friend) {
                for (Model_Group_Chat d : chats) {
                    if (d.getGroupType() == GroupType.TWO) {
                        if (d.getUserID() == friend.getUserID()) {
                            PublicEvent.getInstance().getEventMain().selectChat(d);
                            break;
                        }
                    }
                }
            }

            @Override
            public void userConnect(int groupChatId) {
                for (Model_Group_Chat u : chats) {
                    if (u.getGroupId() == groupChatId) {
                        u.setStatus("online");
                        PublicEvent.getInstance().getEventMain().updateChat(u);
                        break;
                    }
                }
                if (menuMessage.isSelected()) {
                    for (java.awt.Component com : menuList.getComponents()) {
                        Item_People item = (Item_People) com;
                        if (item.getChat().getGroupId() == groupChatId) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
            }

            @Override
            public void userDisconnect(int groupChatId) {
                for (Model_Group_Chat u : chats) {
                    if (u.getGroupId() == groupChatId) {
                        u.setStatus("offline");
                        PublicEvent.getInstance().getEventMain().updateChat(u);
                        break;
                    }
                }
                if (menuMessage.isSelected()) {
                    for (java.awt.Component com : menuList.getComponents()) {
                        Item_People item = (Item_People) com;
                        if (item.getChat().getGroupId() == groupChatId) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
            }
        });
        showMessage();
    }
    
    private void showMessage() {
        menuList.removeAll();
        refreshMenuList();
        searchBar1.setVisible(true);
        for (Model_Group_Chat d : chats) {
            menuList.add(new Item_People(d), "wrap");
        }


    }
    
    private void showFriend() {
        menuList.removeAll();
        searchBar1.setVisible(false);
        menuList.add(new Friend_Search(), "wrap");
        menuList.add(new Friend_List_Menu_Button(), "wrap");
        menuList.add(new Friend_Request_Menu_Button(), "wrap");
        refreshMenuList();
    }
    
    private void showProfile() {
        PublicEvent.getInstance().getEventMain().editProfile();
    }
    
    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMessage = new user.component.MenuButton();
        menuFriend = new user.component.MenuButton();
        menuProfile = new user.component.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();
        searchBar1 = new user.component.SearchBar();

        setBackground(new java.awt.Color(249, 249, 249));

        menu.setBackground(new java.awt.Color(242, 242, 242));
        menu.setOpaque(true);
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/message_selected.png"))); // NOI18N
        menuMessage.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/user/message.png"))); // NOI18N
        menuMessage.setMaximumSize(new java.awt.Dimension(42, 42));
        menuMessage.setMinimumSize(new java.awt.Dimension(42, 42));
        menuMessage.setPreferredSize(new java.awt.Dimension(42, 42));
        menuMessage.setSelected(true);
        menuMessage.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/user/message_selected.png"))); // NOI18N
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menu.add(menuMessage);

        menuFriend.setBackground(new java.awt.Color(242, 242, 242));
        menuFriend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/group.png"))); // NOI18N
        menuFriend.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/user/group.png"))); // NOI18N
        menuFriend.setMaximumSize(new java.awt.Dimension(42, 42));
        menuFriend.setMinimumSize(new java.awt.Dimension(42, 42));
        menuFriend.setPreferredSize(new java.awt.Dimension(42, 42));
        menuFriend.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/user/group_selected.png"))); // NOI18N
        menuFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFriendActionPerformed(evt);
            }
        });
        menu.add(menuFriend);

        menuProfile.setBackground(new java.awt.Color(242, 242, 242));
        menuProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/profile_default.png"))); // NOI18N
        menuProfile.setDefaultCapable(false);
        menuProfile.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/user/profile_default.png"))); // NOI18N
        menuProfile.setMaximumSize(new java.awt.Dimension(42, 42));
        menuProfile.setMinimumSize(new java.awt.Dimension(42, 42));
        menuProfile.setPreferredSize(new java.awt.Dimension(42, 42));
        menuProfile.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/user/profile_selected.png"))); // NOI18N
        menuProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProfileActionPerformed(evt);
            }
        });
        menu.add(menuProfile);

        sp.setBackground(new java.awt.Color(249, 249, 249));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setToolTipText("");

        menuList.setBackground(new java.awt.Color(249, 249, 249));
        menuList.setOpaque(true);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addComponent(sp)
            .addComponent(searchBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(searchBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        if (!menuMessage.isSelected()) {
            menuMessage.setSelected(true);
            menuFriend.setSelected(false);
            showMessage();
        }
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFriendActionPerformed
        if (!menuFriend.isSelected()) {
            menuMessage.setSelected(false);
            menuFriend.setSelected(true);
            showFriend();
        }
    }//GEN-LAST:event_menuFriendActionPerformed

    private void menuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProfileActionPerformed
        if (!menuProfile.isSelected()) {
            showProfile();
        }
    }//GEN-LAST:event_menuProfileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private user.component.MenuButton menuFriend;
    private javax.swing.JLayeredPane menuList;
    private user.component.MenuButton menuMessage;
    private user.component.MenuButton menuProfile;
    private user.component.SearchBar searchBar1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
