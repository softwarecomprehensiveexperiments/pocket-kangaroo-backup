package com.kangaroo.backup.Web;

import com.kangaroo.backup.Constant.TokenConstant;
import com.kangaroo.backup.DTO.*;
import com.kangaroo.backup.Domain.User;
import com.kangaroo.backup.Exception.UserExistException;
import com.kangaroo.backup.Service.UserService;
import com.kangaroo.backup.Utils.FormatCheckerUtils;
import com.kangaroo.backup.Utils.JWTUtils;
import com.kangaroo.backup.Utils.JsonUtils;
import com.kangaroo.backup.Utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 负责登入、登出和注册的控制器类
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class LoginAndRegisterController extends BaseController {

    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    private static final Logger logger = LoggerFactory.getLogger(LoginAndRegisterController.class);

    @Autowired
    public void setUserDao(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public PureStateDTO register(@RequestBody RegisterUserInputDTO registerUserInputDTO) {
        logger.info("Register request received.");
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(false);
        //格式检查
        if (!FormatCheckerUtils.isValidPhone(registerUserInputDTO.getPhone()) ||
                !FormatCheckerUtils.isValidName(registerUserInputDTO.getName()) ||
                !FormatCheckerUtils.isValidPassword(registerUserInputDTO.getPassword())) {
            pureStateDTO.setDescription("输入信息格式不合法");
            return pureStateDTO;
        }
        //调用注册接口
        try {
            userService.register(registerUserInputDTO);
        } catch (UserExistException e) {
            pureStateDTO.setDescription("该用户名或手机号已被注册");
            return pureStateDTO;
        }
        pureStateDTO.setSuccess(true);
        return pureStateDTO;
    }

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public QueryResult<UserOutputDTO> login(@RequestBody LoginCommandDTO loginCommand,
                                            HttpServletRequest request, HttpServletResponse response) {
        logger.info("Login request received from " + request.getLocalAddr() + ".");
        QueryResult<UserOutputDTO> queryResult = new QueryResult<>();
        queryResult.setSuccess(false);
        String key = loginCommand.getKey();
        String password = loginCommand.getPassword();
        //格式检查
        boolean isValidKey = (FormatCheckerUtils.isValidName(key) || FormatCheckerUtils.isValidPhone(key));
        if (!isValidKey || !FormatCheckerUtils.isValidPassword(password)) {
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
        if (user == null) {
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
        //生成Token
        TokenPreloadDTO preload = new TokenPreloadDTO();
        preload.setIss(TokenConstant.SERVER_ISS);
        preload.setIat(System.currentTimeMillis());
        preload.setExp(System.currentTimeMillis() + TokenConstant.EXP_TIME);
        preload.setUserId(userOutputDTO.getId());
        preload.setUserName(userOutputDTO.getName());
        String token = JWTUtils.getToken(TokenConstant.JWT_HEADER_STRING, JsonUtils.objToString(preload));
        response.setHeader("Authorization", token);
        //添加redis缓存
        redisUtils.appendTokenSetAuto(TokenConstant.REDIS_KEY, String.valueOf(preload.getJwtId()));
        return queryResult;
    }

    /**
     * 负责登出服务
     * Token是利用redis缓存管理的，登出/更改密码服务实现起来比较简单
     * 缺点：分布式难以实现，和token的设定有误差，和session混为一潭
     *
     * @param request 从request头部提取token信息
     * @return 返回处理结果
     */
    @RequestMapping(value = "/logout")
    public PureStateDTO logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        long id = JWTUtils.getPreloadId(token, TokenPreloadDTO.class);
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(redisUtils.deleteTokenSetAuto(TokenConstant.REDIS_KEY, String.valueOf(id)));
        return pureStateDTO;
    }
}
