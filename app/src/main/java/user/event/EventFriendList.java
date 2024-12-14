package user.event;

import user.model.Model_Friend;

public interface EventFriendList {
    public void addFriend(Model_Friend friend);

    public void unFriend(Model_Friend friend);

    public void blockFriend(Model_Friend friend);

    public void filterFriend(String name, String status);

    public void userConnect(int userID);

    public void userDisconnect(int userID);
}
