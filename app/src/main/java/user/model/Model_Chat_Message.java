package user.model;

import user.app.MessageType;

public class Model_Chat_Message {

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Model_Chat_Message(int ID, int groupID, String userName, String message, MessageType type) {
        this.ID = ID;
        this.groupID = groupID;
        this.userName = userName;
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Model_Chat_Message() {
    }

    private int ID;
    private int groupID;
    private String userName;
    private String message;
    private MessageType type;
}