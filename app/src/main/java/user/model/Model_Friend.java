package user.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Friend {
    private int userID;
    private String name;
    private String status;
    
    public int getUserID() {
        return userID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getName()  {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isOnline() {
        return status.equals("online");
    }
    
    public Model_Friend(int userID, String name, String status) {
        this.userID = userID;
        this.name = name;
        this.status = status;
    }
    
    public JSONObject toJsonObject() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("userID", String.valueOf(userID));
            obj.put("name", name);
            obj.put("status", status);
            return obj;
        } catch (JSONException e) {
            return null;
        }
    }
}

