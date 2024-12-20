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

import user.config.ConfigUtil;
import user.event.PublicEvent;
import user.model.Model_Group_Chat;
import user.service.Service;

public class Friend_List_Search_Bar extends javax.swing.JPanel {
    private String Url;
    private String Username;
    private String Password;

    public Friend_List_Search_Bar() {
        try {
            // Create an instance of ConfigUtil
            ConfigUtil configUtil = new ConfigUtil();
            // Access configuration values
            Url = configUtil.getString("url");
            Username = configUtil.getString("username");
            Password = configUtil.getString("password");
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }
        initComponents();
        cmdSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+3;");
        newGroup.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+3;");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        cmdSearch = new javax.swing.JButton();
        newGroup = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(504, 100));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Online", "Offline" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        cmdSearch.setText("Search");
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        newGroup.setText("New Group");
        newGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdSearch)
                                .addGap(5, 5, 5)
                                .addComponent(newGroup)
                                .addGap(5, 5, 5)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addGap(5, 5, 5)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField2)
                        .addComponent(newGroup, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdSearch, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
    }// </editor-fold>//GEN-END:initComponents

    private void newGroupActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newGroupActionPerformed
        int currentUserId = Service.getInstance().getUser().getUserID(); // ID của user hiện tại

        try {
            // Kết nối đến database
            Connection conn = DriverManager.getConnection(Url, Username, Password);

            // Lấy dữ liệu bạn bè từ database
            String query = """
                        SELECT
                                    CASE
                                        WHEN user_id_1 = ? THEN user_id_2
                                        ELSE user_id_1
                                    END AS friend_id
                                FROM user_friends
                                WHERE
                                    (user_id_1 = ? OR user_id_2 = ?)
                                    AND status = 'friends';
                    """;

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, currentUserId); // user_id cho điều kiện CASE
            stmt.setInt(2, currentUserId); // user_id cho điều kiện WHERE
            stmt.setInt(3, currentUserId); // user_id cho điều kiện WHERE
            ResultSet rs = stmt.executeQuery();

            // Chuẩn bị dữ liệu cho JTable
            java.util.List<Object[]> data = new java.util.ArrayList<>();
            while (rs.next()) {
                int friendId = rs.getInt("friend_id");

                // Lấy thêm thông tin bạn bè từ bảng `users`
                String friendQuery = "SELECT user_id, username, name, email FROM users WHERE user_id = ?";
                PreparedStatement friendStmt = conn.prepareStatement(friendQuery);
                friendStmt.setInt(1, friendId);

                ResultSet friendRs = friendStmt.executeQuery();
                if (friendRs.next()) {
                    data.add(new Object[] {
                            friendRs.getInt("user_id"),
                            friendRs.getString("username"),
                            friendRs.getString("name"),
                            friendRs.getString("email")
                    });
                }
                friendRs.close();
                friendStmt.close();
            }

            Object[][] friends = data.toArray(new Object[0][]);
            String[] columnNames = { "User ID", "Username", "Name", "Email" };

            // Hiển thị dữ liệu trên JTable
            JTable friendsTable = new JTable(friends, columnNames);
            friendsTable.setFillsViewportHeight(true);
            friendsTable.setRowHeight(30);

            JScrollPane scrollPane = new JScrollPane(friendsTable);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            // Tạo JDialog để hiển thị bảng
            JDialog dialog = new JDialog((JFrame) null, "List of Friends", true); // Khai báo dialog
            dialog.setLayout(new BorderLayout());

            // Thêm bảng vào trung tâm của dialog
            dialog.add(scrollPane, BorderLayout.CENTER);

            // Thêm ô nhập tên nhóm vào phần trên
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout để căn trái
            JTextField groupNameField = new JTextField(20); // Kích thước rộng cho JTextField
            JButton addButton = new JButton("Add");

            // Thêm nút Add bên trái JTextField
            topPanel.add(addButton);
            topPanel.add(new JLabel("Enter Group Name:"));
            topPanel.add(groupNameField);
            dialog.add(topPanel, BorderLayout.NORTH); // Đặt vào phần trên của dialog

            addButton.addActionListener(_ -> {
                int selectedRow = friendsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int friendId = (int) friendsTable.getValueAt(selectedRow, 0);
                    String groupName = groupNameField.getText();

                    if (groupName.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "Please enter a group name.", "Group Name Required",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            // Thêm nhóm mới vào bảng chat_group
                            String insertGroupQuery = "INSERT INTO chat_group (group_name, group_type, created_by) VALUES (?, 3, ?)";
                            int groupId = -1;
                            try (PreparedStatement insertGroupStmt = conn.prepareStatement(insertGroupQuery,
                                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                                insertGroupStmt.setString(1, groupName);
                                insertGroupStmt.setInt(2, currentUserId);
                                insertGroupStmt.executeUpdate();

                                // Lấy groupId sau khi thêm nhóm mới
                                try (ResultSet generatedKeys = insertGroupStmt.getGeneratedKeys()) {
                                    if (generatedKeys.next()) {
                                        groupId = generatedKeys.getInt(1); // group_id vừa được tạo
                                    }
                                }
                            }

                            if (groupId != -1) {
                                // Thêm user vào bảng chat_members với role 'admin'
                                String insertAdminQuery = "INSERT INTO group_members (group_id, user_id, role) VALUES (?, ?, 'admin')";
                                try (PreparedStatement insertAdminStmt = conn.prepareStatement(insertAdminQuery)) {
                                    insertAdminStmt.setInt(1, groupId);
                                    insertAdminStmt.setInt(2, currentUserId);
                                    insertAdminStmt.executeUpdate();
                                }

                                // Thêm bạn bè vào nhóm với role 'members'
                                String insertMemberQuery = "INSERT INTO group_members (group_id, user_id, role) VALUES (?, ?, 'member')";
                                try (PreparedStatement insertMemberStmt = conn.prepareStatement(insertMemberQuery)) {
                                    insertMemberStmt.setInt(1, groupId);
                                    insertMemberStmt.setInt(2, friendId);
                                    insertMemberStmt.executeUpdate();
                                }
                            }

                            JOptionPane.showMessageDialog(dialog, "Friend added to group successfully!");
                            PublicEvent.getInstance().getEventMenuLeft().newChat(new Model_Group_Chat(groupId, 0, groupName, "none", user.app.GroupType.MANY));
                            // Đóng dialog sau khi thành công
                            dialog.dispose(); // Đóng cửa sổ JDialog
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(dialog, "Error adding friend to group: " + ex.getMessage(),
                                    "Database Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select a friend to add.", "No Friend Selected",
                            JOptionPane.WARNING_MESSAGE);
                }
            });

            // Tạo JPanel cho nút và thêm vào phần dưới của dialog
            JPanel buttonPanel = new JPanel();
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.setSize(550, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);

            // Đóng kết nối
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching friends: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_newGroupActionPerformed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmdSearchActionPerformed
        PublicEvent.getInstance().getEventFriendList().filterFriend(jTextField2.getText(),
                jComboBox1.getSelectedItem().toString().toLowerCase());
    }// GEN-LAST:event_cmdSearchActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
        PublicEvent.getInstance().getEventFriendList().filterFriend(jTextField2.getText(),
                jComboBox1.getSelectedItem().toString().toLowerCase());
    }// GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
        PublicEvent.getInstance().getEventFriendList().filterFriend(jTextField2.getText(),
                jComboBox1.getSelectedItem().toString().toLowerCase());
    }// GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSearch;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton newGroup;
    // End of variables declaration//GEN-END:variables
}