/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package admin.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import server.config.ConfigUtil;

/**
 *
 * @author Nghiax
 */
public class spamReports extends javax.swing.JPanel {
    private String url;
    private String username;
    private String password;
    private Connection conn;

    /**
     * Creates new form spamReports
     */
    public spamReports() {
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        SpamReports = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        FilterByName = new javax.swing.JTextField();
        lockAccount = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        SortBy = new javax.swing.JComboBox<>();
        FilterByTimeStamp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        reportedEmail = new javax.swing.JTextField();

        SpamReports.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Timestamp", "ReportBy", "Username", "Reported Email", "Lock"
                }) {
            @SuppressWarnings("rawtypes")
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            @SuppressWarnings("rawtypes")
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        reloadSpamReports();
        jScrollPane4.setViewportView(SpamReports);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Filter by name:");

        FilterByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterByNameActionPerformed(evt);
            }
        });

        lockAccount.setText("Lock Account");
        lockAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockAccountActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Sort by:");

        SortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Timestamp" }));
        SortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortByActionPerformed(evt);
            }
        });

        FilterByTimeStamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterByTimeStampActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Filter by timestamp:");

        jLabel1.setText("Filter by Email");

        reportedEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportedEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(lockAccount))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(FilterByName, javax.swing.GroupLayout.PREFERRED_SIZE, 137,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11)
                                                .addGap(18, 18, 18)
                                                .addComponent(FilterByTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(reportedEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 139,
                                                        Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SortBy, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel11)
                                                .addComponent(FilterByTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1)
                                                .addComponent(reportedEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel10)
                                                .addComponent(SortBy, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel3)
                                                .addComponent(FilterByName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lockAccount)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void reportedEmailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_reportedEmailActionPerformed
        String filterName = reportedEmail.getText().trim();

        if (!filterName.isEmpty()) {
            if (originalModel == null) {
                originalModel = (DefaultTableModel) SpamReports.getModel();
            }

            DefaultTableModel model = originalModel;

            DefaultTableModel filteredModel = new DefaultTableModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                filteredModel.addColumn(model.getColumnName(i));
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                String email = model.getValueAt(i, 3).toString();
                if (email.toLowerCase().contains(filterName.toLowerCase())) {
                    filteredModel.addRow(new Object[] {
                            model.getValueAt(i, 0),
                            model.getValueAt(i, 1),
                            model.getValueAt(i, 2),
                            model.getValueAt(i, 3),
                            model.getValueAt(i, 4),
                    });
                }
            }

            SpamReports.setModel(filteredModel);
        } else {
            SpamReports.setModel(originalModel);
        }
    }// GEN-LAST:event_reportedEmailActionPerformed

    private void reloadSpamReports() {
        try {
            // Truy vấn dữ liệu (đã thêm cột is_locked)
            String query = """
                        SELECT
                            s.report_at AS Timestamp,
                            ur.username AS ReportBy,
                            u.username AS Username,
                            u.email As Email,
                            u.is_locked AS IsLocked
                        FROM
                            spam_list s
                        JOIN
                            users u ON s.report_user = u.user_id
                        JOIN
                            users ur ON s.report_by = ur.user_id
                        ORDER BY
                            s.report_at DESC
                    """;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Lấy model từ bảng SpamReports
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) SpamReports.getModel();

            // Xóa dữ liệu cũ nếu có
            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                Object[] row = new Object[] {
                        rs.getTimestamp("Timestamp"), // Lấy giá trị từ cột report_at
                        rs.getString("ReportBy"), // Lấy giá trị từ cột report_by (tên người báo cáo)
                        rs.getString("Username"), // Lấy giá trị từ cột username (tên người bị báo cáo)
                        rs.getString("Email"),
                        rs.getBoolean("IsLocked") // Lấy giá trị từ cột is_locked
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
    }

    private DefaultTableModel originalModel;

    private void FilterByNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_FilterByNameActionPerformed
        String filterName = FilterByName.getText().trim();

        // Ensure the original model is available
        if (originalModel == null) {
            originalModel = (DefaultTableModel) SpamReports.getModel();
        }

        DefaultTableModel model = originalModel;

        // Create a new model to hold the filtered data
        DefaultTableModel filteredModel = new DefaultTableModel();

        // Add columns from the original model to the filtered model
        for (int i = 0; i < model.getColumnCount(); i++) {
            filteredModel.addColumn(model.getColumnName(i));
        }

        // Loop through the rows of the original model and filter by both Name and
        // Username
        for (int i = 0; i < model.getRowCount(); i++) {
            String name = model.getValueAt(i, 2).toString(); // Assuming the name is in the first column

            boolean matchesName = filterName.isEmpty() || name.toLowerCase().contains(filterName.toLowerCase());

            // Add the row to filtered model if both conditions are met
            if (matchesName) {
                filteredModel.addRow(new Object[] {
                        model.getValueAt(i, 0),
                        model.getValueAt(i, 1),
                        model.getValueAt(i, 2),
                        model.getValueAt(i, 3),
                        model.getValueAt(i, 4),
                });
            }
        }

        // Set the filtered model to the table
        SpamReports.setModel(filteredModel);

    }// GEN-LAST:event_FilterByNameActionPerformed

    private void lockAccountActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_lockAccountActionPerformed
        DefaultTableModel model = (DefaultTableModel) SpamReports.getModel();

        int selectedRow = SpamReports.convertRowIndexToModel(SpamReports.getSelectedRow());

        if (selectedRow != -1) {
            Boolean lock = (Boolean) model.getValueAt(selectedRow, 4);
            Boolean newLock = !lock;
            // Get the username for database identification
            String username = model.getValueAt(selectedRow, 2).toString();

            // Update the database

            String sql = "UPDATE Users SET is_locked = ? WHERE username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setBoolean(1, newLock); // Set the new lock value
                pstmt.setString(2, username); // Identify the user by username
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    reloadSpamReports();
                    System.out.println("Database updated successfully for user: " + username);
                } else {
                    System.out.println("No rows updated for user: " + username);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating database: " + e.getMessage());
            }
        } else {
            System.out.println("No row selected.");
        }
    }// GEN-LAST:event_lockAccountActionPerformed

    private void FilterByTimeStampActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_FilterByTimeStampActionPerformed
        String filterTimestamp = FilterByTimeStamp.getText().trim();

        if (!filterTimestamp.isEmpty()) {
            if (originalModel == null) {
                originalModel = (DefaultTableModel) SpamReports.getModel();
            }

            DefaultTableModel model = originalModel;

            DefaultTableModel filteredModel = new DefaultTableModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                filteredModel.addColumn(model.getColumnName(i));
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                String timestamp = model.getValueAt(i, 0).toString();
                if (timestamp.toLowerCase().contains(filterTimestamp.toLowerCase())) {
                    filteredModel.addRow(new Object[] {
                            model.getValueAt(i, 0),
                            model.getValueAt(i, 1),
                            model.getValueAt(i, 2),
                            model.getValueAt(i, 3),
                            model.getValueAt(i, 4),
                    });
                }
            }

            SpamReports.setModel(filteredModel);
        } else {
            SpamReports.setModel(originalModel);
        }
    }// GEN-LAST:event_FilterByTimeStampActionPerformed

    private void SortByActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SortByActionPerformed
        DefaultTableModel model = (DefaultTableModel) SpamReports.getModel();

        // Configure a TableRowSorter with custom comparators
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        SpamReports.setRowSorter(sorter);

        // Set a custom comparator for the "Creation Date" column (index 10)
        sorter.setComparator(0, (Object o1, Object o2) -> {
            try {
                java.sql.Timestamp ts1 = (java.sql.Timestamp) o1;
                java.sql.Timestamp ts2 = (java.sql.Timestamp) o2;
                return ts1.compareTo(ts2); // Compare timestamps directly
            } catch (ClassCastException ex) {
                Logger.getLogger(userDetail.class.getName()).log(Level.SEVERE, null, ex);
                return 0; // Treat errors as equal
            }
        });

        // Determine which column to sort based on the selected option
        String selectedOption = (String) SortBy.getSelectedItem();
        int columnIndex = -1;

        if ("Name".equals(selectedOption)) {
            columnIndex = 2; // Name column index
        } else if ("Timestamp".equals(selectedOption)) {
            columnIndex = 0; // Creation Date column index
        }

        // Apply sorting if a valid column is selected
        if (columnIndex != -1) {
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
            sorter.sort();
        }
    }// GEN-LAST:event_SortByActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FilterByName;
    private javax.swing.JTextField FilterByTimeStamp;
    private javax.swing.JComboBox<String> SortBy;
    private javax.swing.JTable SpamReports;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton lockAccount;
    private javax.swing.JTextField reportedEmail;
    // End of variables declaration//GEN-END:variables
}
