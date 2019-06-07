package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.*;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.NoCurrentUserException;
import com.kangaroo.backup.Service.UserService;
import com.kangaroo.backup.Utils.FormatCheckerUtils;
import com.kangaroo.backup.Utils.JWTUtils;
import com.sun.tools.javac.util.Assert;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户个人管理控制器，负责响应用户信息获取，基本信息修改，
 */
@RestController
public class UserManagerController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/updateCurrentUser", method = RequestMethod.POST)
    public PureStateDTO updateUser(@RequestBody UpdateUserDTO updateUserDTO, HttpServletRequest request) {
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(false);
        boolean isValidPassword = (updateUserDTO.getUserOldPassword() == null && updateUserDTO.getUserNewPassword() == null) ||
                                    (FormatCheckerUtils.isValidPassword(updateUserDTO.getUserOldPassword()) &&
                                        FormatCheckerUtils.isValidPassword(updateUserDTO.getUserNewPassword()));
        if(!FormatCheckerUtils.isValidName(updateUserDTO.getUserName()) ||
            !FormatCheckerUtils.isValidPhone(updateUserDTO.getUserPhone()) ||
              !isValidPassword) {
            pureStateDTO.setDescription("格式不合法");
            return pureStateDTO;
        }
        try {
            User newUser = userService.updateUser(getCurrentUserId(request), updateUserDTO);
            if(newUser == null) {
                pureStateDTO.setDescription("原密码错误");
                return pureStateDTO;
            }
            pureStateDTO.setSuccess(true);
            return pureStateDTO;
        } catch (NoCurrentUserException e) {
            e.printStackTrace();
            pureStateDTO.setSuccess(false);
            pureStateDTO.setDescription("认证出现未知错误");
        }
        return null;
    }

    @RequestMapping(value = "/user/queryCurrentUser", method = RequestMethod.GET)
    public QueryResult<UserOutputDTO> queryCurrentUser(HttpServletRequest request) {
        QueryResult<UserOutputDTO> queryResult = new QueryResult<>();
        queryResult.setSuccess(false);
        try {
            User user = userService.getUserById(getCurrentUserId(request));
            Assert.checkNonNull(user);
            UserOutputDTO userOutputDTO = new UserOutputDTO();
            BeanUtils.copyProperties(user, userOutputDTO);
            queryResult.setSuccess(true);
            queryResult.setT(userOutputDTO);
            return queryResult;
        } catch (NoCurrentUserException e) {
            e.printStackTrace();
            queryResult.setDescription("当前无登录用户");
        }
        return queryResult;
    }

//    @RequestMapping(value = "/user/query")
}
