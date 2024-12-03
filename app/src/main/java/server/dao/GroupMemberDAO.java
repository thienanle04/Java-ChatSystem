package server.dao;

import server.entity.GroupMember;
import java.util.List;

public interface GroupMemberDAO {
    void addGroupMember(GroupMember member);
    List<GroupMember> getGroupMembersByGroupId(int groupId);
    void updateGroupMember(GroupMember member);
    void deleteGroupMember(int groupId, int userId);
}
