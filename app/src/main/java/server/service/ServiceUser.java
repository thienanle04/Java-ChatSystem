package server.service;

import server.connection.DatabaseConnection;
import server.model.Model_Friend_Request;
import server.model.Model_Login;
import server.model.Model_Message;
import server.model.Model_Register;
import server.model.Model_Reset_Password;
import server.model.Model_User_Profile;

import java.time.LocalDate;
import java.util.ArrayList;
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
        // Check user exit
        Model_Message message = new Model_Message();
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                message.setAction(false);
                message.setMessage("Username Already Exist");
            } else {
                message.setAction(true);
            }
            r.close();
            p.close();

            if (message.isAction()) {
                p = con.prepareStatement("select user_id from users where email =? limit 1");
                p.setString(1, data.getEmail());
                r = p.executeQuery();
                if (r.next()) {
                    message.setAction(false);
                    message.setMessage("Email Already Exist");
                } else {
                    message.setAction(true);
                }
                r.close();
                p.close();
            }

            if (message.isAction()) {
                // Insert User Register
                con.setAutoCommit(false);
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getName());
                p.setString(2, data.getUserName());
                p.setString(3, data.getPassword());
                p.setString(4, data.getEmail());
                LocalDate date = LocalDate.now().minusYears(100);   // Set Date of Birth to current date minus 100 years
                p.setDate(5, java.sql.Date.valueOf(date));
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
                message.setData(new Model_User_Profile(userID, data.getUserName(), data.getEmail(), "", "Other", date));
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

    public Model_User_Profile login(Model_Login login) throws SQLException {
        Model_User_Profile data = null;
        PreparedStatement p = con.prepareStatement(LOGIN);
        p.setString(1, login.getUserName());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String email = r.getString(3);
            String address = r.getString(4);
            String gender = r.getString(5);
            LocalDate dob = r.getDate(6).toLocalDate();
            data = new Model_User_Profile(userID, userName, email, address, gender, dob);
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

    // Return if update success or failed
    boolean updateProfile(Model_User_Profile newProfile) throws SQLException {
        try {
            PreparedStatement p = con.prepareStatement("update users set name=?, email=?, address=?, gender=?, date_of_birth=? where user_id=?");
            p.setString(1, newProfile.getUserName());
            p.setString(2, newProfile.getEmail());
            p.setString(3, newProfile.getAddress());
            p.setString(4, newProfile.getGender());
            p.setDate(5, java.sql.Date.valueOf(newProfile.getDob()));
            p.setInt(6, newProfile.getUserID());
            p.execute();
            p.close();
            return true;
        } catch (Exception e) {
            return false;
        }
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
    public Model_User_Profile getUserProfile(int userId) throws SQLException {
        Model_User_Profile profile = null;
        PreparedStatement p = con.prepareStatement(
                "SELECT user_id, name, email, address, gender, date_of_birth FROM users where user_id=?");
        p.setInt(1, userId);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            int userID = rs.getInt(1);
            String userName = rs.getString(2);
            String email = rs.getString(3);
            String address = rs.getString(4);
            String gender = rs.getString(5);
            LocalDate dob = rs.getDate(6).toLocalDate();
            profile = new Model_User_Profile(userID, userName, email, address, gender, dob);
        }
        return profile;
    }

    public boolean updatePassword(Model_Reset_Password data) throws SQLException {
        try {
            // PreparedStatement p = con.prepareStatement("username=BINARY(?) and password_hash=BINARY(?)");
            PreparedStatement p = con.prepareStatement("update users set password_hash=? where username=BINARY(?) and password_hash=BINARY(?)");
            p.setString(1, data.getNewPassword());
            p.setString(2, data.getUserName());
            p.setString(3, data.getPassword());
            p.execute();
            p.close();

            p = con.prepareStatement("select user_id from users where username =? and password_hash=? limit 1");
            p.setString(1, data.getUserName());
            p.setString(2, data.getNewPassword());
            ResultSet r = p.executeQuery();
            boolean ok = r.next();
            r.close();
            p.close();
            return ok;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean responseFriendRequest(Model_Friend_Request req, String status) throws SQLException {
        int userID1 = req.getToUserID();
        int userID2 = req.getFromUserID();
        // user_id_1 < user_id_2
        if (userID1 > userID2) {
            int temp = userID1;
            userID1 = userID2;
            userID2 = temp;
        }
        
        if (status.equals("reject")) {
            try {
                PreparedStatement p = con.prepareStatement("delete from user_friends where user_id_1=? and user_id_2=?");
                p.setInt(1, userID1);
                p.setInt(2, userID2);
                p.execute();
                p.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        try {
            PreparedStatement p = con.prepareStatement("update user_friends set status=? where user_id_1=? and user_id_2=?");
            p.setString(1, status);
            p.setInt(2, userID1);
            p.setInt(3, userID2);
            p.execute();
            p.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Model_Friend_Request> getFriendRequestsReceived(int userID) throws SQLException {
        ArrayList<Model_Friend_Request> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement("select user_id_1, user_id_2 from user_friends where (user_id_1=? and status='pending_2_1') or (user_id_2=? and status='pending_1_2')");
        p.setInt(1, userID);
        p.setInt(2, userID);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int userID1 = r.getInt(1);
            int userID2 = r.getInt(2);
            int otherID = userID1 == userID ? userID2 : userID1;
            PreparedStatement p2 = con.prepareStatement("select name from users where user_id=?");
            p2.setInt(1, otherID);
            ResultSet r2 = p2.executeQuery();
            if (r2.next()) {
                String name = r2.getString(1);
                list.add(new Model_Friend_Request(userID, otherID, name));
            }
        }
        r.close();
        p.close();
        return list;
    }

    // SQL
    public String getRole (int userId) throws SQLException {
        PreparedStatement p = con.prepareStatement("SELECT role FROM users where user_id=?");
        p.setInt(1, userId);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            String role = rs.getString(1);
            return role;
        }
        return "";
    }
    //  SQL
    private final String LOGIN = "select user_id, username, email, address, gender, date_of_birth from users where username=BINARY(?) and password_hash=BINARY(?)";
    private final String SET_STATUS = "update users set status=? where user_id=?";
    private final String INSERT_USER = "insert into users (name, username, password_hash, email, date_of_birth) values (?, ?,?,?,?)";
    private final String CHECK_USER = "select user_id from users where username =? limit 1";
    // Instance
    private final Connection con;
}
