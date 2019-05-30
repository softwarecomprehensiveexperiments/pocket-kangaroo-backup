package com.kangaroo.backup.Web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

//此处配置test类的运行环境，RunWith负责配置运行类，SpringBootTest负责声明环境
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class RedisConfigTest {

    @Resource
    RedisTemplate<String, String> template;

    @Test
    public void redisTemplate() {
        template.opsForValue().set("id", "1");
        template.opsForValue().append("id", "2");
        template.opsForSet().add("testSet", "1", "2", "3", "4");
        Assert.assertTrue(template.opsForValue().get("id").equals("12"));
        assertTrue(template.opsForSet().isMember("testSet", Integer.toString(1)));
        template.delete(Arrays.asList("id", "testSet"));
    }

}