package server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.connection.DatabaseConnection;
import server.model.Model_User_Account;
import server.model.Model_Group_Chat;

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

    public List<Model_Group_Chat> getChatListId(int userId) throws SQLException {
        // This function is used to get the list of chats that the user has
        List<Model_Group_Chat> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, userId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int groupID = r.getInt(1);
            String name = r.getString(2);

            // Count the number of members in the group
            PreparedStatement p2 = con.prepareStatement("select count(*) from group_members where group_id = ?");
            p2.setInt(1, groupID);
            ResultSet r2 = p2.executeQuery();
            r2.next();
            int countMembers = r2.getInt(1);
            r2.close();
            p2.close();

            if (countMembers == 2) {
                list.add(new Model_Group_Chat(groupID, name, "offline", GroupType.TWO));
            } else {
                list.add(new Model_Group_Chat(groupID, name, "none", GroupType.MANY));
            }
        }
        r.close();
        p.close();
        return list;
    }

    private final String SELECT_USER_ACCOUNT = "select cg.group_id, cg.group_name from chat_group cg join group_members gm on cg.group_id = gm.group_id where gm.user_id = ?";

}
