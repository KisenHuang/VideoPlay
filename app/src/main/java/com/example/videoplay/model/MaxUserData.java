package com.example.videoplay.model;

import java.io.Serializable;

/**
 * @author Huangwy
 * @TIME 2016/3/23 20:07
 */
public class MaxUserData implements Serializable {

    /**
     * avatar : http://7xj7i6.com2.z0.glb.qiniucdn.com/@/api/video_users_max_plus.png2.jpg
     * count : 16499
     * dm_link : hot_all
     * game_type : dota2
     * recent_time : 1458726944.0000
     * show_type : 1
     * username : 最新视频
     */

    private String avatar;
    private int count;
    private String dm_link;
    private String game_type;
    private float recent_time;
    private int show_type;
    private String username;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDm_link(String dm_link) {
        this.dm_link = dm_link;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public void setRecent_time(float recent_time) {
        this.recent_time = recent_time;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getCount() {
        return count;
    }

    public String getDm_link() {
        return dm_link;
    }

    public String getGame_type() {
        return game_type;
    }

    public float getRecent_time() {
        return recent_time;
    }

    public int getShow_type() {
        return show_type;
    }

    public String getUsername() {
        return username;
    }
}
