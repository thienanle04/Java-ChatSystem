package user.component;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import user.swing.ScrollBar;

public class Chat_Body extends javax.swing.JPanel {

    public Chat_Body() {
        initComponents();
        init();
        
        addDate("11/06/2024");
        addItemLeft("Hey! How’s everything going with you?", "Phuoc Nghia");
        addItemRight("Hey! I'm good, just been super busy with work. How about you?");
        
        addDate("Today");
        addItemLeft("Same here! Work has been non-stop lately. But I'm looking forward to the weekend.", "Phuoc Nghia");
        addItemRight("Oh, nice! Got any plans?");
        
        addItemLeft("Not sure yet, maybe catching up on some sleep. Or I might go hiking if the weahter's nice. You?", "Phuoc Nghia");
        addItemRight("That sounds awesome! I've been meaning to go hiking too. I might just relax, though. I could use a lazy weekend.");
    
        addItemLeft("Totally understand. Sometimes doing nothing is the best plan.", "Phuoc Nghia");
        addItemRight("Haha, exactly!");
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[bottom]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
        
    }

    public void addItemLeft(String text, String user) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setUserProfile(user);
        body.add(item, "wrap, w 100::50%");
        body.repaint();
        body.revalidate();
    }

    public void addItemRight(String text) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        body.add(item, "wrap, al right, w 100::50%");
        body.repaint();
        body.revalidate();
    }
    
    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

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
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
