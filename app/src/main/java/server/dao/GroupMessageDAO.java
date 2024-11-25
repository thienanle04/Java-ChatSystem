package server.dao;

import server.entity.GroupMessage;
import java.util.List;

public interface GroupMessageDAO {
    void addMessage(GroupMessage message);
    GroupMessage getMessageById(int messageId);
    List<GroupMessage> getMessagesByGroupId(int groupId);
    void updateMessage(GroupMessage message);
    void deleteMessage(int messageId);
}
