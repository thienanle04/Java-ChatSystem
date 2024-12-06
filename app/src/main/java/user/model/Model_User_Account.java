package user.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_User_Account {

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Model_User_Account(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public Model_User_Account(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            userID = obj.getInt("userID");
            userName = obj.getString("userName");
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

    private int userID;
    private String userName;
}
