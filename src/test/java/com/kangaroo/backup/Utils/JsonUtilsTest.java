package com.kangaroo.backup.Utils;

import com.kangaroo.backup.DTO.QueryResult;
import com.kangaroo.backup.Domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonUtilsTest {

    public static class TestObj{
        int id;
    }

    @Test
    public void objToString() {
        TestObj testObj = new TestObj();
        testObj.id = 2;
        QueryResult<User> queryResult = new QueryResult<>();
        queryResult.setT(new User("phone", "name2", "123", 1));
        queryResult.setSuccess(true);
        queryResult.setDescription("test");
        System.out.println(JsonUtils.objToString(queryResult));
        assertEquals(JsonUtils.objToString(testObj), "{\"id\":2}");
    }

    @Test
    public void stringToObj() {
        TestObj testObj = JsonUtils.stringToObj("{\"id\":3}", TestObj.class);
        assertEquals(testObj.id, 3);
    }
}