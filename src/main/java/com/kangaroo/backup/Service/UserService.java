package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.LoginCommandDTO;
import com.kangaroo.backup.DTO.RegisterUserDTO;
import com.kangaroo.backup.Domain.LoginLog;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public void loginSuccess(User user) {
        LoginLog loginLog = new LoginLog(user.getLastIp(), user.getId(), new Date());
        loginLogDao.save(loginLog);
    }

    public void register(RegisterUserDTO registerUserDTO) throws UserExistException {
        User user = userDao.loadByName(registerUserDTO.getName());
        if(user == null) {
            throw new UserExistException();
        }
        userDao.save(registerUserDTO.covertToUser());
    }

    public User loginByName(LoginCommandDTO loginCommandDTO) {
        if (!userDao.hasMatchNameAndPassword(loginCommandDTO.getKey(), loginCommandDTO.getPassword())) {
            return null;
        }
        User user = userDao.loadByName(loginCommandDTO.getKey());
        return user;
    }

    public User loginByPhone(LoginCommandDTO loginCommandDTO) {
        if (!userDao.hasMatchPhoneAndPassword(loginCommandDTO.getKey(), loginCommandDTO.getPassword())) {
            return null;
        }
        User user = userDao.loadByName(loginCommandDTO.getKey());
        return user;
    }

    public void logout(User user) {

    }

    public User getUserById(int id) {
        return userDao.loadById(id);
    }
}
