package user.event;

import user.model.Model_Group_Chat;
import user.model.Model_Friend;
import user.model.Model_Friend_Request;

import java.util.List;

public interface EventMenuLeft {

    public void newChat(List<Model_Group_Chat> chats);

    public void newChat(Model_Group_Chat chat);

    public void selectChat(Model_Friend friend);

    public void selectChat(Model_Friend_Request friend);

    public void userConnect(int userID);

    public void userDisconnect(int userID);
}
