package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.LoginCommandDTO;
import com.kangaroo.backup.DTO.RegisterUserInputDTO;
import com.kangaroo.backup.Dao.LoginLogMapper;
import com.kangaroo.backup.Dao.UserMapper;
import com.kangaroo.backup.Domain.LoginLog;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setLoginLogMapper(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    public void loginSuccess(User user) {
        LoginLog loginLog = new LoginLog(user.getLastIp(), user.getUserId(), new Date());
        loginLogMapper.insert(loginLog);
    }

    public void register(RegisterUserInputDTO registerUserInputDTO) throws UserExistException {
        if(isDuplicateName(registerUserInputDTO.getName())) {
            throw new UserExistException("Duplicate name.");
        }
        if(isDuplicatePhone(registerUserInputDTO.getPhone())) {
            throw new UserExistException("Duplicate phone.");
        }
        userMapper.insert(registerUserInputDTO.covertToUser());
    }

    public User loginByName(LoginCommandDTO loginCommandDTO) {
       // if (userMapper.getMatchNameAndPasswordCount(loginCommandDTO.getKey(), loginCommandDTO.getPassword()) == 0) {
            return null;
        //}
        //User user = userMapper.loadByName(loginCommandDTO.getKey());
        //return user;
    }

    public User loginByPhone(LoginCommandDTO loginCommandDTO) {
        //if (userMapper.getMatchPhoneAndPasswordCount(loginCommandDTO.getKey(), loginCommandDTO.getPassword()) == 0) {
            return null;
        //}
        //User user = userMapper.loadByPhone(loginCommandDTO.getKey());
        //return user;
    }

    public void logout(User user) {

    }

    public User getUserById(int id) {
        return null;
        //return userMapper.loadById(id);
    }

    public boolean isDuplicateName(String name) {
        return userMapper.loadByName(name) != null;
    }

    public boolean isDuplicatePhone(String phone) {
        return userMapper.loadByPhone(phone) != null;
    }
}
