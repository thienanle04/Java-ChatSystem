package user.event;

import user.model.Model_Group_Chat;
import java.util.List;

public interface EventMenuLeft {

    public void newChat(List<Model_Group_Chat> chats);

    public void newChat(Model_Group_Chat chat);

    public void userConnect(int userID);

    public void userDisconnect(int userID);
}
