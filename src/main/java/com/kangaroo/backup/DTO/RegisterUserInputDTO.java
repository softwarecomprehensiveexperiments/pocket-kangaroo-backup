package com.kangaroo.backup.DTO;

import com.kangaroo.backup.Domain.User;
import org.springframework.beans.BeanUtils;

/**
 * DTO class for register operation.
 */
public class RegisterUserInputDTO {

    private String phone;

    private String name;

    private String password;

    private int sex;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public User covertToUser() {
        User user =  new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
