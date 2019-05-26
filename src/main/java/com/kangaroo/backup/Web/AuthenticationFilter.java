package com.kangaroo.backup.Web;

import com.kangaroo.backup.Utils.JWTUtils;
import com.kangaroo.backup.Utils.MatchUriUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*", filterName = "authenticationFilter")
public class AuthenticationFilter extends BaseController implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private FilterConfig filterConfig;
    private static final Set<String> GREEN_URLS = new HashSet<>();
    static {
        GREEN_URLS.add("/task/public");
        GREEN_URLS.add("/user/login");
        GREEN_URLS.add("/user/register");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
            /*HttpSession session = httpServletRequest.getSession(true);
            User user = (User)session.getAttribute("user");
            if(user != null) chain.doFilter(request, response);
            else {

            }*/
            String token = httpServletRequest.getHeader("Token");
            if(token != null && JWTUtils.checkToken(token)) {
                chain.doFilter(request, response);
            }
            else {
                ((HttpServletResponse) response).sendError(81, "请登陆！");
            }
        }
    }
}
