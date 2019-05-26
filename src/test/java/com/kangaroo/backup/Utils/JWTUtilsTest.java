package com.kangaroo.backup.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTUtilsTest {

    @Test
    public void checkToken() {
        String headerString = "{\"as\":\"test\"}";
        String preload = "{\"userId\":\"123\"}";
        String token = JWTUtils.getToken(headerString, preload);
        assertTrue(JWTUtils.checkToken(token));
    }
}