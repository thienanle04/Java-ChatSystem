package user.event;

import user.model.Model_Friend_Request;

public interface EventFindNewFriend {

    public void addFriend(Model_Friend_Request friend);

    public void blockFriend(Model_Friend_Request friend);

    public void findNewFriend(Model_Friend_Request req);

}
