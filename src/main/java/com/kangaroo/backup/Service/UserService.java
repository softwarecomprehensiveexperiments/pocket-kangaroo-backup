package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.LoginCommandInputDTO;
import com.kangaroo.backup.DTO.RegisterUserInputDTO;
import com.kangaroo.backup.DTO.UpdateUserInputDTO;
import com.kangaroo.backup.DTO.UserOutputDTO;
import com.kangaroo.backup.Dao.LoginLogMapper;
import com.kangaroo.backup.Dao.UserMapper;
import com.kangaroo.backup.Domain.LoginLog;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.UserExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private TransactionService transactionService;

    private UserMapper userMapper;

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

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

    public User loginByName(LoginCommandInputDTO loginCommandInputDTO) {
        if (userMapper.getMatchNameAndPasswordCount(loginCommandInputDTO.getKey(), loginCommandInputDTO.getPassword()) == 0) {
            return null;
        }
        User user = userMapper.loadByName(loginCommandInputDTO.getKey());
        return user;
    }

    public User loginByPhone(LoginCommandInputDTO loginCommandInputDTO) {
        if (userMapper.getMatchPhoneAndPasswordCount(loginCommandInputDTO.getKey(), loginCommandInputDTO.getPassword()) == 0) {
            return null;
        }
        return userMapper.loadByPhone(loginCommandInputDTO.getKey());
    }


    public UserOutputDTO getUserOutputDTOById(int id) {
        User user = userMapper.loadById(id);
        return getUserOutputDTOByUser(user);
    }

    public UserOutputDTO getUserOutputDTOByUser(User user) {
        UserOutputDTO userOutputDTO = new UserOutputDTO();
        BeanUtils.copyProperties(user, userOutputDTO);
        int[] receData = transactionService.getUserStatisticsData(user.getUserId());
        userOutputDTO.setUserDoingReceiveTaskCount(receData[0]);
        userOutputDTO.setUserCompletedReceiveTaskCount(receData[1]);
        int[] releData = taskService.getUserStatisticsData(user.getUserId());
        userOutputDTO.setUserDoingReleaseTaskCount(releData[0]);
        userOutputDTO.setUserCompletedReleaseTaskCount(releData[1]);
        return userOutputDTO;
    }

    public boolean isDuplicateName(String name) {
        return userMapper.loadByName(name) != null;
    }

    public boolean isDuplicatePhone(String phone) {
        return userMapper.loadByPhone(phone) != null;
    }

    public User updateUser(int id, UpdateUserInputDTO updateUserInputDTO) {
        User user = userMapper.loadById(id);
        if(updateUserInputDTO.getUserOldPassword() != null && !user.getPassword().equals(updateUserInputDTO.getUserOldPassword())) {
            return null;
        }
        user.setName(updateUserInputDTO.getUserName());
        user.setPhone(updateUserInputDTO.getUserPhone());
        user.setSex(updateUserInputDTO.getUserSex());
        user.setPassword(updateUserInputDTO.getUserNewPassword());
        userMapper.update(user);
        return user;
    }

    public String getName(int userId) {
        User user = userMapper.loadById(userId);
        return user.getName();
    }
}
