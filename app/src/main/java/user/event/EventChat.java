package user.event;


import user.model.Model_Chat_Message;
import java.util.LinkedList;
import java.util.HashMap;
import user.model.Model_Group_Chat;
import user.model.Model_Delete_Message;

public interface EventChat {

    public void sendMessage(Model_Chat_Message message);

    public void receiveMessage(Model_Chat_Message message);

    public void initAllChat(HashMap<Integer, LinkedList<Model_Chat_Message>> chats_data);

    public void newChat(Model_Group_Chat groupChat);

    public void deleteAllMessages(Model_Delete_Message req);
}
