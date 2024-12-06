package user.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Receive_Message {

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupId) {
        this.groupID = groupId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Receive_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            fromUserID = obj.getInt("fromUserID");
            userName = obj.getString("userName");
            groupID = obj.getInt("groupID");
            text = obj.getString("text");
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

    private int fromUserID;
    private String userName;
    private int groupID;
    private String text;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fromUserID", fromUserID);
            json.put("userName", userName);
            json.put("groupID", groupID);
            json.put("text", text);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
