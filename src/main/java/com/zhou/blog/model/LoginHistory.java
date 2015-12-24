package com.zhou.blog.model;

/**
 * Created by zhouwenchao on 15/12/9.
 */
public class LoginHistory {
    private String loginName;
    private String ipAddress;
    private String loginTime;
    private String type;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LoginHistory(String loginName, String ipAddress, String loginTime, String type) {
        this.loginName = loginName;
        this.ipAddress = ipAddress;
        this.loginTime = loginTime;
        this.type = type;
    }

    public LoginHistory(){
    }
}
