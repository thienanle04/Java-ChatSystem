package server.model;

public class Model_Change_Password {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Model_Change_Password(String userName, String password, String newPassword) {
        this.userName = userName;
        this.password = password;
        this.newPassword = newPassword;
    }

    public Model_Change_Password() {
    }

    private String userName;
    private String password;
    private String newPassword;
}
