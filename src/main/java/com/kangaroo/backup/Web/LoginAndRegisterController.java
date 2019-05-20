package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.*;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.UserExistException;
import com.kangaroo.backup.Service.UserService;
import com.kangaroo.backup.Utils.FormatCheckerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class LoginAndRegisterController extends BaseController {
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(LoginAndRegisterController.class);

    @Autowired
    public void setUserDao(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public RegisterUserOutputDTO register(@RequestBody RegisterUserInputDTO registerUserInputDTO) {
        logger.info("Register request received.");
        RegisterUserOutputDTO registerUserOutputDTO = new RegisterUserOutputDTO();
        registerUserOutputDTO.setSuccess(false);
        //格式检查
        if(!FormatCheckerUtils.isValidPhone(registerUserInputDTO.getPhone()) ||
            !FormatCheckerUtils.isValidName(registerUserInputDTO.getName()) ||
                !FormatCheckerUtils.isValidPassword(registerUserInputDTO.getPassword())) {
            registerUserOutputDTO.setDescription("输入信息格式不合法");
            return registerUserOutputDTO;
        }
        //调用注册接口
        try {
            userService.register(registerUserInputDTO);
        } catch (UserExistException e) {
            registerUserOutputDTO.setDescription("该用户名或手机号已被注册");
            return registerUserOutputDTO;
        }
        registerUserOutputDTO.setSuccess(true);
        return registerUserOutputDTO;
    }

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public QueryResult<UserOutputDTO> login(@RequestBody LoginCommandDTO loginCommand, HttpServletRequest request) {
        logger.info("Login request received from " + request.getLocalAddr() + ".");
        QueryResult<UserOutputDTO> queryResult = new QueryResult<>();
        queryResult.setSuccess(false);
        String key = loginCommand.getKey();
        String password = loginCommand.getPassword();
        //格式检查
        if (!(FormatCheckerUtils.isValidName(key) || FormatCheckerUtils.isValidPhone(key)) ||
                !FormatCheckerUtils.isValidPassword(password)) {
            queryResult.setDescription("输入信息格式不合法");
            return queryResult;
        }
        //尝试登陆
        User user;
        if (FormatCheckerUtils.isValidName(key)) {
            user = userService.loginByName(loginCommand);
        } else {
            user = userService.loginByPhone(loginCommand);
        }
        if(user == null) {
            queryResult.setDescription("用户名或密码不正确");
            return queryResult;
        }
        //登陆成功
        user.setLastDate(new Date());
        user.setLastIp(request.getLocalAddr());
        userService.loginSuccess(user);
        UserOutputDTO userOutputDTO = new UserOutputDTO();
        BeanUtils.copyProperties(user, userOutputDTO);
        queryResult.setSuccess(true);
        queryResult.setT(userOutputDTO);
        return queryResult;
    }

    @RequestMapping(value = "/logout")
    public String logout(@RequestBody int id) {
        return "";
    }
}
