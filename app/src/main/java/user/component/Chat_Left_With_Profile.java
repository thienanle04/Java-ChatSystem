package user.component;

import java.awt.Color;
import javax.swing.Icon;

public class Chat_Left_With_Profile extends javax.swing.JLayeredPane {

    public Chat_Left_With_Profile() {
        initComponents();
        txt.setBackground(new Color(242, 242, 242));
    }

    public void setText(String text) {
            txt.setText(text);
            txt.setTime("10:30 PM");
    }
    
    public void setImageProfile(Icon image) {
        IaImage.setImage(image);
    }
    
    public void setUserProfile(String user) {
        txt.setUserProfile(user);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        IaImage = new user.swing.ImageAvatar();
        txt = new user.component.Chat_Item();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        IaImage.setBorderColor(new java.awt.Color(0, 0, 0));
        IaImage.setBorderSize(0);
        IaImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/user/default_avatar.png"))); // NOI18N
        IaImage.setMaximumSize(new java.awt.Dimension(30, 30));
        IaImage.setMinimumSize(new java.awt.Dimension(30, 30));

        jLayeredPane1.setLayer(IaImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(IaImage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(IaImage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
