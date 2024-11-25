package server.entity;

public class MessageVisibility {
    private int visibilityId;
    private int messageId;
    private int userId;
    private String visibilityStatus;
    private String modifiedAt;

    // Getters and Setters
    public int getVisibilityId() { return visibilityId; }
    public void setVisibilityId(int visibilityId) { this.visibilityId = visibilityId; }
    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getVisibilityStatus() { return visibilityStatus; }
    public void setVisibilityStatus(String visibilityStatus) { this.visibilityStatus = visibilityStatus; }
    public String getModifiedAt() { return modifiedAt; }
    public void setModifiedAt(String modifiedAt) { this.modifiedAt = modifiedAt; }
}
