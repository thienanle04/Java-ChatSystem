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

import javax.swing.JOptionPane;

import server.config.ConfigUtil;

/**
 *
 * @author Nghiax
 */
public class loginHistory extends javax.swing.JPanel {
    private String url;
    private String username;
    private String password;
    private Connection conn;

    /**
     * Creates new form loginHistory
     */
    public loginHistory() {
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        loginHistory = new javax.swing.JTable();

        loginHistory.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null }
                },
                new String[] {
                        "Timestamp", "Username", "FullName"
                }) {
            @SuppressWarnings("rawtypes")
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false
            };

            @SuppressWarnings("rawtypes")
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
                            lh.logged_in_at AS Timestamp,
                            u.username AS Username,
                            u.name AS FullName
                        FROM
                            Login_History lh
                        JOIN
                            Users u
                        ON
                            lh.user_id = u.user_id
                        ORDER BY
                            lh.logged_in_at DESC
                    """;

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Lấy model từ bảng
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) loginHistory.getModel();

            // Xóa dữ liệu cũ nếu có
            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                Object[] row = new Object[] {
                        rs.getTimestamp("Timestamp"),
                        rs.getString("Username"),
                        rs.getString("FullName")
                };
                model.addRow(row);
            }

            // Đóng kết nối
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        jScrollPane2.setViewportView(loginHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable loginHistory;
    // End of variables declaration//GEN-END:variables
}
