package server.dao;

import server.entity.LoginHistory;

import java.util.List;

public interface LoginHistoryDAO {
    void addLoginHistory(LoginHistory loginHistory);
    List<LoginHistory> getLoginHistoryByUserId(int userId);
}
