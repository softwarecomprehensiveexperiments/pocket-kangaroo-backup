package com.kangaroo.backup.DTO;

public class TokenPreloadDTO {

    private static long JWT_ID_COUNTER = 0;

    private String iss;

    private long iat;

    private long exp;

    private int userId;

    private long jwtId;

    public TokenPreloadDTO() {
        jwtId = ++JWT_ID_COUNTER;
    }

    private String userName;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getJwtId() {
        return jwtId;
    }

}
