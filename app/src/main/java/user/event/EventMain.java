package user.event;

import user.model.Model_Group_Chat;
import user.model.Model_User_Profile;

public interface EventMain {

    public void showLoading(boolean show);

    public void initChat();

    public void selectChat(Model_Group_Chat chat);

    public void updateChat(Model_Group_Chat chat);
    
    public void editProfile();

    public void updateProfile(Model_User_Profile newUserProfile);

    public void showNotification(String message);
}