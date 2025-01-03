package user.event;

import user.model.Model_Group_Chat;

public interface EventMain {

    public void showLoading(boolean show);

    public void initChat();
    
    public void initAdminApp();

    public void selectChat(Model_Group_Chat chat);

    public void updateChat(Model_Group_Chat chat);

    public void showFriendList();

    public void showFriendRequest();

    public void showFindNewFriend();
    
    public void editProfile();

    public void searchAllMessages(String key);
    
    public void showNotification(String message);
}