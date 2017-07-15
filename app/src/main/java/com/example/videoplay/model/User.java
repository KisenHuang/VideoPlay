package com.example.videoplay.model;

/**
 * @author Huangwy
 * @TIME 2016/4/13 19:23
 */
public class User {
    private String phone;
    private String password;
    private String uId;

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

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
