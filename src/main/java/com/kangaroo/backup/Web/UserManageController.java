package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.PureStateDTO;
import com.kangaroo.backup.DTO.QueryResult;
import com.kangaroo.backup.DTO.UpdateUserInputDTO;
import com.kangaroo.backup.DTO.UserOutputDTO;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.NoCurrentUserException;
import com.kangaroo.backup.Service.AccountService;
import com.kangaroo.backup.Service.UserService;
import com.kangaroo.backup.Utils.FormatCheckerUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户个人管理控制器，负责响应用户信息获取，基本信息修改，
 */
@RestController
public class UserManageController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/user/currentUser", method = RequestMethod.PUT)
    public PureStateDTO updateUser(@RequestBody UpdateUserInputDTO updateUserInputDTO, HttpServletRequest request) {
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(false);
        boolean isValidPassword = (updateUserInputDTO.getUserOldPassword() == null && updateUserInputDTO.getUserNewPassword() == null) ||
                                    (FormatCheckerUtils.isValidPassword(updateUserInputDTO.getUserOldPassword()) &&
                                        FormatCheckerUtils.isValidPassword(updateUserInputDTO.getUserNewPassword()));
        if(!FormatCheckerUtils.isValidName(updateUserInputDTO.getUserName()) ||
            !FormatCheckerUtils.isValidPhone(updateUserInputDTO.getUserPhone()) ||
              !isValidPassword) {
            pureStateDTO.setDescription("格式不合法");
            return pureStateDTO;
        }
        try {
            User newUser = userService.updateUser(getCurrentUserId(request), updateUserInputDTO);
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

    @RequestMapping(value = "/user/currentUser", method = RequestMethod.GET)
    public QueryResult<UserOutputDTO> queryCurrentUser(HttpServletRequest request) {
        QueryResult<UserOutputDTO> queryResult = new QueryResult<>();
        queryResult.setSuccess(false);
        try {
            User user = userService.getUserById(getCurrentUserId(request));
            assert user != null;
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

    @RequestMapping(value = "/user/credit")
    public PureStateDTO addCredit(@RequestParam int amonut, HttpServletRequest request) {
        PureStateDTO dto = new PureStateDTO();
        dto.setSuccess(false);
        if(amonut <= 0) {
            dto.setDescription("金额需大于0");
            return dto;
        }
        try {
            int userId = getCurrentUserId(request);
            accountService.addCredit(userId, amonut);
            dto.setSuccess(true);
        } catch (NoCurrentUserException e) {
            dto.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return dto;
    }

}
