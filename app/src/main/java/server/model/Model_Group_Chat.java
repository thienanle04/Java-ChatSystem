package server.model;

import server.app.GroupType;

public class Model_Group_Chat {

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public Model_Group_Chat(int groupId, String name, String status, GroupType groupType) {
        this.groupId = groupId;
        this.name = name;
        this.status = status;
        this.groupType = groupType;
    }

    private int groupId;
    private String name;
    private String status;
    private GroupType groupType;
}
