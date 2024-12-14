package user.model;

import org.json.JSONException;
import org.json.JSONObject;

import user.app.GroupType;

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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public boolean isOnline() {
        return status.equals("online");
    }

    public Model_Group_Chat(int groupId, int userID, String name, String status, GroupType groupType) {
        this.groupId = groupId;
        this.userID = userID;
        this.name = name;
        this.status = status;
        this.groupType = groupType;
    }

    public Model_Group_Chat(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            groupId = obj.getInt("groupId");
            userID = obj.getInt("userID");
            name = obj.getString("name");
            status = obj.getString("status");
            groupType = GroupType.toGroupType(obj.getInt("groupType"));
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

    private int groupId;
    private int userID;
    private String name;
    private String status;
    private GroupType groupType;
}
