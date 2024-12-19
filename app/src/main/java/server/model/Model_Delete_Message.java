package server.model;

public class Model_Delete_Message {
    private int messageID;
    private int groupID;
    private int userID;

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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Model_Delete_Message() {}

    public Model_Delete_Message(int messageID, int groupID, int userID) {
        this.messageID = messageID;
        this.groupID = groupID;
        this.userID = userID;
    }
}
