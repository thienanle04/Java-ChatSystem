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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
            groupId = obj.getInt("groupId");
            text = obj.getString("text");
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

    private int fromUserID;
    private String userName;
    private int groupId;
    private String text;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fromUserID", fromUserID);
            json.put("userName", userName);
            json.put("groupId", groupId);
            json.put("text", text);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
