package server.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public Model_User_Profile(int userID, String userName, String email, String address, String gender, String dob) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dob = LocalDate.parse(dob);
    }

    public Model_User_Profile(int userID, String userName, String email, String address, String gender, LocalDate dob) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
    }

    public Model_User_Profile() {
    }

    private int userID;
    private String userName;
    private String email;
    private String address;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
}
