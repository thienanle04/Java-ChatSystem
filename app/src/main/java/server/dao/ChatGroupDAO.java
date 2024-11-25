package server.dao;

import server.entity.ChatGroup;
import java.util.List;

public interface ChatGroupDAO {
    void addChatGroup(ChatGroup group);
    ChatGroup getChatGroupById(int groupId);
    List<ChatGroup> getAllChatGroups();
    void updateChatGroup(ChatGroup group);
    void deleteChatGroup(int groupId);
}

