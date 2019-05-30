package com.kangaroo.backup.Utils;

import com.kangaroo.backup.DTO.TokenPreloadDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTUtilsTest {

    @Test
    public void checkToken() {
        String headerString = "{\"as\":\"test\"}";
        String preload = "{\"userId\":\"123\",\"exp\":\"999999999999\"}";
        String token = JWTUtils.getToken(headerString, preload);
        System.out.println("----------------------------------------------" + token);
        assertFalse(JWTUtils.checkToken(token, TokenPreloadDTO.class));
    }
}