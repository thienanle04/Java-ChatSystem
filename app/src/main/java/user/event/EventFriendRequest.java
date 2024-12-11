package user.event;

import user.model.Model_Friend_Request;

public interface EventFriendRequest {
    public void acceptFriendRequest(Model_Friend_Request response);

    public void rejectFriendRequest(Model_Friend_Request response);
}
