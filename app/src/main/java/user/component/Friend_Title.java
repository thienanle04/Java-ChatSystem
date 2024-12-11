package user.component;

import com.formdev.flatlaf.FlatClientProperties;

public class Friend_Title extends javax.swing.JPanel {

    public Friend_Title() {
        initComponents();
        title.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+10 bold;");
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        title = new javax.swing.JLabel();

        setBackground(new java.awt.Color(140, 179, 250));
        setForeground(new java.awt.Color(255, 255, 255));

        layer.setPreferredSize(new java.awt.Dimension(98, 40));
        layer.setLayout(new java.awt.GridLayout(0, 1));

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Community");
        layer.add(title);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
