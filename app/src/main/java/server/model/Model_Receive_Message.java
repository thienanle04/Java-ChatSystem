package server.model;

public class Model_Receive_Message {

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

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
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

    public Model_Receive_Message(int messageID, int groupID, int fromUserID, String userName, String text) {
        this.messageID = messageID;
        this.groupID = groupID;
        this.fromUserID = fromUserID;
        this.userName = userName;
        this.text = text;
    }

    public Model_Receive_Message() {
    }

    private int messageID;
    private int groupID;
    private int fromUserID;
    private String userName;
    private String text;
}
