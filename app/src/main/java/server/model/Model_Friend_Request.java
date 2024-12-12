package server.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Friend_Request {
    private int toUserID;
    private int fromUserID;
    private String name;

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }
    
    public String getName()  {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Model_Friend_Request(int toUserID, int fromUserID, String name) {
        this.toUserID = toUserID;
        this.fromUserID = fromUserID;
        this.name = name;
    }

    public Model_Friend_Request() {
    }

    public Model_Friend_Request(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            this.toUserID = obj.getInt("toUserID");
            this.fromUserID = obj.getInt("fromUserID");
            name = obj.getString("name");
        } catch (JSONException e) {
            System.out.println("Error parsing JSON to Model_Friend_Request");
        }
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("toUserID", toUserID);
            obj.put("fromUserID", fromUserID);
            obj.put("name", name);
            return obj;
        } catch (JSONException e) {
            return null;
        }
    }

}
