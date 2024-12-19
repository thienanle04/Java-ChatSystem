package user.model;

import org.json.JSONObject;
import org.json.JSONException;

public class Model_Spam_Report {
    private int spam_user_id;
    private int report_user_id;

    public Model_Spam_Report() {
    }

    public Model_Spam_Report(int spam_user_id, int report_user_id) {
        this.spam_user_id = spam_user_id;
        this.report_user_id = report_user_id;
    }

    public int getSpam_user_id() {
        return spam_user_id;
    }

    public void setSpam_user_id(int spam_user_id) {
        this.spam_user_id = spam_user_id;
    }

    public int getReport_user_id() {
        return report_user_id;
    }

    public void setReport_user_id(int report_user_id) {
        this.report_user_id = report_user_id;
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("spam_user_id", spam_user_id);
            obj.put("report_user_id", report_user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
