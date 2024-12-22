package user.event;

import user.model.Model_Friend;
import user.model.Model_New_Group;

public interface EventFriendList {
    public void addFriend(Model_Friend friend);

    public void unFriend(Model_Friend friend);

    public void blockFriend(Model_Friend friend);

    public void filterFriend(String name, String status);

    public void userConnect(int userID);

    public void userDisconnect(int userID);

    public void newGroupChat(Model_New_Group group);
}
