package user.model;

import org.json.JSONObject;
import org.json.JSONException;

public class Model_Delete_Message {
    private int ID;
    private int userID;

    public Model_Delete_Message(int ID, int userID) {
        this.ID = ID;
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("ID", ID);
            obj.put("userID", userID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
