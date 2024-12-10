package user.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Register {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Model_Register(String name, String userName, String password, String email) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Model_Register() {
    }

    private String name;
    private String userName;
    private String password;
    private String email;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("userName", userName);
            json.put("password", password);
            json.put("email", email);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
