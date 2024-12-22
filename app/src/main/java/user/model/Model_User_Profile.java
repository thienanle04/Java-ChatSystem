package user.model;

import java.time.LocalDate;
import org.json.JSONObject;
import org.json.JSONException;

public class Model_User_Profile {

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getLocalDateDob() {
        return dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob.toString();
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Model_User_Profile(int userID, String userName, String name, String email, String address, String gender, LocalDate dob) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
    }

    public Model_User_Profile(Object json) {
        JSONObject obj = (JSONObject) json;

        try {
            this.userID = obj.getInt("userID");
            this.userName = obj.getString("userName");
            this.name = obj.getString("name");
            this.email = obj.getString("email");
            this.address = obj.getString("address");
            this.gender = obj.getString("gender");
            this.dob = LocalDate.parse(obj.getString("dob"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("userID", this.userID);
            obj.put("userName", this.userName);
            obj.put("name", this.name);
            obj.put("email", this.email);
            obj.put("address", this.address);
            obj.put("dob", this.dob);
            obj.put("gender", this.gender);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Model_User_Profile() {
    }

    private int userID;
    private String userName;
    private String name;
    private String email;
    private String address;
    private String gender;
    private LocalDate dob;
}
