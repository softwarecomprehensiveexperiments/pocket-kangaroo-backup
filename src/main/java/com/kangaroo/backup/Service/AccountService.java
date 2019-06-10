package com.kangaroo.backup.Service;

import com.kangaroo.backup.Dao.UserMapper;
import com.kangaroo.backup.Domain.User;

import com.kangaroo.backup.Exception.NotEnoughBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 账户管理系统服务层
 */
@Service
public class AccountService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void addCredit(int userId, int amount) {
        User user = userMapper.loadById(userId);
        user.setProperties(user.getProperties() + amount);
        userMapper.update(user);
    }

    public void pay(int userId, int amount) throws NotEnoughBalanceException {
        User user = userMapper.loadById(userId);
        if(user.getProperties() < amount) {
            throw new NotEnoughBalanceException();
        }
        user.setProperties(user.getProperties() - amount);
        userMapper.update(user);
    }
}
