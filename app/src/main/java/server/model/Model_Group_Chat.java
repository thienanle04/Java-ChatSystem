package server.model;

public class Model_Group_Chat {

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
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

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public Model_Group_Chat(int groupId, int userID, String name, String status, int groupType) {
        this.groupId = groupId;
        this.userID = userID;
        this.name = name;
        this.status = status;
        this.groupType = groupType;
    }

    private int groupId;
    private int userID;
    private String name;
    private String status;
    private int groupType;
}
