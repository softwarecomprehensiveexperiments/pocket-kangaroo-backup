package com.kangaroo.backup.Domain;

import java.util.Date;

/**
 * PO/DO class.
 */
public class LoginLog extends BaseDomain {

    private static final long serialVersionUID = 4526072973133579013L;

    private int loginLogId;

    private int userId;

    private String ip;

    private Date date;

    public LoginLog(String ip, int userId, Date date) {
        this.ip = ip;
        this.userId = userId;
        this.date = date;
    }

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
