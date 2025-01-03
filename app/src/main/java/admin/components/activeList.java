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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
public class activeList extends javax.swing.JPanel {
    private String url;
    private String username;
    private String password;
    private Connection conn;

    /**
     * Creates new form activeList
     */
    public activeList() {
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

        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sortBy = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        ActiveList = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        startDate = new javax.swing.JTextField();
        endDate = new javax.swing.JTextField();
        filterByTime = new javax.swing.JButton();
        viewChart = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        appOpen = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        filterByAppOpen = new javax.swing.JComboBox<>();

        jLabel12.setText("Start Date (yyyy-mm-dd):");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Sort by:");

        sortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "CreationDate" }));
        sortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByActionPerformed(evt);
            }
        });

        ActiveList.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Username", "App Open", "People Chatted", "Group Chatted", "Creation Date"
                }) {
            @SuppressWarnings("rawtypes")
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.String.class
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
        filterByTimeActionPerformed(null);
        jScrollPane7.setViewportView(ActiveList);

        jLabel14.setText("End Date (yyyy-mm-dd):");

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

        filterByTime.setText("Filter by time");
        filterByTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByTimeActionPerformed(evt);
            }
        });

        viewChart.setText("View Charts");
        viewChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewChartActionPerformed(evt);
            }
        });

        jLabel17.setText("Filter by app open:");

        appOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appOpenActionPerformed(evt);
            }
        });

        jLabel18.setText("Filter by:");

        filterByAppOpen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", ">", "<" }));
        filterByAppOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterByAppOpenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel12)
                                                        .addComponent(jLabel14))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(endDate,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 130,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(startDate,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                130,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                layout.createSequentialGroup()
                                                                                        .addComponent(appOpen)
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                        .addComponent(jLabel18)))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(filterByTime)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(jLabel13)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(sortBy,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        90,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(filterByAppOpen,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        46,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))))))
                                        .addComponent(viewChart, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(0, 591, Short.MAX_VALUE))
                                        .addComponent(jScrollPane7))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(filterByTime, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sortBy, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(appOpen, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18)
                                        .addComponent(filterByAppOpen, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewChart, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private DefaultTableModel originalModel;

    private void sortByActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_sortByActionPerformed
        DefaultTableModel model = (DefaultTableModel) ActiveList.getModel();

        // Configure a TableRowSorter with custom comparators
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        ActiveList.setRowSorter(sorter);

        // Set a custom comparator for the "Creation Date" column (index 0)
        sorter.setComparator(4, (Object o1, Object o2) -> {
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
        String selectedOption = (String) sortBy.getSelectedItem();
        int columnIndex = -1;

        if ("Name".equals(selectedOption)) {
            columnIndex = 0; // Name column index
        } else if ("CreationDate".equals(selectedOption)) {
            columnIndex = 4; // Creation Date column index
        }

        // Apply sorting if a valid column is selected
        if (columnIndex != -1) {
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
            sorter.sort();
        }
    }// GEN-LAST:event_sortByActionPerformed

    private void startDateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_startDateActionPerformed
        //
    }// GEN-LAST:event_startDateActionPerformed

    private void endDateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_endDateActionPerformed
    }// GEN-LAST:event_endDateActionPerformed

    private void filterByTimeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_filterByActionPerformed
        String start = startDate.getText().trim();
        String end = endDate.getText().trim();

        try {
            LocalDate _startDate = start.isEmpty() ? LocalDate.of(1900, 1, 1) : LocalDate.parse(start);
            LocalDate _endDate = end.isEmpty() ? LocalDate.of(2030, 12, 31) : LocalDate.parse(end);

            java.sql.Date sqlStartDate = java.sql.Date.valueOf(_startDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(_endDate);
            if (originalModel == null) {
                originalModel = (DefaultTableModel) ActiveList.getModel();
            }

            try {
                // Truy vấn số lần ứng dụng mở
                String appOpenQuery = """
                        SELECT
                            subquery.user_id,
                            subquery.username AS Username,
                            COUNT(subquery.logged_in_at) AS AppOpen,  -- Count the logins for each user
                            DATE_FORMAT(MAX(subquery.logged_in_at), '%Y-%m-%d') AS LastChatDate  -- Most recent login date
                        FROM (
                            SELECT
                                u.user_id,
                                u.username,
                                l.logged_in_at,
                                ROW_NUMBER() OVER (PARTITION BY u.user_id ORDER BY l.logged_in_at DESC) AS row_num
                            FROM
                                users u
                            LEFT JOIN
                                login_history l ON u.user_id = l.user_id
                            WHERE
                                l.logged_in_at BETWEEN ? AND ?  -- Date range for filtering logins
                        ) AS subquery
                        GROUP BY
                            subquery.user_id, subquery.username;  -- Group by user_id to count logins per user

                        """;

                // Truy vấn số nhóm đã trò chuyện
                String groupChattedQuery = """
                            SELECT
                                gm.sender_id AS UserID,
                                COUNT(DISTINCT gm.group_id) AS GroupChatted
                            FROM
                                group_messages gm
                            JOIN
                                chat_group cg ON gm.group_id = cg.group_id
                            WHERE
                                cg.group_type != 2
                                AND gm.sent_at BETWEEN ? AND ?
                            GROUP BY
                                gm.sender_id;
                        """;

                // Truy vấn số người đã trò chuyện
                String peopleChattedQuery = """
                            SELECT
                                    gm.sender_id AS UserID,
                                    COUNT(DISTINCT gm_other.user_id) AS PeopleChatted
                                FROM
                                    group_messages gm
                                JOIN
                                    group_members gm_other
                                    ON gm.group_id = gm_other.group_id
                                    AND gm.sender_id != gm_other.user_id
                                WHERE
                                    gm.sent_at BETWEEN ? AND ?
                                GROUP BY
                                    gm.sender_id;
                        """;

                // Thực thi truy vấn
                PreparedStatement appOpenStmt = conn.prepareStatement(appOpenQuery);
                appOpenStmt.setDate(1, sqlStartDate);
                appOpenStmt.setDate(2, sqlEndDate);

                PreparedStatement groupChattedStmt = conn.prepareStatement(groupChattedQuery);
                groupChattedStmt.setDate(1, sqlStartDate);
                groupChattedStmt.setDate(2, sqlEndDate);

                PreparedStatement peopleChattedStmt = conn.prepareStatement(peopleChattedQuery);
                peopleChattedStmt.setDate(1, sqlStartDate);
                peopleChattedStmt.setDate(2, sqlEndDate);

                ResultSet appOpenRs = appOpenStmt.executeQuery();
                ResultSet groupChattedRs = groupChattedStmt.executeQuery();
                ResultSet peopleChattedRs = peopleChattedStmt.executeQuery();

                // Lưu kết quả GroupChatted và PeopleChatted vào Map
                Map<Integer, Integer> groupChattedMap = new HashMap<>();
                Map<Integer, Integer> peopleChattedMap = new HashMap<>();

                while (groupChattedRs.next()) {
                    groupChattedMap.put(groupChattedRs.getInt("UserID"), groupChattedRs.getInt("GroupChatted"));
                }

                while (peopleChattedRs.next()) {
                    peopleChattedMap.put(peopleChattedRs.getInt("UserID"), peopleChattedRs.getInt("PeopleChatted"));
                }

                // Lấy model từ bảng
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ActiveList
                        .getModel();

                // Xóa dữ liệu cũ nếu có
                model.setRowCount(0);

                // Thêm dữ liệu từ ResultSet appOpen vào bảng
                while (appOpenRs.next()) {
                    int userId = appOpenRs.getInt("user_id");
                    String username = appOpenRs.getString("Username");
                    int appOpen = appOpenRs.getInt("AppOpen");
                    Timestamp lastChatDate = appOpenRs.getTimestamp("LastChatDate");

                    int groupChatted = groupChattedMap.getOrDefault(userId, 0);
                    int peopleChatted = peopleChattedMap.getOrDefault(userId, 0);

                    Object[] row = new Object[] {
                            username,
                            appOpen,
                            peopleChatted,
                            groupChatted,
                            lastChatDate
                    };
                    model.addRow(row);
                }

                // Đóng kết nối
                appOpenRs.close();
                groupChattedRs.close();
                peopleChattedRs.close();
                appOpenStmt.close();
                groupChattedStmt.close();
                peopleChattedStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter dates in the format yyyy-MM-dd.",
                    "Invalid Date Format",
                    JOptionPane.ERROR_MESSAGE);
        }

    }// GEN-LAST:event_filterByActionPerformed

    private void viewChartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_viewChartActionPerformed
        String inputYear = JOptionPane.showInputDialog(null, "Enter the year to view data (yyyy):", "Enter Year",
                JOptionPane.QUESTION_MESSAGE);
        if (inputYear != null && !inputYear.trim().isEmpty()) {
            try {
                int year = Integer.parseInt(inputYear.trim());
                // Query data
                String query = """
                            WITH MonthList AS (
                                SELECT 1 AS Month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION
                                SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION
                                SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
                            )
                            SELECT
                                ml.Month AS Month,
                                COALESCE(COUNT(DISTINCT lh.user_id), 0) AS UserCount
                            FROM
                                MonthList ml
                            LEFT JOIN login_history lh ON MONTH(lh.logged_in_at) = ml.Month
                                AND YEAR(lh.logged_in_at) = ?
                            GROUP BY
                                ml.Month
                            ORDER BY
                                ml.Month
                        """;

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, year);
                ResultSet rs = stmt.executeQuery();

                // Prepare data for the chart
                org.jfree.data.category.DefaultCategoryDataset dataset = new org.jfree.data.category.DefaultCategoryDataset();
                while (rs.next()) {
                    int month = rs.getInt("Month");
                    int userCount = rs.getInt("UserCount");
                    dataset.addValue(userCount, "Users", "M" + month);
                }

                // Create bar chart
                org.jfree.chart.JFreeChart barChart = org.jfree.chart.ChartFactory.createBarChart(
                        "App Usage Chart for Year " + year,
                        "Month",
                        "Number of Users",
                        dataset);

                // Display the chart
                org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(barChart);
                javax.swing.JDialog chartDialog = new javax.swing.JDialog((java.awt.Frame) null, "Statistics Chart",
                        true);
                chartDialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
                chartDialog.setContentPane(chartPanel);
                chartDialog.setSize(800, 600);
                chartDialog.setLocationRelativeTo(null);
                chartDialog.setVisible(true);

                // Close connections
                rs.close();
                stmt.close();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid year format!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have not entered a year!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_viewChartActionPerformed

    private void appOpenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_appOpenActionPerformed
        //
        String directFriend = appOpen.getText().trim();
        String _filterBy = (String) filterByAppOpen.getSelectedItem();// =, > , <
        if (originalModel == null) {
            originalModel = (DefaultTableModel) ActiveList.getModel();
        }
        if (!directFriend.isEmpty()) {
            try {
                int directFriendValue = Integer.parseInt(directFriend);

                DefaultTableModel model = originalModel;

                DefaultTableModel filteredModel = new DefaultTableModel();

                for (int i = 0; i < model.getColumnCount(); i++) {
                    filteredModel.addColumn(model.getColumnName(i));
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    int friendsCount = Integer.parseInt(model.getValueAt(i, 1).toString());

                    boolean matches = false;
                    switch (_filterBy) {
                        case "=" -> matches = friendsCount == directFriendValue;
                        case ">" -> matches = friendsCount > directFriendValue;
                        case "<" -> matches = friendsCount < directFriendValue;
                    }

                    if (matches) {
                        Object[] rowData = new Object[model.getColumnCount()];
                        for (int j = 0; j < model.getColumnCount(); j++) {
                            rowData[j] = model.getValueAt(i, j);
                        }
                        filteredModel.addRow(rowData);
                    }
                }

                ActiveList.setModel(filteredModel);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ActiveList.setModel(originalModel);
        }
    }// GEN-LAST:event_appOpenActionPerformed

    private void filterByAppOpenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_filterByAppOpenActionPerformed
        appOpenActionPerformed(evt);
    }// GEN-LAST:event_filterByAppOpenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ActiveList;
    private javax.swing.JTextField appOpen;
    private javax.swing.JTextField endDate;
    private javax.swing.JComboBox<String> filterByAppOpen;
    private javax.swing.JButton filterByTime;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JComboBox<String> sortBy;
    private javax.swing.JTextField startDate;
    private javax.swing.JButton viewChart;
    // End of variables declaration//GEN-END:variables
}
