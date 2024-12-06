/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package admin.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Nghiax
 */
public class groupList extends javax.swing.JPanel {

    /**
     * Creates new form groupList
     */
    public groupList() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        GroupList = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        filterByName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SortBy = new javax.swing.JComboBox<>();
        viewMember = new javax.swing.JButton();
        viewAdmin = new javax.swing.JButton();

        GroupList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Group B", "11-11-2024"},
                {"Group A", "12-11-2024"}
            },
            new String [] {
                "Group Name", "Creation Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(GroupList);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Filter by name:");

        filterByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByNameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Sort by:");

        SortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "CreationDate" }));
        SortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortByActionPerformed(evt);
            }
        });

        viewMember.setText("View Member");
        viewMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMemberActionPerformed(evt);
            }
        });

        viewAdmin.setText("View Admin");
        viewAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(filterByName, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(viewMember)
                        .addGap(35, 35, 35)
                        .addComponent(viewAdmin)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SortBy, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(filterByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewAdmin)
                    .addComponent(viewMember)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private DefaultTableModel originalModel;
    private void filterByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterByNameActionPerformed
        // TODO add your handling code here:
        String filterName = filterByName.getText().trim();

        if (!filterName.isEmpty()) {
            if (originalModel == null) {
                originalModel = (DefaultTableModel) GroupList.getModel();
            }

            DefaultTableModel model = originalModel;

            DefaultTableModel filteredModel = new DefaultTableModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                filteredModel.addColumn(model.getColumnName(i));
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                String name = model.getValueAt(i, 0).toString(); 
                if (name.toLowerCase().contains(filterName.toLowerCase())) {
                    filteredModel.addRow(new Object[] {
                        model.getValueAt(i, 0), 
                        model.getValueAt(i, 1),
                    });
                }
            }

            GroupList.setModel(filteredModel);
        } else {
            GroupList.setModel(originalModel);
        }
    }//GEN-LAST:event_filterByNameActionPerformed

    private void viewMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMemberActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) GroupList.getModel();

        // Get the selected row
        int selectedRow = GroupList.getSelectedRow();

        if (selectedRow != -1) {
            String groupName = (String) model.getValueAt(selectedRow, 0);

            String[][] members = getGroupMembers(groupName); // Replace with actual data

            String[] columnNames = {"Member Name", "Role"};

            JTable membersTable = new JTable(members, columnNames);
            membersTable.setFillsViewportHeight(true); 
            membersTable.setRowHeight(30); 

            JScrollPane scrollPane = new JScrollPane(membersTable);
            scrollPane.setPreferredSize(new Dimension(400, 200)); 

            JDialog dialog = new JDialog((JFrame) null, "List Members of Group: " + groupName, true);
            dialog.setLayout(new BorderLayout());
            dialog.add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dialog.dispose());
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setSize(450, 300);
            dialog.setLocationRelativeTo(this); 
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a group to view its members.", 
                "No Group Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_viewMemberActionPerformed

    private String[][] getGroupMembers(String groupName) {
        return switch (groupName) {
            case "Group A" -> new String[][] {
                {"Bob", "Member"},
                {"Charlie", "Member"}
            };
            case "Group B" -> new String[][] {
                {"Eva", "Member"}
            };
            default -> new String[][] {
                {"No members found", ""}
            };
        };
    }
    private void viewAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAdminActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) GroupList.getModel();

        int selectedRow = GroupList.getSelectedRow();

        if (selectedRow != -1) {
            String groupName = (String) model.getValueAt(selectedRow, 0);

            String[][] members = getGroupAdmins(groupName); 
            
            String[] columnNames = {"Member Name", "Role"};

            JTable membersTable = new JTable(members, columnNames);
            membersTable.setFillsViewportHeight(true); 
            membersTable.setRowHeight(30); 

            JScrollPane scrollPane = new JScrollPane(membersTable);
            scrollPane.setPreferredSize(new Dimension(400, 200)); 

            JDialog dialog = new JDialog((JFrame) null, "List Admins of Group: " + groupName, true);
            dialog.setLayout(new BorderLayout());
            dialog.add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dialog.dispose());
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setSize(450, 300);
            dialog.setLocationRelativeTo(this); 
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a group to view its members.", 
                "No Group Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_viewAdminActionPerformed
    
    private void SortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortByActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) GroupList.getModel();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        GroupList.setRowSorter(sorter);

        String selectedOption = (String) SortBy.getSelectedItem();
        int columnIndex = -1;

        if ("Name".equals(selectedOption)) {
            columnIndex = 0; 
        } else if ("CreationDate".equals(selectedOption)) {
            columnIndex = 1; 
        }

        if (columnIndex != -1) {
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
            sorter.sort();
        }
    }//GEN-LAST:event_SortByActionPerformed

    private String[][] getGroupAdmins(String groupName) {
        return switch (groupName) {
            case "Group A" -> new String[][] {
                {"Alice", "Admin"},
            };
            case "Group B" -> new String[][] {
                {"David", "Admin"},
            };
            default -> new String[][] {
                {"No members found", ""}
            };
        };
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GroupList;
    private javax.swing.JComboBox<String> SortBy;
    private javax.swing.JTextField filterByName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton viewAdmin;
    private javax.swing.JButton viewMember;
    // End of variables declaration//GEN-END:variables
}