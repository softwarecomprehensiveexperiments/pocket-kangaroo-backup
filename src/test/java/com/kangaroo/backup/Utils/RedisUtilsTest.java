package com.kangaroo.backup.Utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class RedisUtilsTest {
    @Autowired
    RedisUtils redisUtils;

    @Test
    public void isMemberSet() {
        redisUtils.appendMemberSet("1i", "1", "2", "3");
        redisUtils.deleteMemberSet("1i", "3");
        Assert.assertTrue(redisUtils.isMemberInSet("1i", "1"));
        Assert.assertFalse(redisUtils.isMemberInSet("1i", "3"));
        redisUtils.deleteSet("1i");
        long what = LocalDateTime.now().toLocalDate().toEpochDay();
        int bet = Period.between(LocalDate.now(), LocalDate.of(2019, 5, 29)).getDays();
        System.out.println(what + "----------------------" + bet);
        Assert.assertFalse(redisUtils.isMemberInSet("null", "22"));
    }
}