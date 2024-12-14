package user.component;

import java.awt.Component;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import user.model.Model_Friend;

public class Friend_List_Body extends javax.swing.JPanel {

    public Friend_List_Body() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx, hidemode 3", "10[fill]10", "10[fill]10"));
        sp.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "width:5;"
                + "background:null;"
                + "trackInsets:5,0,5,0;"
                + "thumbInsets:5,0,5,0;");
        sp.getVerticalScrollBar().setUnitIncrement(10);
    }

    public void addFriend(Model_Friend friend) {
        Friend_List_Item item = new Friend_List_Item(friend);
        body.add(item, "wrap, w 100::100%");
        repaint();
        revalidate();
    }

    public void updateUserStatus(int userID, String status) {
        for (Component component : body.getComponents()) {
            if (component instanceof Friend_List_Item) {
                Friend_List_Item item = (Friend_List_Item) component;
                if (item.getFriend().getUserID() == userID) {
                    item.updateUserStatus(status);
                    break;
                }
            }
        }
        repaint();
        revalidate();
    }

    public void removeFriend(int userID) {
        for (Component component : body.getComponents()) {
            if (component instanceof Friend_List_Item) {
                Friend_List_Item item = (Friend_List_Item) component;
                if (item.getFriend().getUserID() == userID) {
                    body.remove(component);
                    break;
                }
            }
        }
        repaint();
        revalidate();
    }

    public void filterFriend(String name, String status) {
        for (Component component : body.getComponents()) {
            if (component instanceof Friend_List_Item) {
                Friend_List_Item item = (Friend_List_Item) component;
                if (item.getFriend().getName().toLowerCase().contains(name.toLowerCase()) && (status.equals("all") || item.getFriend().getStatus().equals(status))) {
                    item.setVisible(true);
                } else {
                    item.setVisible(false);
                }
            }
        }
        body.repaint();
        body.revalidate();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        setPreferredSize(new java.awt.Dimension(826, 1000));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables

}
