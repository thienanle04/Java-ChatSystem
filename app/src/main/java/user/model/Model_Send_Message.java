package user.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Send_Message {

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getGroupID() {
        return this.groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Send_Message(int groupID, int fromUserID, String text) {
        this.groupID = groupID;
        this.fromUserID = fromUserID;
        this.text = text;
    }

    public Model_Send_Message() {
    }

    private int groupID;
    private int fromUserID;
    private String text;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("groupID", groupID);
            json.put("fromUserID", fromUserID);
                json.put("text", text);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
