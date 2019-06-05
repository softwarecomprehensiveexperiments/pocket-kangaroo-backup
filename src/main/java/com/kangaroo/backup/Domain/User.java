package com.kangaroo.backup.Domain;

import java.util.Date;

/**
 * PO/DO class.
 */
public class User extends BaseDomain {
    private static final long serialVersionUID = -9085312745591431751L;
    private int userId;
    private String userPhone;
    private String userName;
    private String userPassword;
    private int userSex;
    private int userProperties;
    private String userIcon;
    private String userLastIp;
    private Date userLastDate;

    public static class Sex {
        public static final int BOY = 0;
        public static final int GIRL = 1;

    }

    public User(int userId, String userPhone, String userName, String userPassword, int userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userSex = userSex;
        this.userProperties = 0;
        this.userIcon = null;
        this.userLastIp = null;
    }

    public User(String userPhone, String userName, String userPassword, int userSex) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userSex = userSex;
        this.userProperties = 0;
        this.userIcon = null;
        this.userLastIp = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhone() {
        return userPhone;
    }

    public void setPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getSex() {
        return userSex;
    }

    public void setSex(int userSex) {
        this.userSex = userSex;
    }

    public int getProperties() {
        return userProperties;
    }

    public void setProperties(int userProperties) {
        this.userProperties = userProperties;
    }

    public String getIcon() {
        return userIcon;
    }

    public void setIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getLastIp() {
        return userLastIp;
    }

    public void setLastIp(String userLastIp) {
        this.userLastIp = userLastIp;
    }

    public Date getLastDate() {
        return userLastDate;
    }

    public void setLastDate(Date userLastDate) {
        this.userLastDate = userLastDate;
    }
}
