package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.LoginCommandDTO;
import com.kangaroo.backup.DTO.RegisterUserInputDTO;
import com.kangaroo.backup.DTO.UpdateUserDTO;
import com.kangaroo.backup.Dao.LoginLogMapper;
import com.kangaroo.backup.Dao.UserMapper;
import com.kangaroo.backup.Domain.LoginLog;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.UserExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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

    /**
     * 处理登陆成功的事务
     * @param user 目标用户
     */
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
        if (userMapper.getMatchNameAndPasswordCount(loginCommandDTO.getKey(), loginCommandDTO.getPassword()) == 0) {
            return null;
        }
        User user = userMapper.loadByName(loginCommandDTO.getKey());
        return user;
    }

    public User loginByPhone(LoginCommandDTO loginCommandDTO) {
        if (userMapper.getMatchPhoneAndPasswordCount(loginCommandDTO.getKey(), loginCommandDTO.getPassword()) == 0) {
            return null;
        }
        return userMapper.loadByPhone(loginCommandDTO.getKey());
    }

    public User getUserById(int id) {
        return userMapper.loadById(id);
    }

    public boolean isDuplicateName(String name) {
        return userMapper.loadByName(name) != null;
    }

    public boolean isDuplicatePhone(String phone) {
        return userMapper.loadByPhone(phone) != null;
    }

    public User updateUser(int id, UpdateUserDTO updateUserDTO) {
        User user = userMapper.loadById(id);
        logger.info("up : [{}]", updateUserDTO.getUserName());
        logger.info("Updating user id: [{}], old password: [{}], input password: [{}]", id, user.getPassword(), updateUserDTO.getUserOldPassword());
        if(updateUserDTO.getUserOldPassword() != null || !user.getPassword().equals(updateUserDTO.getUserOldPassword())) {
            return null;
        }
        user.setName(updateUserDTO.getUserName());
        user.setPhone(updateUserDTO.getUserPhone());
        user.setSex(updateUserDTO.getUserSex());
        user.setPassword(updateUserDTO.getUserNewPassword());
        userMapper.update(user);
        return user;
    }
}
