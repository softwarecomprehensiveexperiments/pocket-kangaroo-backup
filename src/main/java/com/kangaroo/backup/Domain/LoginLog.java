package com.kangaroo.backup.Domain;

import java.util.Date;

/**
 * PO/DO class.
 */
public class LoginLog extends BaseDomain {
    private static final long serialVersionUID = 4526072973133579013L;
    private int loginLogId;
    private int loginLogUserId;
    private String loginLogIp;
    private Date loginLogDate;

    public LoginLog(String loginLogIp, int loginLogUserId, Date loginLogDate) {
        this.loginLogIp = loginLogIp;
        this.loginLogUserId = loginLogUserId;
        this.loginLogDate = loginLogDate;
    }

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public String getLoginLogIp() {
        return loginLogIp;
    }

    public void setLoginLogIp(String loginLogIp) {
        this.loginLogIp = loginLogIp;
    }

    public int getLoginLogUserId() {
        return loginLogUserId;
    }

    public void setLoginLogUserId(int loginLogUserId) {
        this.loginLogUserId = loginLogUserId;
    }

    public Date getLoginLogDate() {
        return loginLogDate;
    }

    public void setLoginLogDate(Date loginLogDate) {
        this.loginLogDate = loginLogDate;
    }
}
