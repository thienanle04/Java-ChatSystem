package user.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import user.event.PublicEvent;
import user.model.Model_Friend_Request;
import user.model.Model_Group_Chat;
import user.service.Service;


@SuppressWarnings("unused")
public class Message_Search_Bar extends javax.swing.JPanel {
    private Model_Group_Chat chat;

    public Model_Group_Chat getChat() {
        return chat;
    }

    public void setChat(Model_Group_Chat chat) {
        this.chat = chat;
    }

    public Message_Search_Bar() {
        initComponents();
        cmdSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+3;");
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        navigator = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(229, 229, 229));
        setPreferredSize(new java.awt.Dimension(504, 35));
        setRequestFocusEnabled(false);

        txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActionPerformed(evt);
            }
        });

        cmdSearch.setText("Search");
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        navigator.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        navigator.setEnabled(false);
        navigator.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                navigatorStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cmdSearch)
                .addGap(3, 3, 3)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(navigator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(navigator, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        String searchText = txt.getText();
        if (searchText.isEmpty()) {
            PublicEvent.getInstance().getEventChat().cancelSearch();
            navigator.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1)); // No matches
            navigator.setEnabled(false);
            return;
        }
    
        int matchCount = PublicEvent.getInstance().getEventChat().searchMessage(chat.getGroupId(), searchText);
        if (matchCount > 0) {
            navigator.setModel(new javax.swing.SpinnerNumberModel(1, 1, matchCount, 1) {
                @Override
                public Object getNextValue() {
                    // Return the "previous" value to reverse behavior
                    return super.getPreviousValue();
                }

                @Override
                public Object getPreviousValue() {
                    // Return the "next" value to reverse behavior
                    return super.getNextValue();
                }
            }); // From 1 to matchCount
            navigator.setEnabled(true);
            navigator.setValue(matchCount);
        } else {
            navigator.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1)); // No matches
            navigator.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No matches found.");
        }
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActionPerformed
        String searchText = txt.getText();
        if (searchText.isEmpty()) {
            PublicEvent.getInstance().getEventChat().cancelSearch();
            navigator.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1)); // No matches
            navigator.setEnabled(false);
            return;
        }
    
        int matchCount = PublicEvent.getInstance().getEventChat().searchMessage(chat.getGroupId(), searchText);
        if (matchCount > 0) {
            navigator.setModel(new javax.swing.SpinnerNumberModel(1, 1, matchCount, 1) {
                @Override
                public Object getNextValue() {
                    // Return the "previous" value to reverse behavior
                    return super.getPreviousValue();
                }

                @Override
                public Object getPreviousValue() {
                    // Return the "next" value to reverse behavior
                    return super.getNextValue();
                }
            }); // From 1 to matchCount
            navigator.setEnabled(true);
            navigator.setValue(matchCount);
        } else {
            navigator.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1)); // No matches
            navigator.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No matches found.");
        }
    }//GEN-LAST:event_txtActionPerformed

    private void navigatorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_navigatorStateChanged
        if (navigator.isEnabled()) {
            int selectedIndex = (int) navigator.getValue() - 1; // Convert 1-based index to 0-based
            PublicEvent.getInstance().getEventChat().navigateToMatch(selectedIndex);
        }
    }//GEN-LAST:event_navigatorStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSearch;
    private javax.swing.JSpinner navigator;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}