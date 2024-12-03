package server.dao;

import server.entity.MessageVisibility;

public interface MessageVisibilityDAO {
    void addVisibility(MessageVisibility visibility);
    MessageVisibility getVisibilityById(int visibilityId);
    void updateVisibility(MessageVisibility visibility);
    void deleteVisibility(int visibilityId);
}
