package com.kangaroo.backup.Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Hs256Utils {

    /**
     * HS256加密，内部默认使用utf-8编码表示字符串
     * @param msg 待加密原始信息
     * @param secret 密钥
     * @return 加密后的二进制数
     */
    public static byte[] encode(String msg, String secret) {
        byte[] output = null;
        try {
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSHA256.init(key);
            output = hmacSHA256.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
