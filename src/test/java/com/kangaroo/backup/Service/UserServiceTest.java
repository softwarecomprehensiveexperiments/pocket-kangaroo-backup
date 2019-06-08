package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.UpdateUserInputDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void updateUser() {
        UpdateUserInputDTO updateUserInputDTO = new UpdateUserInputDTO();
        updateUserInputDTO.setUserName("chen22");
        updateUserInputDTO.setUserPhone("1234");
        updateUserInputDTO.setUserOldPassword("chen123456");
        updateUserInputDTO.setUserNewPassword("chen1234");
        updateUserInputDTO.setUserSex(1);
        userService.updateUser(1, updateUserInputDTO);
    }
}