package com.kangaroo.backup.Utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

@Component
public class JWTUtils {
    //服务器密钥
    private static String secret;

    //注入依赖普通成员方法，静态变量需要自写注入函数
    @Value("${secret}")
    public void initSecret(String secret) {
        JWTUtils.secret = secret;
    }

    //Token基本语法检测，根据这里Base64定义的UrlSafe字符集，两个额外符号为-和_，还有填充符=
    private static final String CHECK_TOKEN_REGEX = "^[a-zA-z0-9\\-_=]+\\.[a-zA-z0-9\\-_=]+\\.[a-zA-z0-9\\-_=]+$";

    public static String getToken(@NotNull String headerJsonString, @NotNull String preloadJsonString) {
        String headerBase64 = Base64.encodeBase64URLSafeString(headerJsonString.getBytes(StandardCharsets.UTF_8));
        String preloadBase64 = Base64.encodeBase64URLSafeString(preloadJsonString.getBytes(StandardCharsets.UTF_8));
        String combine = headerBase64 + "." + preloadBase64;
        String signature = getSignature(combine);
        return combine + "." + signature;
    }

    //验证是否为合法token
    public static boolean checkToken(@NotNull String token) {
        if(!token.matches(CHECK_TOKEN_REGEX)) {
            return false;
        }
        String[] s = token.split("\\.");
        String signature = getSignature(s[0] + "." + s[1]);
        return signature.equals(s[2]);
    }

    //签名算法：HS256加密，Base64表示
    private static String getSignature(String combine) {
        byte[] encode = Hs256Utils.encode(combine, secret);
        return Base64.encodeBase64URLSafeString(encode);
    }
}
