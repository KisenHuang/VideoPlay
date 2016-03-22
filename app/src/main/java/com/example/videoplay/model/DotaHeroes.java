package com.example.videoplay.model;

/**
 * Created by Huangwy on 2015/11/28.
 */
public class DotaHeroes {
    /**
     * 英雄头像的地址
     */
    private String icon_url;
    /**
     * 英雄详情地址
     */
    private String detail_url;
    /**
     * 英雄名称
     */
    private String title;

    /**
     * 英雄类型
     */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DotaHeroes{" +
                "icon_url='" + icon_url + '\'' +
                ", detail_url='" + detail_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
