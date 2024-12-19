/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package admin.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
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

import server.config.ConfigUtil;

/**
 *
 * @author Nghiax
 */
public class groupList extends javax.swing.JPanel {
    private String url;
    private String username;
    private String password;
    private Connection conn;

    /**
     * Creates new form groupList
     */
    public groupList() {
        try {
            // Create an instance of ConfigUtil
            ConfigUtil configUtil = new ConfigUtil();
            // Access configuration values
            url = configUtil.getString("url");
            username = configUtil.getString("username");
            password = configUtil.getString("password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
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
                new Object[][] {

                },
                new String[] {
                        "Group Name", "Creation Date"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        try {
            // Truy vấn dữ liệu
            String query = """
                        SELECT
                            group_name AS GroupName,
                            created_at AS CreationDate
                        FROM
                            chat_group
                        WHERE
                            group_type != 2
                    """;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Lấy model từ bảng
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) GroupList.getModel();

            // Xóa dữ liệu cũ nếu có
            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                Object[] row = new Object[] {
                        rs.getString("GroupName"),
                        rs.getTimestamp("CreationDate")
                };
                model.addRow(row);
            }

            // Đóng kết nối
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(filterByName, javax.swing.GroupLayout.PREFERRED_SIZE, 205,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SortBy, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(viewMember)
                                                        .addGap(35, 35, 35)
                                                        .addComponent(viewAdmin)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(filterByName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2))
                                        .addComponent(SortBy))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(viewAdmin)
                                        .addComponent(viewMember))));
    }// </editor-fold>//GEN-END:initComponents

    private DefaultTableModel originalModel;

    private void filterByNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_filterByNameActionPerformed
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
    }// GEN-LAST:event_filterByNameActionPerformed

    private void viewMemberActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_viewMemberActionPerformed
        DefaultTableModel model = (DefaultTableModel) GroupList.getModel();

        // Get the selected row
        int selectedRow = GroupList.getSelectedRow();

        if (selectedRow != -1) {
            String groupName = (String) model.getValueAt(selectedRow, 0);

            try {
                // Truy vấn dữ liệu
                String query = """
                            SELECT
                                u.username AS Username,
                                u.name AS MemberName,
                                gm.role AS Role
                            FROM
                                group_members gm
                            JOIN
                                chat_group cg
                            ON
                                gm.group_id = cg.group_id
                            JOIN
                                users u
                            ON
                                gm.user_id = u.user_id
                            WHERE
                                cg.group_name = ? AND gm.role = 'member'
                        """;
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, groupName);
                ResultSet rs = stmt.executeQuery();

                // Chuẩn bị dữ liệu cho JTable
                java.util.List<Object[]> data = new java.util.ArrayList<>();
                while (rs.next()) {
                    data.add(new Object[] { rs.getString("Username"), rs.getString("MemberName"),
                            rs.getString("Role") });
                }

                Object[][] members = data.toArray(new Object[0][]);
                String[] columnNames = { "Username", "Member Name", "Role" };

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

                // Đóng kết nối
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a group to view its members.",
                    "No Group Selected", JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_viewMemberActionPerformed

    private String[][] getGroupMembers(String groupName) {
        return switch (groupName) {
            case "Group A" -> new String[][] {
                    { "Bob", "Member" },
                    { "Charlie", "Member" }
            };
            case "Group B" -> new String[][] {
                    { "Eva", "Member" }
            };
            default -> new String[][] {
                    { "No members found", "" }
            };
        };
    }

    private void viewAdminActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_viewAdminActionPerformed
        DefaultTableModel model = (DefaultTableModel) GroupList.getModel();

        // Get the selected row
        int selectedRow = GroupList.getSelectedRow();

        if (selectedRow != -1) {
            String groupName = (String) model.getValueAt(selectedRow, 0);

            try {
                // Truy vấn dữ liệu
                String query = """
                            SELECT
                                u.username AS Username,
                                u.name AS MemberName,
                                gm.role AS Role
                            FROM
                                group_members gm
                            JOIN
                                chat_group cg
                            ON
                                gm.group_id = cg.group_id
                            JOIN
                                users u
                            ON
                                gm.user_id = u.user_id
                            WHERE
                                cg.group_name = ? AND gm.role = 'admin'
                        """;
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, groupName);
                ResultSet rs = stmt.executeQuery();

                // Chuẩn bị dữ liệu cho JTable
                java.util.List<Object[]> data = new java.util.ArrayList<>();
                while (rs.next()) {
                    data.add(new Object[] { rs.getString("Username"), rs.getString("MemberName"),
                            rs.getString("Role") });
                }

                Object[][] members = data.toArray(new Object[0][]);
                String[] columnNames = { "Username", "Member Name", "Role" };

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

                // Đóng kết nối
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a group to view its members.",
                    "No Group Selected", JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_viewAdminActionPerformed

    private void SortByActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SortByActionPerformed
        DefaultTableModel model = (DefaultTableModel) GroupList.getModel();

        // Configure a TableRowSorter with custom comparators
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        GroupList.setRowSorter(sorter);

        // Set a custom comparator for the "Creation Date" column
        sorter.setComparator(1, (Object o1, Object o2) -> {
            try {
                // Cast the objects to Timestamp
                java.sql.Timestamp ts1 = (java.sql.Timestamp) o1;
                java.sql.Timestamp ts2 = (java.sql.Timestamp) o2;

                // Compare the Timestamps directly
                return ts1.compareTo(ts2);
            } catch (ClassCastException ex) {
                Logger.getLogger(userDetail.class.getName()).log(Level.SEVERE, null, ex);
                return 0; // Treat any errors (like ClassCastException) as equal
            }
        });

        // Determine which column to sort
        String selectedOption = (String) SortBy.getSelectedItem();
        int columnIndex = -1;

        if ("Name".equals(selectedOption)) {
            columnIndex = 0;
        } else if ("CreationDate".equals(selectedOption)) {
            columnIndex = 1;
        }

        // Apply sorting if a valid column is selected
        if (columnIndex != -1) {
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
            sorter.sort();
        }
    }// GEN-LAST:event_SortByActionPerformed

    private String[][] getGroupAdmins(String groupName) {
        return switch (groupName) {
            case "Group A" -> new String[][] {
                    { "Alice", "Admin" },
            };
            case "Group B" -> new String[][] {
                    { "David", "Admin" },
            };
            default -> new String[][] {
                    { "No members found", "" }
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
