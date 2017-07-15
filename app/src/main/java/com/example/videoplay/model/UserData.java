package com.example.videoplay.model;

import java.io.Serializable;

/**
 * 本地用户数据
 *
 * @author Huangwy
 * @TIME 2016/4/10 11:47
 */
public class UserData implements Serializable {

    private String userAvatar;

    private String userAickName;

    private String localCode;

    private String uToken;

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getUserAickName() {
        return userAickName;
    }

    public void setUserAickName(String userAickName) {
        this.userAickName = userAickName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getuToken() {
        return uToken;
    }

    public void setuToken(String uToken) {
        this.uToken = uToken;
    }
}
