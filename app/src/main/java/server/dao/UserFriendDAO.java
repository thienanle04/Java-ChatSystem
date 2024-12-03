package server.dao;

import server.entity.UserFriend;

import java.util.List;

public interface UserFriendDAO {
    void addFriend(UserFriend friend);
    List<UserFriend> getFriendsByUserId(int userId);
    void updateFriendStatus(UserFriend friend);
    void deleteFriend(int userId, int friendId);
}
