package user.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Model_Chat_Message {

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Model_Chat_Message(int messageID, int groupID, int senderID, String userName, String message, LocalDateTime time) {
        this.messageID = messageID;
        this.groupID = groupID;
        this.senderID = senderID;
        this.userName = userName;
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Model_Chat_Message() {
    }

    public Model_Chat_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            messageID = obj.getInt("messageID");
            groupID = obj.getInt("groupID");
            senderID = obj.getInt("senderID");
            userName = obj.getString("userName");
            message = obj.getString("message");
            time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse(obj.getString("time"), LocalDateTime::from);
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("messageID", messageID);
            json.put("groupID", groupID);
            json.put("senderID", senderID);
            json.put("userName", userName);
            json.put("message", message);
            json.put("time", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time));
            return json;
        } catch (JSONException e) {
            return null;
        }
    }

    private int messageID;
    private int groupID;
    private int senderID;
    private String userName;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}