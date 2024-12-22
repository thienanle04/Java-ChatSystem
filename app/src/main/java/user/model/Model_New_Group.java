package user.model;

import org.json.JSONObject;
import org.json.JSONException;

public class Model_New_Group {

    public Model_New_Group() {
    }

    public Model_New_Group(int userID1, int userID2, String groupName) {
        this.userID1 = userID1;
        this.userID2 = userID2;
        this.groupName = groupName;
    }

    public int getUserID1() {
        return userID1;
    }

    public void setUserID1(int userID1) {
        this.userID1 = userID1;
    }

    public int getUserID2() {
        return userID2;
    }

    public void setUserID2(int userID2) {
        this.userID2 = userID2;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("userID1", userID1);
            obj.put("userID2", userID2);
            obj.put("groupName", groupName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private int userID1;
    private int userID2;
    private String groupName;
}
