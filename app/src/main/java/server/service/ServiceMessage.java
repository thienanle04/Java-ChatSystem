package server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;


import server.connection.DatabaseConnection;
import server.model.Model_Group_Chat;
import server.model.Model_Chat_Message;
import server.app.GroupType;

public class ServiceMessage {
    
    private final Connection con;
    public ServiceMessage() {
        try {
            this.con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    // Get the list of users in a group chat
    public List<Integer> getUserListInGroupChat(int groupId) throws SQLException {
        List<Integer> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select u.user_id from users u join group_members gm on u.user_id = gm.user_id where gm.group_id = ?");
        p.setInt(1, groupId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int userID = r.getInt(1);
            list.add(Integer.valueOf(userID));
        }
        r.close();
        p.close();
        return list;
    }

    // Get the private chat between only two users
    public Model_Group_Chat getPrivateChat(int user, int other) {
        Model_Group_Chat groupChat = null;
        try {
            PreparedStatement p = con.prepareStatement("select group_id from group_members where user_id = ? and group_id in (select gm.group_id from group_members gm join chat_group cg on cg.group_id = gm.group_id where gm.user_id = ? and cg.group_type = 2)");
            p.setInt(1, user);
            p.setInt(2, other);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                // group chat name is the name of the other person
                PreparedStatement p2 = con.prepareStatement("select u.name from users u where u.user_id = ? limit 1");
                p2.setInt(1, other);
                ResultSet r2 = p2.executeQuery();
                r2.next();
                String name = r2.getString(1);
                groupChat = new Model_Group_Chat(r.getInt(1), other, name, "none", 2);
                p2.close();
                r2.close();
                return groupChat;
            }
            r.close();
            p.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a new private chat
        try {
            PreparedStatement p = con.prepareStatement("insert into chat_group (group_type) values (2)", PreparedStatement.RETURN_GENERATED_KEYS);
            p.executeUpdate();
            ResultSet r = p.getGeneratedKeys();
            if (r.next()) {
                int groupID = r.getInt(1);
                PreparedStatement p2 = con.prepareStatement("insert into group_members (group_id, user_id) values (?, ?)");
                p2.setInt(1, groupID);
                p2.setInt(2, user);
                p2.executeUpdate();
                p2.setInt(1, groupID);
                p2.setInt(2, other);
                p2.executeUpdate();
                p2.close();

                PreparedStatement p3 = con.prepareStatement("select u.name, u.status from users u where u.user_id = ? limit 1");
                p3.setInt(1, other);
                ResultSet r3 = p3.executeQuery();
                r3.next();
                String name = r3.getString(1);
                String status = r3.getString(2);
                groupChat = new Model_Group_Chat(groupID, other, name, status, 2);
                p3.close();
                r3.close();
            }
            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupChat;
    }

    // Note: Private chat is the direct chat of two users
    public List<Integer> getChatPrivateOfUser(int userId) throws SQLException {
        List<Integer> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select group_id from group_members where user_id = ? and group_id in (select group_id from chat_group where group_type = 2)");
        p.setInt(1, userId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int groupID = r.getInt(1);
            list.add(Integer.valueOf(groupID));
        }
        r.close();
        p.close();
        return list;
    }

    // This function is used to get the list of chats that the user has
    public List<Model_Group_Chat> getChatListId(int userId) throws SQLException {
        List<Model_Group_Chat> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, userId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int groupID = r.getInt(1);
            String name = r.getString(2);
            GroupType type = GroupType.toGroupType(r.getInt(3));

            if (type == GroupType.TWO) {
                // If the group is a two-person chat, then the name of the chat is the name of the other person
                PreparedStatement p2 = con.prepareStatement("select u.user_id, u.name, u.status from group_members gm join users u on u.user_id = gm.user_id where gm.group_id = ? and gm.user_id <> ? limit 1");
                p2.setInt(1, groupID);
                p2.setInt(2, userId);
                ResultSet r2 = p2.executeQuery();
                r2.next(); 
                int otherID = r2.getInt(1);
                name = r2.getString(2);
                String status = r2.getString(3); // then the status of the group chat is the status of 'other' person
                list.add(new Model_Group_Chat(groupID, otherID, name, status, type.getValue()));
            } else {
                // If group type is "MANY", then its' status is "none"
                list.add(new Model_Group_Chat(groupID, 0, name, "none", type.getValue()));
            }
        }
        r.close();
        p.close();
        return list;
    }

    // Save the message to the database
    public Model_Chat_Message saveMessage(Model_Chat_Message message) throws SQLException {
        // Check if user block or blocked by the other user
        PreparedStatement p3 = con.prepareStatement("select group_type from chat_group where group_id = ?");
        p3.setInt(1, message.getGroupID());
        ResultSet r3 = p3.executeQuery();
        r3.next();
        int groupType = r3.getInt(1);
        if (groupType == 2) {
            int userID1 = message.getSenderID();

            PreparedStatement p5 = con.prepareStatement("select user_id from group_members where group_id = ? and user_id <> ?");
            p5.setInt(1, message.getGroupID());
            p5.setInt(2, userID1);
            ResultSet r5 = p5.executeQuery();
            r5.next();
            int userID2 = r5.getInt(1);

            if (userID1 > userID2) {
                int temp = userID1;
                userID1 = userID2;
                userID2 = temp;
            }
            PreparedStatement p4 = con.prepareStatement("select * from user_friends where user_id_1 = ? and user_id_2 = ? and (status = 'block_1_2' or status = 'block_2_1')");
            p4.setInt(1, userID1);
            p4.setInt(2, userID2);
            ResultSet r4 = p4.executeQuery();
            if (r4.next()) {
                return null;
            }
            p4.close();
            r4.close();
        }

        Model_Chat_Message receive_Message = null;
        PreparedStatement p = con.prepareStatement("insert into group_messages (group_id, sender_id, message) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        p.setInt(1, message.getGroupID());
        p.setInt(2, message.getSenderID());
        p.setString(3, message.getMessage());
        p.executeUpdate();
        ResultSet r = p.getGeneratedKeys();

        PreparedStatement p2 = con.prepareStatement("select u.name from users u where u.user_id = ?");
        p2.setInt(1, message.getSenderID());
        ResultSet r2 = p2.executeQuery();
        r2.next();
        String name = r2.getString(1);

        if (r.next()) {
            int messageID = r.getInt(1);
            receive_Message = new Model_Chat_Message(messageID, message.getGroupID(), message.getSenderID(), name, message.getMessage());
        }
        r.close();
        p.close();
        return receive_Message;
    }

    public void deleteAllMessages(int groupId, int userId) throws SQLException {
        try {
            PreparedStatement p = con.prepareStatement("update message_visibility set visibility_status = 'hidden' where user_id = ? and message_id in (select message_id from group_messages where group_id = ?)");
            p.setInt(1, userId);
            p.setInt(2, groupId);
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    // Get all chat messages
    public HashMap<Integer, LinkedList<Model_Chat_Message>> getAllChat(int userId) throws SQLException {
        HashMap<Integer, LinkedList<Model_Chat_Message>> data = new HashMap<>();
        List<Model_Group_Chat> list = getChatListId(userId);
        for (Model_Group_Chat group : list) {
            LinkedList<Model_Chat_Message> messages = new LinkedList<>();
            PreparedStatement p = con.prepareStatement("select gm.message_id, sender_id, message, visibility_status from group_messages gm join message_visibility mv on gm.message_id = mv.message_id where gm.group_id = ? and mv.user_id = ?");
            p.setInt(1, group.getGroupId());
            p.setInt(2, userId);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int messageID = r.getInt(1);
                int senderID = r.getInt(2);
                String message = r.getString(3);
                String visibility = r.getString(4);
                if (visibility.equals("deleted")) {
                    message = "This message was deleted";
                } else if (visibility.equals("hidden")) {
                    continue;
                }
                PreparedStatement p2 = con.prepareStatement("select u.name from users u where u.user_id = ? limit 1");
                p2.setInt(1, senderID);
                ResultSet r2 = p2.executeQuery();
                r2.next();
                String userName = r2.getString(1);
                messages.add(new Model_Chat_Message(messageID, group.getGroupId(), senderID, userName, message));
            }
            data.put(group.getGroupId(), messages);
        }
        return data;
    }

    private final String SELECT_USER_ACCOUNT = "select cg.group_id, cg.group_name, cg.group_type from chat_group cg join group_members gm on cg.group_id = gm.group_id where gm.user_id = ?";

}
