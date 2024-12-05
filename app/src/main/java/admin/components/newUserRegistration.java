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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Nghiax
 */
public class newUserRegistration extends javax.swing.JPanel {

    /**
     * Creates new form newUserRegistration
     */
    public newUserRegistration() {
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

        jScrollPane5 = new javax.swing.JScrollPane();
        NewUserRegistation = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        SortBy = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        startDate = new javax.swing.JTextField();
        endDate = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        FilterByTime = new javax.swing.JButton();
        filterByName = new javax.swing.JTextField();

        NewUserRegistation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Creation Date", "Username", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        try {
            // Kết nối đến database
            String url = "jdbc:mysql://localhost:3306/chatsystem?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "admin";
            String password = "*Nghia1692004"; // Thay bằng mật khẩu của bạn
            Connection conn = DriverManager.getConnection(url, user, password);

            // Truy vấn dữ liệu
            String query = """
                SELECT 
                    DATE_FORMAT(created_at, '%Y-%m-%d') AS CreationDate,
                    username, 
                    password_hash 
                FROM 
                    users
            """;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Lấy model từ bảng
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) NewUserRegistation.getModel();

            // Xóa dữ liệu cũ nếu có
            model.setRowCount(0);

            // Thêm dữ liệu từ ResultSet vào bảng
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("CreationDate"),
                    rs.getString("username"),
                    rs.getString("password_hash")
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

        jScrollPane5.setViewportView(NewUserRegistation);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Sort by:");

        SortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "CreationDate" }));
        SortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortByActionPerformed(evt);
            }
        });

        jLabel9.setText("Filter by name:");

        jButton4.setText("View Charts");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel15.setText("Start Date (yyyy-mm-dd):");

        startDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDateActionPerformed(evt);
            }
        });

        endDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateActionPerformed(evt);
            }
        });

        jLabel16.setText("End Date (yyyy-mm-dd):");

        FilterByTime.setText("Filter by time");
        FilterByTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterByTimeActionPerformed(evt);
            }
        });

        filterByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(FilterByTime)))
                                .addGap(147, 147, 147)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(filterByName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(SortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FilterByTime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(filterByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String inputYear = JOptionPane.showInputDialog(null, "Nhập năm cần xem dữ liệu (yyyy):", "Nhập Năm", JOptionPane.QUESTION_MESSAGE);
        if (inputYear != null && !inputYear.trim().isEmpty()) {
            try {
                int year = Integer.parseInt(inputYear.trim());

                // Kết nối đến database
                String url = "jdbc:mysql://localhost:3306/chatsystem?zeroDateTimeBehavior=CONVERT_TO_NULL";
                String user = "admin";
                String password = "*Nghia1692004"; // Thay bằng mật khẩu của bạn
                Connection conn = DriverManager.getConnection(url, user, password);

                // Truy vấn dữ liệu
                String query = """
                    SELECT 
                        MONTH(created_at) AS Month, 
                        COUNT(*) AS UserCount 
                    FROM 
                        users 
                    WHERE 
                        YEAR(created_at) = ?
                    GROUP BY 
                        MONTH(created_at)
                    ORDER BY 
                        MONTH(created_at)
                """;
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, year);
                ResultSet rs = stmt.executeQuery();

                // Chuẩn bị dữ liệu cho biểu đồ
                org.jfree.data.category.DefaultCategoryDataset dataset = new org.jfree.data.category.DefaultCategoryDataset();
                while (rs.next()) {
                    int month = rs.getInt("Month");
                    int userCount = rs.getInt("UserCount");
                    dataset.addValue(userCount, "Số lượng", "Tháng " + month);
                }

                // Tạo biểu đồ cột
                org.jfree.chart.JFreeChart barChart = org.jfree.chart.ChartFactory.createBarChart(
                        "Biểu đồ số lượng người đăng ký năm " + year,
                        "Tháng",
                        "Số lượng",
                        dataset
                );

                // Hiển thị biểu đồ
                org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(barChart);

                javax.swing.JDialog chartDialog = new javax.swing.JDialog((java.awt.Frame) null, "View Chart", true);
                chartDialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
                chartDialog.setContentPane(chartPanel);
                chartDialog.setSize(800, 600);
                chartDialog.setLocationRelativeTo(this);

                chartDialog.setVisible(true);

                // Đóng kết nối
                rs.close();
                stmt.close();
                conn.close();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Năm nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập năm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    private DefaultTableModel originalModel;
    private void startDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDateActionPerformed

    private void endDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateActionPerformed

    private void FilterByTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterByTimeActionPerformed
        // TODO add your handling code here:
        String start = startDate.getText().trim();
        String end = endDate.getText().trim();

        if (!start.isEmpty() || !end.isEmpty()) {
            try {
                LocalDate _startDate = start.isEmpty() ? LocalDate.MIN : LocalDate.parse(start);
                LocalDate _endDate = end.isEmpty() ? LocalDate.MAX : LocalDate.parse(end);

                if (originalModel == null) {
                    originalModel = (DefaultTableModel) NewUserRegistation.getModel();
                }

                DefaultTableModel model = originalModel;

                DefaultTableModel filteredModel = new DefaultTableModel();

                for (int i = 0; i < model.getColumnCount(); i++) {
                    filteredModel.addColumn(model.getColumnName(i));
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    String dateStr = model.getValueAt(i, 0).toString(); 
                    LocalDate creationDate = LocalDate.parse(dateStr);

                    if ((creationDate.isEqual(_startDate) || creationDate.isAfter(_startDate)) &&
                        (creationDate.isEqual(_endDate) || creationDate.isBefore(_endDate))) {
                        filteredModel.addRow(new Object[] {
                            model.getValueAt(i, 0), 
                            model.getValueAt(i, 1), 
                            model.getValueAt(i, 2), 
                        });
                    }
                }

                NewUserRegistation.setModel(filteredModel);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter dates in the format yyyy-MM-dd.", 
                    "Invalid Date Format", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            NewUserRegistation.setModel(originalModel);
        }
    }//GEN-LAST:event_FilterByTimeActionPerformed

    private void filterByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterByNameActionPerformed
        // TODO add your handling code here:
        String filterName = filterByName.getText().trim();

        if (!filterName.isEmpty()) {
            if (originalModel == null) {
                originalModel = (DefaultTableModel) NewUserRegistation.getModel();
            }

            DefaultTableModel model = originalModel;

            DefaultTableModel filteredModel = new DefaultTableModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                filteredModel.addColumn(model.getColumnName(i));
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                String name = model.getValueAt(i, 1).toString(); // Assuming Name is in column 0
                if (name.toLowerCase().contains(filterName.toLowerCase())) {
                    filteredModel.addRow(new Object[] {
                        model.getValueAt(i, 0), 
                        model.getValueAt(i, 1), 
                        model.getValueAt(i, 2)
                    });
                }
            }

            NewUserRegistation.setModel(filteredModel);
        } else {
            NewUserRegistation.setModel(originalModel);
        }
    }//GEN-LAST:event_filterByNameActionPerformed

    private void SortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortByActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) NewUserRegistation.getModel();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        NewUserRegistation.setRowSorter(sorter);

        String selectedOption = (String) SortBy.getSelectedItem();
        int columnIndex = -1;

        if ("Name".equals(selectedOption)) {
            columnIndex = 1; 
        } else if ("Creation Date".equals(selectedOption)) {
            columnIndex = 0; 
        }

        if (columnIndex != -1) {
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
            sorter.sort(); 
        }
    }//GEN-LAST:event_SortByActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FilterByTime;
    private javax.swing.JTable NewUserRegistation;
    private javax.swing.JComboBox<String> SortBy;
    private javax.swing.JTextField endDate;
    private javax.swing.JTextField filterByName;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField startDate;
    // End of variables declaration//GEN-END:variables
}
