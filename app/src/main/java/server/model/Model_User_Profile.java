package server.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
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

    public Model_User_Profile(int userID, String userName, String email, String address, String gender, LocalDate dob) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
    }

    @SuppressWarnings("unchecked")
    public Model_User_Profile(Object json) {
        JSONObject obj = null;
        try {
            if (json instanceof LinkedHashMap) {
                obj = new JSONObject((LinkedHashMap<String, Object>) json);
            } else {
                obj = new JSONObject(json);
            }
            this.userID = obj.getInt("userID");
            this.userName = obj.getString("userName");
            this.email = obj.getString("email");
            this.address = obj.getString("address");
            this.gender = obj.getString("gender");
            this.dob = LocalDate.parse(obj.getString("dob"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Model_User_Profile() {
    }

    private int userID;
    private String userName;
    private String email;
    private String address;
    private String gender;
    private LocalDate dob;
}
