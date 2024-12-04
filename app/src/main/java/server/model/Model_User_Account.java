package server.model;

public class Model_User_Account {

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

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Model_User_Account(int userID, String userName, String email, String status) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.status = status;
    }

    public Model_User_Account() {
    }

    private int userID;
    private String userName;
    private String email;
    private String status;
}
