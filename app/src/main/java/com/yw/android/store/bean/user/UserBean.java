package com.yw.android.store.bean.user;

import java.io.Serializable;

/**
 * Created by feng on 2017/9/19.
 */

public class UserBean implements Serializable{

    /**
     * loginName : admin
     * token : 3285c5997f2e471ba67f5ce6ca285927
     * host : 192.168.0.105
     * expiration : 1495779210272
     * rateLimit : 0
     * rateRemaining : 0
     * principal : {"name":"admin","nickname":"admin","mobile":"13912345678","email":null,
     * "avatar":null,"signature":null,"gender":null,"genderName":null,"birthday":null,
     * "idNumber":null,"areaName":"四川省,成都市,青白江区","invitationCode":null,"hasPassword":true,
     * "userTypes":["Member","Employee","Administrator","Author","Cloud"],
     * "registerTime":"2017-04-13 00:55:37"}
     */

    private String loginName;
    private String token;
    private String host;
    private long expiration;
    private String rateLimit;
    private String rateRemaining;
    private PrincipalBean principal;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(String rateLimit) {
        this.rateLimit = rateLimit;
    }

    public String getRateRemaining() {
        return rateRemaining;
    }

    public void setRateRemaining(String rateRemaining) {
        this.rateRemaining = rateRemaining;
    }

    public PrincipalBean getPrincipal() {
        return principal;
    }

    public void setPrincipal(PrincipalBean principal) {
        this.principal = principal;
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "loginName='" + loginName + '\'' +
                ", token='" + token + '\'' +
                ", host='" + host + '\'' +
                ", expiration=" + expiration +
                ", rateLimit='" + rateLimit + '\'' +
                ", rateRemaining='" + rateRemaining + '\'' +
                ", principal=" + principal +
                '}';
    }
}
