package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.TokenPreloadDTO;
import com.kangaroo.backup.Exception.NoCurrentUserException;
import com.kangaroo.backup.Utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    /**
     * 获取当前用户ID
     * @param request 当前request
     * @return userId
     * @throws NoCurrentUserException
     */
    public final int getCurrentUserId(HttpServletRequest request) throws NoCurrentUserException {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty()) {
            throw new NoCurrentUserException();
        }
        int userId = JWTUtils.getUserId(token, TokenPreloadDTO.class);
        if(userId == -1) {
            throw new NoCurrentUserException();
        }
        return userId;
    }
}
