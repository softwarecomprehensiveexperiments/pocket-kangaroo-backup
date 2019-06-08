package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserInputDTO {
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_phone")
    private String userPhone;

    @JsonProperty("user_sex")
    private int userSex;

    @JsonProperty("old_password")
    private String userOldPassword;

    @JsonProperty("new_password")
    private String userNewPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserOldPassword() {
        return userOldPassword;
    }

    public void setUserOldPassword(String userOldPassword) {
        this.userOldPassword = userOldPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }
}
