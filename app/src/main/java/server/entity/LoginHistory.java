package server.entity;

public class LoginHistory {
    private int loginId;
    private int userId;
    private String loggedInAt;

    // Getters and Setters
    public int getLoginId() {
        return loginId;
    }
    
    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoggedInAt() {
        return loggedInAt;
    }

    public void setLoggedInAt(String loggedInAt) {
        this.loggedInAt = loggedInAt;
    }

}
