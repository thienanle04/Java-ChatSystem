package user.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Friend_Search extends javax.swing.JPanel {
    public Friend_Search() {
        initComponents();
        init();
    }
    
    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(242,242,242));
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(249,249,249));
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        icon = new user.swing.ImageAvatar();
        lb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));

        icon.setBorderSize(0);
        icon.setImage(new javax.swing.ImageIcon(getClass().getResource("/user/friend_search.png"))); // NOI18N

        lb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb.setText("Find new");
        lb.setMaximumSize(new java.awt.Dimension(50, 20));
        lb.setMinimumSize(new java.awt.Dimension(50, 20));
        lb.setName(""); // NOI18N
        lb.setPreferredSize(new java.awt.Dimension(50, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(icon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private user.swing.ImageAvatar icon;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
