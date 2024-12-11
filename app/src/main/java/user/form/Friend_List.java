package user.form;

import net.miginfocom.swing.MigLayout;
import user.component.Friend_Title;
import user.component.Friend_Search_Bar;
import user.component.Friend_List_Body;

public class Friend_List extends javax.swing.JPanel {
    Friend_Title title;
    Friend_Search_Bar search_bar;
    Friend_List_Body body;

    public Friend_List() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[200]10[100]10[fill]0"));
        title = new Friend_Title();
        search_bar = new Friend_Search_Bar();
        body = new Friend_List_Body();
        add(title, "wrap");
        add(search_bar, "wrap");
        title.setTitle("Friend list");
        add(body, "wrap");
    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}