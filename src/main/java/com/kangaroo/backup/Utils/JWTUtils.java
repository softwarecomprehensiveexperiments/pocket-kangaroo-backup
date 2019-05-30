package com.kangaroo.backup.Utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

@Component
public class JWTUtils {

    /**
     * 服务器密钥
     */
    private static String baseSecret;

    /**
     * 注入依赖于普通成员方法，静态变量需要自写注入函数
     * @param secret 注入的密钥
     */
    @Value("${base_secret}")
    public void initBaseSecret(String secret) {
        JWTUtils.baseSecret = secret;
    }

    /**
     * Token基本语法检测，根据这里Base64定义的UrlSafe字符集，两个额外符号为-和_，还有填充符=
     */
    private static final String CHECK_TOKEN_REGEX = "^[a-zA-z0-9\\-_=]+\\.[a-zA-z0-9\\-_=]+\\.[a-zA-z0-9\\-_=]+$";

    /**
     * 获取token的方式是传递header和preload字段
     * @param headerJsonString token的原始header字段
     * @param preloadJsonString token的原始preload字段
     * @return 加密token
     */
    public static String getToken(@NotNull String headerJsonString, @NotNull String preloadJsonString) {
        String headerBase64 = Base64.encodeBase64URLSafeString(headerJsonString.getBytes(StandardCharsets.UTF_8));
        String preloadBase64 = Base64.encodeBase64URLSafeString(preloadJsonString.getBytes(StandardCharsets.UTF_8));
        String combine = headerBase64 + "." + preloadBase64;
        String signature = getSignature(combine);
        return combine + "." + signature;
    }

    /**
     * 验证是否为合法token，包括：基本格式正确，签名正确，未过期。
     * @param token 待验证token
     * @param clazz preload的class，反射调用getExp()
     * @return 验证结果，正确为true
     */
    public static <T>boolean checkToken(String token, Class<T> clazz) {
        if(!token.matches(CHECK_TOKEN_REGEX)) {
            return false;
        }
        String[] s = token.split("\\.");
        String signature = getSignature(s[0] + "." + s[1]);
        //签名错误则返回
        if(!signature.equals(s[2])) {
            return false;
        }
        try {
            String preloadString = new String(Base64.decodeBase64(s[1]), StandardCharsets.UTF_8);
            T preload = JsonUtils.stringToObj(preloadString, clazz);
            //Json转换异常
            if(preload == null) {
                return false;
            }
            //判断过期
            Method m = clazz.getMethod("getExp");
            return (Long)m.invoke(preload) > System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 得到token的id(无校验过程)
     * @param token 目标token
     * @param clazz 用于封装属性的class
     * @return 解析得到的token id
     */
    public static <T>long getPreloadId(String token, Class<T> clazz) {
        String[] s = token.split("\\.");
        String preloadString = new String(Base64.decodeBase64(s[1]), StandardCharsets.UTF_8);
        T preload = JsonUtils.stringToObj(preloadString, clazz);
        long id = -1;
        try {
            Method m = clazz.getMethod("getJwtId");
            id = (Long)m.invoke(preload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 签名算法：HS256加密，Base64表示
     * @param combine 由Header和preload两部分Base64编码组合成的字符串
     * @return 对应签名
     */
    private static String getSignature(String combine) {
        byte[] encode = Hs256Utils.encode(combine, baseSecret);
        return Base64.encodeBase64URLSafeString(encode);
    }
}
