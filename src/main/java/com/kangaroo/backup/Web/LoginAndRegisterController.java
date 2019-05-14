package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.LoginCommandDTO;
import com.kangaroo.backup.DTO.RegisterUserDTO;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.UserExistException;
import com.kangaroo.backup.Service.UserService;
import com.kangaroo.backup.Utils.FormatChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class LoginAndRegisterController {
    private UserService userService;

    @Autowired
    public void setUserDao(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register")
    public String register(@RequestBody RegisterUserDTO registerUserDTO) {
        //格式检查
        if(!FormatChecker.isValidPhone(registerUserDTO.getPhone()) ||
            !FormatChecker.isValidName(registerUserDTO.getName()) ||
                FormatChecker.isValidPassword(registerUserDTO.getPassword())) {
            return "invalid";
        }
        //调用注册接口
        try {
            userService.register(registerUserDTO);
        } catch (UserExistException e) {
            return "have exist";
        }
        return "ok";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestBody LoginCommandDTO loginCommand, HttpServletRequest request) {
        String key = loginCommand.getKey();
        String password = loginCommand.getPassword();
        //格式检查
        if (!(FormatChecker.isValidName(key) || FormatChecker.isValidPhone(key)) ||
                FormatChecker.isValidPassword(password)) {
            return "invalid";
        }
        //尝试登陆
        User user;
        if (FormatChecker.isValidName(key)) {
            user = userService.loginByName(loginCommand);
        } else {
            user = userService.loginByPhone(loginCommand);
        }
        if(user == null) {
            return "Incorrect key or pas";
        }
        //登陆成功
        user.setLastDate(new Date());
        user.setLastIp(request.getLocalAddr());
        userService.loginSuccess(user);
        return "ok";
    }
    @RequestMapping(value = "/logout")
    public String logout(@RequestBody int id) {
        return "";
    }
}
