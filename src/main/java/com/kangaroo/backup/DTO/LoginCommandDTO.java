package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginCommandDTO {

    private String key;

    private String password;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
