package com.kangaroo.backup.Utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

@Component
public class JWTUtils {
    //服务器密钥
    @Value("${secret}")
    private static String secret;

    //Token基本语法检测，根据Base64标准字符集，额外的符号为+和/
    private static final String CHECK_TOKEN_REGEX = "^[a-zA-z0-9\\/\\+]+\\.[a-zA-z0-9\\/\\+]+\\.[a-zA-z0-9\\/\\+]+$";

    public static String getToken(@NotNull String headerJsonString, @NotNull String preloadJsonString) {
        String headerBase64 = Base64.encodeBase64String(headerJsonString.getBytes(StandardCharsets.UTF_8));
        String preloadBase64 = Base64.encodeBase64String(preloadJsonString.getBytes(StandardCharsets.UTF_8));
        String combine = headerBase64 + "." + preloadBase64;
        String signature = getSignature(combine);
        String token = combine + "." + signature;
        return token;
    }

    //验证是否为合法token
    public static boolean checkToken(@NotNull String token) {
        if(!token.matches(CHECK_TOKEN_REGEX)) {
            return false;
        }
        String[] s = token.split(".");
        String signature = getSignature(s[0] + "." + s[1]);
        return signature.equals(s[3]);
    }

    //签名算法：HS256加密，Base64表示
    private static String getSignature(String combine) {
        byte[] encode = Hs256Utils.encode(combine, secret);
        return Base64.encodeBase64String(encode);
    }
}
