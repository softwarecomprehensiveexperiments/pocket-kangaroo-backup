package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.LoginLog;

import java.util.List;

public interface LoginLogMapper  {

    List<LoginLog> getLoginLog();

    List<LoginLog> getLoginLogByUserId(int userId);

    List<LoginLog> getLoginLogByIp(String ip);

    void insert(LoginLog loginLogUser);

    void update(LoginLog loginLogUser);

    void delete(int loginLogId);

}
