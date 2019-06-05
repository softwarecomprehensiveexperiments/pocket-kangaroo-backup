package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.UpdateUserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void updateUser() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setUserName("chen22");
        updateUserDTO.setUserPhone("1234");
        updateUserDTO.setUserOldPassword("chen123456");
        updateUserDTO.setUserNewPassword("chen1234");
        updateUserDTO.setUserSex(1);
        userService.updateUser(1, updateUserDTO);
    }
}