package server.dao.impl;

import server.dao.ChatGroupDAO;
import server.entity.ChatGroup;
import server.connection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatGroupDAOImpl implements ChatGroupDAO {

    @Override
    public void addChatGroup(ChatGroup group) {
        String sql = "INSERT INTO chat_group (group_name, created_by, created_at, is_encrypted) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, group.getGroupName());
            stmt.setInt(2, group.getCreatedBy());
            stmt.setString(3, group.getCreatedAt());
            stmt.setBoolean(4, group.isEncrypted());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding chat group: " + e.getMessage());
        }
    }

    @Override
    public ChatGroup getChatGroupById(int groupId) {
        String sql = "SELECT * FROM chat_group WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, groupId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ChatGroup group = new ChatGroup();
                group.setGroupId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                group.setCreatedBy(rs.getInt("created_by"));
                group.setCreatedAt(rs.getString("created_at"));
                group.setEncrypted(rs.getBoolean("is_encrypted"));
                return group;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching chat group: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ChatGroup> getAllChatGroups() {
        List<ChatGroup> groups = new ArrayList<>();
        String sql = "SELECT * FROM chat_group";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ChatGroup group = new ChatGroup();
                group.setGroupId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                group.setCreatedBy(rs.getInt("created_by"));
                group.setCreatedAt(rs.getString("created_at"));
                group.setEncrypted(rs.getBoolean("is_encrypted"));
                groups.add(group);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all chat groups: " + e.getMessage());
        }
        return groups;
    }

    @Override
    public void updateChatGroup(ChatGroup group) {
        String sql = "UPDATE chat_group SET group_name = ?, created_by = ?, created_at = ?, is_encrypted = ? WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, group.getGroupName());
            stmt.setInt(2, group.getCreatedBy());
            stmt.setString(3, group.getCreatedAt());
            stmt.setBoolean(4, group.isEncrypted());
            stmt.setInt(5, group.getGroupId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating chat group: " + e.getMessage());
        }
    }

    @Override
    public void deleteChatGroup(int groupId) {
        String sql = "DELETE FROM chat_group WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, groupId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting chat group: " + e.getMessage());
        }
    }
}
