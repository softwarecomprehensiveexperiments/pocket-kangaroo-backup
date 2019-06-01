package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.LoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginLogMapper  {

    List<LoginLog> getLoginLog();

    List<LoginLog> getLoginLogByUserId(int loginLogUserId);

    List<LoginLog> getLoginLogByIp(String loginLogIp);

    void insert(LoginLog loginLogUser);

    void update(LoginLog loginLogUser);

    void delete(int loginLogId);

}
