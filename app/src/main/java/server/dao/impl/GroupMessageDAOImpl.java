package server.dao.impl;

import server.dao.GroupMessageDAO;
import server.entity.GroupMessage;
import server.connection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupMessageDAOImpl implements GroupMessageDAO {

    @Override
    public void addMessage(GroupMessage message) {
        String sql = "INSERT INTO group_messages (group_id, sender_id, message, sent_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, message.getGroupId());
            stmt.setInt(2, message.getSenderId());
            stmt.setString(3, message.getMessage());
            stmt.setString(4, message.getSentAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding group message: " + e.getMessage());
        }
    }

    @Override
    public GroupMessage getMessageById(int messageId) {
        String sql = "SELECT * FROM group_messages WHERE message_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, messageId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                GroupMessage message = new GroupMessage();
                message.setMessageId(rs.getInt("message_id"));
                message.setGroupId(rs.getInt("group_id"));
                message.setSenderId(rs.getInt("sender_id"));
                message.setMessage(rs.getString("message"));
                message.setSentAt(rs.getString("sent_at"));
                return message;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching group message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<GroupMessage> getMessagesByGroupId(int groupId) {
        List<GroupMessage> messages = new ArrayList<>();
        String sql = "SELECT * FROM group_messages WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, groupId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GroupMessage message = new GroupMessage();
                message.setMessageId(rs.getInt("message_id"));
                message.setGroupId(rs.getInt("group_id"));
                message.setSenderId(rs.getInt("sender_id"));
                message.setMessage(rs.getString("message"));
                message.setSentAt(rs.getString("sent_at"));
                messages.add(message);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching messages by group ID: " + e.getMessage());
        }
        return messages;
    }

    @Override
    public void updateMessage(GroupMessage message) {
        String sql = "UPDATE group_messages SET group_id = ?, sender_id = ?, message = ?, sent_at = ? WHERE message_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, message.getGroupId());
            stmt.setInt(2, message.getSenderId());
            stmt.setString(3, message.getMessage());
            stmt.setString(4, message.getSentAt());
            stmt.setInt(5, message.getMessageId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating group message: " + e.getMessage());
        }
    }

    @Override
    public void deleteMessage(int messageId) {
        String sql = "DELETE FROM group_messages WHERE message_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, messageId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting group message: " + e.getMessage());
        }
    }
}
