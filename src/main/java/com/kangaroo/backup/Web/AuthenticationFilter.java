package com.kangaroo.backup.Web;

import com.kangaroo.backup.Constant.TokenConstant;
import com.kangaroo.backup.DTO.TokenPreloadDTO;
import com.kangaroo.backup.JWT.JWTUtils;
import com.kangaroo.backup.Utils.MatchUriUtils;
import com.kangaroo.backup.Redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 第一层过滤器，负责认证
 */
@WebFilter(urlPatterns = "/*", filterName = "authenticationFilter")
public class AuthenticationFilter extends BaseController implements Filter {

    private RedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private FilterConfig filterConfig;

    private static final Set<String> GREEN_URLS = new HashSet<>();
    static {
        GREEN_URLS.add("/task/public");
        GREEN_URLS.add("/user/login");
        GREEN_URLS.add("/user/register");
        GREEN_URLS.add("/in");
        GREEN_URLS.add("/*Test");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String uri = httpServletRequest.getRequestURI();
        if(MatchUriUtils.isMatchedUri(uri, GREEN_URLS)) {
            chain.doFilter(request, response);
        }
        else {
            logger.info("Url: " + ((HttpServletRequest) request).getRequestURI() + " being filtering.");
            String token = httpServletRequest.getHeader("Authorization");
            //是否为合法Token
            if(token != null && JWTUtils.checkToken(token, TokenPreloadDTO.class)) {
                //是否在redis缓存中
                if(redisUtils.isTokenInSetAuto(TokenConstant.REDIS_TOKEN_KEY, String.valueOf(JWTUtils.getPreloadId(token, TokenPreloadDTO.class)))) {
                    chain.doFilter(request, response);
                }
                else {
                    httpServletResponse.sendError(403, "登陆信息已失效，请重新登陆");
                }
            }
            else {
                httpServletResponse.sendError(403, "未登陆或登陆信息过期");
            }
        }
    }
}
