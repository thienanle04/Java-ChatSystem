package user.event;

import user.model.Model_Group_Chat;
import user.model.Model_Friend;

public interface EventMain {

    public void showLoading(boolean show);

    public void initChat();

    public void selectChat(Model_Group_Chat chat);

    public void selectChat(Model_Friend friend);

    public void updateChat(Model_Group_Chat chat);

    public void showFriendList();

    public void showFriendRequest();

    public void showFindFriend();
    
    public void editProfile();
    
    public void goFindNewFriend();
    
    public void goViewFriendList();
    
    public void goViewFriendRequest();

    public void showNotification(String message);
}