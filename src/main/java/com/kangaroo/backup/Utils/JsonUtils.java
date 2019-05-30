package com.kangaroo.backup.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * 处理Json字符串/类的工具类
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转字符串
     * @param obj 目标对象
     * @param <T> 模板，无需传入
     * @return Json字符串
     */
    public static <T>String objToString(@NotNull T obj) {
        if(obj == null) {
            return null;
        }
        try {
            return (obj instanceof String) ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转对象
     * @param jsonString 目标字符串
     * @param clazz 转换类
     * @param <T> 模板，无需传入
     * @return 类对应的对象实例
     */
    public static <T>T stringToObj(String jsonString, Class<T> clazz) {
        if(jsonString == null || jsonString.isEmpty() || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
