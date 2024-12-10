package user.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Reset_Password {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Model_Reset_Password(String userName, String password, String newPassword) {
        this.userName = userName;
        this.password = password;
        this.newPassword = newPassword;
    }

    public Model_Reset_Password() {
    }

    private String userName;
    private String password;
    private String newPassword;

    public JSONObject toJsonObject() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("userName", userName);
            obj.put("password", password);
            obj.put("newPassword", newPassword);
            return obj;
        } catch (JSONException e) {
            return null;
        }
    }
}
