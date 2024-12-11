package user.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Friend_Request {
    private int userID;
    private String name;
    
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
    
    public Model_Friend_Request(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }
    
    public JSONObject toJsonObject() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("userID", String.valueOf(userID));
            obj.put("name", name);
            return obj;
        } catch (JSONException e) {
            return null;
        }
    }

}
