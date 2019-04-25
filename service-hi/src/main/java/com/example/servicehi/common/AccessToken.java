package com.example.servicehi.common;

import java.util.Date;

public class AccessToken {
    private String token;
    private Date createDate;
    public static AccessToken accessToken;

    private AccessToken() {
    }

    public static AccessToken getInstance() {
        if (accessToken == null) {
            accessToken = new AccessToken();
        }
        return accessToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
