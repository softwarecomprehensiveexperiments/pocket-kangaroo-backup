package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void testUpdateAndLoad() {
        User user = mapper.loadById(1);
        Assert.assertNotNull(user);
        System.out.println("-------------------" + user.getName());
        Assert.assertTrue(user.getName().equals("chenjifan"));
        user.setName("jifan");
        mapper.update(user);
        User user1 = mapper.loadById(1);
        Assert.assertTrue(user1.getName().equals("jifan"));
    }
}