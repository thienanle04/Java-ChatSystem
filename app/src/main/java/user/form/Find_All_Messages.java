package user.form;

import net.miginfocom.swing.MigLayout;
import user.component.Friend_Title;
import user.component.Find_Message_Body;
import user.model.Model_Chat_Message;
import java.util.ArrayList;

public class Find_All_Messages extends javax.swing.JPanel {
    Friend_Title title;
    Find_Message_Body body;

    public Find_All_Messages() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[200]10[fill]0"));
        title = new Friend_Title();
        body = new Find_Message_Body();
        add(title, "wrap");
        title.setTitle("Find messages result");
        add(body, "wrap");
    }

    public void showResults(ArrayList<Model_Chat_Message> results) {
        results.sort((Model_Chat_Message o1, Model_Chat_Message o2) -> o2.getTime().compareTo(o1.getTime()));
        body.showResults(results);
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