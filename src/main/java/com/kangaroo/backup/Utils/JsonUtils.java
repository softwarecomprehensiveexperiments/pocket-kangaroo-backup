package com.kangaroo.backup.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

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
