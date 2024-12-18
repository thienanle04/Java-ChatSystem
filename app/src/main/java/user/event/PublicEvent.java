package user.event;

public class PublicEvent {

    private static PublicEvent instance;
    private EventMain eventMain;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventMenuLeft eventMenuLeft;
    private EventUpdateInfo eventUpdateInfo;
    private EventFriendRequest eventFriendRequest;
    private EventFriendList eventFriendList;
    private EventFindNewFriend eventFindNewFriend;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    public void addEventFindNewFriend(EventFindNewFriend event) {
        this.eventFindNewFriend = event;
    }

    public EventFindNewFriend getEventFindNewFriend() {
        return eventFindNewFriend;
    }

    public void addEventFriendList(EventFriendList event) {
        this.eventFriendList = event;
    }

    public EventFriendList getEventFriendList() {
        return eventFriendList;
    }

    public void addEventFriendRequest(EventFriendRequest event) {
        this.eventFriendRequest = event;
    }

    public EventFriendRequest getEventFriendRequest() {
        return eventFriendRequest;
    }
    
    public void addEventUpdateInfo(EventUpdateInfo event) {
        this.eventUpdateInfo = event;
    }

    public EventUpdateInfo getEventUpdateInfo() {
        return eventUpdateInfo;
    }


    private PublicEvent() {

    }

    public void addEventMain(EventMain event) {
        this.eventMain = event;
    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }

    public void addEventLogin(EventLogin event) {
        this.eventLogin = event;
    }

    public void addEventMenuLeft(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }

    public EventMain getEventMain() {
        return eventMain;
    }

    public EventChat getEventChat() {
        return eventChat;
    }

    public EventLogin getEventLogin() {
        return eventLogin;
    }

    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }
}