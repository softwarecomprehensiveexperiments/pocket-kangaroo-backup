package com.kangaroo.backup.Domain;

import java.util.Date;

/**
 * PO/DO class.
 */
public class User extends BaseDomain {
    private static final long serialVersionUID = -9085312745591431751L;
    private int userId;
    private String phone;
    private String name;
    private String password;
    private int sex;
    private int properties;
    private String icon;
    private String lastIp;
    private Date lastDate;

    public static class Sex {
        public static final int BOY = 0;
        public static final int GIRL = 1;
    }

    public User(String phone, String name, String password, int sex) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.properties = 0;
        this.icon = null;
        this.lastIp = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getProperties() {
        return properties;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
