package server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.connection.DatabaseConnection;
import server.model.Model_Group_Chat;
import server.model.Model_User_Account;
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
                PreparedStatement p2 = con.prepareStatement("select u.username, u.status from group_members gm join users u on u.user_id = gm.user_id where gm.group_id = ? and gm.user_id <> ? limit 1");
                p2.setInt(1, groupID);
                p2.setInt(2, userId);
                ResultSet r2 = p2.executeQuery();
                r2.next();
                name = r2.getString(1);
                String status = r2.getString(2); // then the status of the group chat is the status of 'other' person
                list.add(new Model_Group_Chat(groupID, name, status, type.getValue()));
            } else {
                // If group type is "MANY", then its' status is "none"
                list.add(new Model_Group_Chat(groupID, name, "none", type.getValue()));
            }
        }
        r.close();
        p.close();
        return list;
    }

    private final String SELECT_USER_ACCOUNT = "select cg.group_id, cg.group_name, cg.group_type from chat_group cg join group_members gm on cg.group_id = gm.group_id where gm.user_id = ?";

}
