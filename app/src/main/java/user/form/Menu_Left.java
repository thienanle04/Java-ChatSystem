package user.form;

import net.miginfocom.swing.MigLayout;
import user.component.Item_People;
import user.swing.ScrollBar;

public class Menu_Left extends javax.swing.JPanel {
    public Menu_Left() {
        initComponents();
        init();
    }
    
    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        showMessages();
    }
    
    private void showMessages() {
        menuList.removeAll();
        for(int i = 0; i < 20; i++) {
            menuList.add(new Item_People("Person " + i), "wrap");
        }
        refreshMenuList();
    }
    
    private void showGroup() {
        menuList.removeAll();
        for(int i = 0; i < 5; i++) {
            menuList.add(new Item_People("Group " + i), "wrap");
        }
        refreshMenuList();
    }
    
    private void showBox() {
        menuList.removeAll();
        for(int i = 0; i < 10; i++) {
            menuList.add(new Item_People("Box " + i), "wrap");
        }
        refreshMenuList();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMessage = new user.component.MenuButton();
        menuGroup = new user.component.MenuButton();
        menuBox = new user.component.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

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

        menuGroup.setBackground(new java.awt.Color(242, 242, 242));
        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/group.png"))); // NOI18N
        menuGroup.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/user/group.png"))); // NOI18N
        menuGroup.setMaximumSize(new java.awt.Dimension(42, 42));
        menuGroup.setMinimumSize(new java.awt.Dimension(42, 42));
        menuGroup.setPreferredSize(new java.awt.Dimension(42, 42));
        menuGroup.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/user/group_selected.png"))); // NOI18N
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuGroup);

        menuBox.setBackground(new java.awt.Color(242, 242, 242));
        menuBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/box.png"))); // NOI18N
        menuBox.setDefaultCapable(false);
        menuBox.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/user/box.png"))); // NOI18N
        menuBox.setMaximumSize(new java.awt.Dimension(42, 42));
        menuBox.setMinimumSize(new java.awt.Dimension(42, 42));
        menuBox.setPreferredSize(new java.awt.Dimension(42, 42));
        menuBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/user/box_selected.png"))); // NOI18N
        menuBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoxActionPerformed(evt);
            }
        });
        menu.add(menuBox);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp)
                    .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 114, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        if (!menuMessage.isSelected()) {
            menuMessage.setSelected(true);
            menuGroup.setSelected(false);
            menuBox.setSelected(false);
            showMessages();
        }
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
        if (!menuGroup.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(true);
            menuBox.setSelected(false);
            showGroup();
        }
    }//GEN-LAST:event_menuGroupActionPerformed

    private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoxActionPerformed
        if (!menuBox.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(false);
            menuBox.setSelected(true);
            showBox();
        }
    }//GEN-LAST:event_menuBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private user.component.MenuButton menuBox;
    private user.component.MenuButton menuGroup;
    private javax.swing.JLayeredPane menuList;
    private user.component.MenuButton menuMessage;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}