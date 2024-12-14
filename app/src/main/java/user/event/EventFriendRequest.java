package user.event;

import user.model.Model_Friend_Request;

public interface EventFriendRequest {
    public void acceptFriendRequest(Model_Friend_Request response);

    public void rejectFriendRequest(Model_Friend_Request response);

    public void addNewFriendRequest(Model_Friend_Request resquest);

    public void searchFriendRequest(String keyword);
}
