package server.service;

import server.connection.DatabaseConnection;
import server.model.Model_Login;
import server.model.Model_Message;
import server.model.Model_Register;
import server.model.Model_User_Account;
import server.model.Model_User_Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUser {

    public ServiceUser() {
        try {
            this.con = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public Model_Message register(Model_Register data) {
        //  Check user exit
        Model_Message message = new Model_Message();
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                message.setAction(false);
                message.setMessage("User Already Exit");
            } else {
                message.setAction(true);
            }
            r.close();
            p.close();
            if (message.isAction()) {
                //  Insert User Register
                con.setAutoCommit(false);
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getUserName());
                p.setString(2, data.getPassword());
                p.setString(3, data.getEmail());
                p.execute();
                r = p.getGeneratedKeys();
                r.first();
                int userID = r.getInt(1);
                r.close();
                p.close();
                con.commit();
                con.setAutoCommit(true);
                message.setAction(true);
                message.setMessage("Ok");
                message.setData(new Model_User_Account(userID, data.getUserName()));
            }
        } catch (SQLException e) {
            message.setAction(false);
            message.setMessage("Server Error");
            try {
                if (con.getAutoCommit() == false) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException e1) {
            }
        }
        return message;
    }

    public Model_User_Account login(Model_Login login) throws SQLException {
        Model_User_Account data = null;
        PreparedStatement p = con.prepareStatement(LOGIN);
        p.setString(1, login.getUserName());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            data = new Model_User_Account(userID, userName);

            // Set Status Online
            p = con.prepareStatement(SET_STATUS);
            p.setString(1, "online");
            p.setInt(2, userID);
            p.execute();

            // Set Login History
            p = con.prepareStatement("insert into login_history (user_id) values (?)");
            p.setInt(1, userID);
            p.execute();
        }
        r.close();
        p.close();
        return data;
    }

    // Return list of private chat (2 person chat) of user
    public void userDisconnect(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement(SET_STATUS);
        p.setString(1, "offline");
        p.setInt(2, userID);
        p.execute();
        p.close();
    }

    // Get user profile (all information)
    public Model_User_Profile getUserProfile (int userId) throws SQLException {
        Model_User_Profile profile = null;
        PreparedStatement p = con.prepareStatement("SELECT username, email FROM users where user_id=?");
        p.setInt(1, userId);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            String userName = rs.getString(1);
            String email = rs.getString(2);
            return new Model_User_Profile(userId, userName, email);
        }
        return profile;
    }

    //  SQL
    private final String LOGIN = "select user_id, username, email from users where username=BINARY(?) and password_hash=BINARY(?)";
    private final String SET_STATUS = "update users set status=? where user_id=?";
    private final String INSERT_USER = "insert into users (username, password_hash, email) values (?,?,?)";
    private final String CHECK_USER = "select user_id from users where username =? limit 1";
    //  Instance
    private final Connection con;
}
