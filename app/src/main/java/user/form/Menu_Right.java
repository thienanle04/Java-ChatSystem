/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package user.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import user.config.ConfigUtil;
import user.model.Model_Group_Chat;
import user.service.Service;
import user.app.GroupType;
import user.event.PublicEvent;

public class Menu_Right extends javax.swing.JPanel {

    private String Url;
    private String Username;
    private String Password;

    /**
     * Creates new form Menu_Left
     */
    public Menu_Right() {
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
    }

    private int user_Id;
    private Model_Group_Chat groupChat;

    public void setChat(Model_Group_Chat groupChat) {
        user_Id = Service.getInstance().getUser().getUserID();
        this.groupChat = groupChat;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMember = new javax.swing.JButton();
        promoteAdminRole = new javax.swing.JButton();
        deleteMember = new javax.swing.JButton();
        changeGroupName = new javax.swing.JButton();

        setBackground(new java.awt.Color(249, 249, 249));

        addMember.setText("Add Members");
        addMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberActionPerformed(evt);
            }
        });

        promoteAdminRole.setText("Promote Admin Role");
        promoteAdminRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promoteAdminRoleActionPerformed(evt);
            }
        });

        deleteMember.setText("Delete Members");
        deleteMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMemberActionPerformed(evt);
            }
        });

        changeGroupName.setText("Change Group Name");
        changeGroupName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeGroupNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(addMember, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(promoteAdminRole, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        199, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(changeGroupName, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(deleteMember, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(changeGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMember, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteMember, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(promoteAdminRole, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(177, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void addMemberActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addMemberActionPerformed
        // Thay đổi giá trị `currentUserId` bằng user_id hiện tại
        int currentUserId = user_Id; // ID của user hiện tại
        int groupId = this.groupChat.getGroupId();
        if (this.groupChat.getGroupType() == GroupType.MANY) {
            try {
                // Kết nối đến database
                String url = Url;
                String user = Username;
                String password = Password; // Thay bằng mật khẩu của bạn
                Connection conn = DriverManager.getConnection(url, user, password);

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
                                AND status = 'friends'
                                AND CASE
                                        WHEN user_id_1 = ? THEN user_id_2
                                        ELSE user_id_1
                                    END NOT IN (
                                        SELECT user_id
                                        FROM group_members
                                        WHERE group_id = ?
                                    );
                        """;

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, currentUserId); // user_id cho điều kiện CASE
                stmt.setInt(2, currentUserId); // user_id cho điều kiện WHERE
                stmt.setInt(3, currentUserId); // user_id cho điều kiện WHERE
                stmt.setInt(4, currentUserId); // user_id cho điều kiện CASE
                stmt.setInt(5, groupId); // groupId để lọc

                ResultSet rs = stmt.executeQuery();

                // Chuẩn bị dữ liệu cho JTable
                java.util.List<Object[]> data = new java.util.ArrayList<>();
                while (rs.next()) {
                    int friendId = rs.getInt("friend_id");

                    // Lấy thêm thông tin bạn bè từ bảng `users`
                    String friendQuery = "SELECT user_id, username, name FROM users WHERE user_id = ?";
                    PreparedStatement friendStmt = conn.prepareStatement(friendQuery);
                    friendStmt.setInt(1, friendId);

                    ResultSet friendRs = friendStmt.executeQuery();
                    if (friendRs.next()) {
                        data.add(new Object[] {
                                friendRs.getInt("user_id"),
                                friendRs.getString("username"),
                                friendRs.getString("name"),
                        });
                    }
                    friendRs.close();
                    friendStmt.close();
                }

                Object[][] friends = data.toArray(new Object[0][]);
                String[] columnNames = { "User ID", "Username", "Name" };

                // Hiển thị dữ liệu trên JTable
                JTable friendsTable = new JTable(friends, columnNames);
                friendsTable.setFillsViewportHeight(true);
                friendsTable.setRowHeight(30);
                // Ẩn cột "User ID"
                friendsTable.getColumnModel().getColumn(0).setMinWidth(0);
                friendsTable.getColumnModel().getColumn(0).setMaxWidth(0);
                friendsTable.getColumnModel().getColumn(0).setWidth(0);

                JScrollPane scrollPane = new JScrollPane(friendsTable);
                scrollPane.setPreferredSize(new Dimension(500, 300));

                // Tạo JDialog để hiển thị bảng
                JDialog dialog = new JDialog((JFrame) null, "List of Friends", true);
                dialog.setLayout(new BorderLayout());
                dialog.add(scrollPane, BorderLayout.CENTER);

                JButton addButton = new JButton("Add");
                addButton.addActionListener(_ -> {
                    int selectedRow = friendsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int friendId = (int) friendsTable.getValueAt(selectedRow, 0);
                        try {
                            // Thêm bạn bè vào nhóm với groupID
                            String insertQuery = "INSERT INTO group_members (group_id, user_id, role) VALUES (?, ?, 'member')";
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                                insertStmt.setInt(1, groupId); // Thay `groupId` bằng giá trị nhóm của bạn
                                insertStmt.setInt(2, friendId);
                                insertStmt.executeUpdate();
                                JOptionPane.showMessageDialog(dialog, "Friend added to group successfully!");
                                dialog.dispose();
                            } // Thay `groupId` bằng giá trị nhóm của bạn
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(dialog, "Error adding friend to group: " + ex.getMessage(),
                                    "Database Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Please select a friend to add.", "No Friend Selected",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(addButton);
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
        } else {
            PublicEvent.getInstance().getEventMain().showNotification("Can not add new member to private chat");
        }
    }// GEN-LAST:event_addMemberActionPerformed

    private void deleteMemberActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteMemberActionPerformed
        int groupId = this.groupChat.getGroupId();
        int userId = user_Id;
        String role = "member";
        String url = Url;
        String user = Username;
        String password = Password;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT role FROM group_members WHERE group_id = ? AND user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, groupId);
            stmt.setInt(2, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = rs.getString("role"); // Lấy giá trị của cột "role"
            } else {
                System.out.println("User không thuộc group này hoặc group không tồn tại.");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Right.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(role);
        if (this.groupChat.getGroupType() == GroupType.MANY) {
            if (role.equals("admin")) {
                // Create and show a dialog with group members
                JDialog dialog = new JDialog((Frame) null, "Group Members", true);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(600, 400);
                dialog.setLocationRelativeTo(null);

                // Table model
                DefaultTableModel tableModel = new DefaultTableModel(new String[] { "User Name", "Name", "UserId" }, 0);
                JTable memberTable = new JTable(tableModel);

                // Fetch group members
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT u.username, u.name, gm.user_id " +
                            "FROM group_members gm " +
                            "JOIN users u ON gm.user_id = u.user_id " +
                            "WHERE gm.group_id = ? AND gm.role = 'member'";

                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, groupId);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        String userName = rs.getString("username");
                        String name = rs.getString("name");
                        int _userId = rs.getInt("user_id");

                        // Add member data to table
                        tableModel.addRow(new Object[] { userName, name, _userId });
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Menu_Right.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Hide the 'userId' column by setting its min/max width to 0
                memberTable.getColumnModel().getColumn(2).setMinWidth(0);
                memberTable.getColumnModel().getColumn(2).setMaxWidth(0);
                memberTable.getColumnModel().getColumn(2).setWidth(0);

                // Create the Delete button
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(_ -> {
                    int selectedRow = memberTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int _userId = (int) tableModel.getValueAt(selectedRow, 2); // Get user_id from the selected row

                        try (Connection conn = DriverManager.getConnection(url, user, password)) {
                            // Thực hiện câu lệnh DELETE để xóa người dùng khỏi group_members
                            String deleteQuery = "DELETE FROM group_members WHERE user_id = ? AND group_id = ?";
                            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                            deleteStmt.setInt(1, _userId); // Truyền user_id
                            deleteStmt.setInt(2, groupId); // Truyền group_id
                            deleteStmt.executeUpdate();
                            JOptionPane.showMessageDialog(dialog, "User successfully deleted from group.");

                            // Xóa dòng trong JTable
                            tableModel.removeRow(selectedRow);
                        } catch (SQLException ex) {
                            Logger.getLogger(Menu_Right.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(dialog, "Error deleting user from group.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Please select a user to delete.", "No Selection",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });
                // Set up the dialog
                dialog.add(new JScrollPane(memberTable), BorderLayout.CENTER);

                // Add the Promote button to the bottom panel
                JPanel bottomPanel = new JPanel();
                bottomPanel.add(deleteButton);
                dialog.add(bottomPanel, BorderLayout.SOUTH);

                dialog.setVisible(true);
            } else {
                PublicEvent.getInstance().getEventMain().showNotification("You are not a admin");
            }
        } else {
            PublicEvent.getInstance().getEventMain().showNotification("Can not promote admin in private chat");
        }
    }// GEN-LAST:event_deleteMemberActionPerformed

    private void promoteAdminRoleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_promoteAdminRoleActionPerformed
        int groupId = this.groupChat.getGroupId();
        int userId = user_Id;
        String role = "member";
        String url = Url;
        String user = Username;
        String password = Password;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT role FROM group_members WHERE group_id = ? AND user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, groupId);
            stmt.setInt(2, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = rs.getString("role"); // Lấy giá trị của cột "role"
            } else {
                System.out.println("User không thuộc group này hoặc group không tồn tại.");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_Right.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(role);
        if (this.groupChat.getGroupType() == GroupType.MANY) {
            if (role.equals("admin")) {
                // Create and show a dialog with group members
                JDialog dialog = new JDialog((Frame) null, "Group Members", true);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(600, 400);
                dialog.setLocationRelativeTo(null);

                // Table model
                DefaultTableModel tableModel = new DefaultTableModel(new String[] { "User Name", "Name", "UserId" }, 0);
                JTable memberTable = new JTable(tableModel);

                // Fetch group members
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT u.username, u.name, gm.user_id " +
                            "FROM group_members gm " +
                            "JOIN users u ON gm.user_id = u.user_id " +
                            "WHERE gm.group_id = ? AND gm.role = 'member'";

                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, groupId);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        String userName = rs.getString("username");
                        String name = rs.getString("name");
                        int _userId = rs.getInt("user_id");

                        // Add member data to table
                        tableModel.addRow(new Object[] { userName, name, _userId });
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Menu_Right.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Hide the 'userId' column by setting its min/max width to 0
                memberTable.getColumnModel().getColumn(2).setMinWidth(0);
                memberTable.getColumnModel().getColumn(2).setMaxWidth(0);
                memberTable.getColumnModel().getColumn(2).setWidth(0);

                // Create the Promote button
                JButton promoteButton = new JButton("Promote");
                promoteButton.addActionListener(_ -> {
                    int selectedRow = memberTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int _userId = (int) tableModel.getValueAt(selectedRow, 2); // Get user_id from the selected row

                        try (Connection conn = DriverManager.getConnection(url, user, password)) {
                            String updateQuery = "UPDATE group_members SET role = 'admin' WHERE user_id = ? AND group_id = ?";
                            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                            updateStmt.setInt(1, _userId);
                            updateStmt.setInt(2, groupId);
                            updateStmt.executeUpdate();
                            JOptionPane.showMessageDialog(dialog, "User promoted to admin.");

                            // Optionally refresh the table or remove the promoted user from the list
                            tableModel.removeRow(selectedRow);
                        } catch (SQLException ex) {
                            Logger.getLogger(Menu_Right.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(dialog, "Error promoting user to admin.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Please select a user to promote.", "No Selection",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });

                // Set up the dialog
                dialog.add(new JScrollPane(memberTable), BorderLayout.CENTER);

                // Add the Promote button to the bottom panel
                JPanel bottomPanel = new JPanel();
                bottomPanel.add(promoteButton);
                dialog.add(bottomPanel, BorderLayout.SOUTH);

                dialog.setVisible(true);
            } else {
                PublicEvent.getInstance().getEventMain().showNotification("You are not a admin");
            }
        } else {
            PublicEvent.getInstance().getEventMain().showNotification("Can not promote admin in private chat");
        }
    }// GEN-LAST:event_promoteAdminRoleActionPerformed

    private void changeGroupNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_changeGroupNameActionPerformed
        String newGroupName = "";
        int groupId = this.groupChat.getGroupId();
        String url = Url;
        String user = Username;
        String password = Password;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối cơ sở dữ liệu
            conn = DriverManager.getConnection(url, user, password);

            // Truy vấn tên nhóm hiện tại
            String query = "SELECT group_name, group_type FROM chat_group WHERE group_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, groupId);
            rs = stmt.executeQuery();

            String currentGroupName = null;
            int groupType = -1;

            if (rs.next()) {
                currentGroupName = rs.getString("group_name");
                groupType = rs.getInt("group_type");
            }

            if (currentGroupName != null) {
                if (groupType == 2) {
                    JOptionPane.showMessageDialog(this, "This group cannot be renamed because its type is 2.",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Hiển thị hộp thoại để người dùng nhập tên nhóm mới
                newGroupName = JOptionPane.showInputDialog(this,
                        "Current Group Name: " + currentGroupName + "\nEnter New Group Name:",
                        "Change Group Name",
                        JOptionPane.PLAIN_MESSAGE);

                // Kiểm tra người dùng có nhập giá trị mới hay không
                if (newGroupName != null && !newGroupName.trim().isEmpty()) {
                    // Cập nhật tên nhóm trong cơ sở dữ liệu
                    String updateQuery = "UPDATE chat_group SET group_name = ? WHERE group_id = ? AND group_type != 2";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setString(1, newGroupName.trim());
                    stmt.setInt(2, groupId);
                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Group name updated successfully!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        groupChat.setName(newGroupName.trim());
                        PublicEvent.getInstance().getEventHome().renameGroupChat(groupChat);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Failed to update group name. Group type may be restricted.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Group name update cancelled.", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Group not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }// GEN-LAST:event_changeGroupNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMember;
    private javax.swing.JButton changeGroupName;
    private javax.swing.JButton deleteMember;
    private javax.swing.JButton promoteAdminRole;
    // End of variables declaration//GEN-END:variables
}
