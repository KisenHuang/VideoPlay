package com.example.videoplay.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/24 21:28
 */
public class UserVideoInfo implements Serializable {

    /**
     * class : 1
     * create_time : 1458790300.0000
     * create_time_desc : 2016-03-24 19:31:40
     * link : XMTUxMDc5Njk3Mg==
     * play_times : 4,227
     * play_times_num : 4227
     * segs : [{"desc":"标清","seg_type":"flv","type":"flv","url":"http://pl.youku.com/playlist/m3u8?vid=377699243&time=1458825668&ts=1458825668&ctype=12&token=2504&keyframe=0&sid=545882566885012c415bf&ev=1&type=flv&ep=dyaREkGPU8gG7SvaiD8bNCjjcSVZXP0N8RmMiNBmA9QmTei7&oip=2096831764"}]
     * thumb_img : http://r1.ykimg.com/0541040856F3CF446A0A3F045CA1BE3A
     * time_len : 1:06:00
     * title : 好汉杯第三周 IG VS NB.Y第二局
     * uid : UMTcxMzI3OTE2
     * url : http://v.youku.com/v_show/id_XMTUxMDc5Njk3Mg==.html?from=y1.7-1.2
     * url_info : {"Referer":"http://v.youku.com/","User_Agent":"Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4","url":"http://play.youku.com/play/get.json?vid=XMTUxMDc5Njk3Mg==&ct=12"}
     * user_info : {"avatar":"http://g4.ykimg.com/0130391F484E41616F1C7C028D906B75183E8B-9A38-2B7F-5B50-AF992D357737","class":1,"dm_link":"haitao","order":4,"type":"dota2","uid":"UMTcxMzI3OTE2","username":"海涛"}
     * username : 海涛
     * vtag : dota2
     * zone_time :
     */

    private String create_time;
    private String create_time_desc;
    private String link;
    private String play_times;
    private int play_times_num;
    private String thumb_img;
    private String time_len;
    private String title;
    private String uid;
    private String url;
    /**
     * Referer : http://v.youku.com/
     * User_Agent : Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4
     * url : http://play.youku.com/play/get.json?vid=XMTUxMDc5Njk3Mg==&ct=12
     */

    private UrlInfoEntity url_info;
    /**
     * avatar : http://g4.ykimg.com/0130391F484E41616F1C7C028D906B75183E8B-9A38-2B7F-5B50-AF992D357737
     * class : 1
     * dm_link : haitao
     * order : 4
     * type : dota2
     * uid : UMTcxMzI3OTE2
     * username : 海涛
     */

    private UserInfoEntity user_info;
    private String username;
    private String vtag;
    private String zone_time;
    /**
     * desc : 标清
     * seg_type : flv
     * type : flv
     * url : http://pl.youku.com/playlist/m3u8?vid=377699243&time=1458825668&ts=1458825668&ctype=12&token=2504&keyframe=0&sid=545882566885012c415bf&ev=1&type=flv&ep=dyaREkGPU8gG7SvaiD8bNCjjcSVZXP0N8RmMiNBmA9QmTei7&oip=2096831764
     */

    private List<SegsEntity> segs;

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setCreate_time_desc(String create_time_desc) {
        this.create_time_desc = create_time_desc;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPlay_times(String play_times) {
        this.play_times = play_times;
    }

    public void setPlay_times_num(int play_times_num) {
        this.play_times_num = play_times_num;
    }

    public void setThumb_img(String thumb_img) {
        this.thumb_img = thumb_img;
    }

    public void setTime_len(String time_len) {
        this.time_len = time_len;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrl_info(UrlInfoEntity url_info) {
        this.url_info = url_info;
    }

    public void setUser_info(UserInfoEntity user_info) {
        this.user_info = user_info;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setVtag(String vtag) {
        this.vtag = vtag;
    }

    public void setZone_time(String zone_time) {
        this.zone_time = zone_time;
    }

    public void setSegs(List<SegsEntity> segs) {
        this.segs = segs;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getCreate_time_desc() {
        return create_time_desc;
    }

    public String getLink() {
        return link;
    }

    public String getPlay_times() {
        return play_times;
    }

    public int getPlay_times_num() {
        return play_times_num;
    }

    public String getThumb_img() {
        return thumb_img;
    }

    public String getTime_len() {
        return time_len;
    }

    public String getTitle() {
        return title;
    }

    public String getUid() {
        return uid;
    }

    public String getUrl() {
        return url;
    }

    public UrlInfoEntity getUrl_info() {
        return url_info;
    }

    public UserInfoEntity getUser_info() {
        return user_info;
    }

    public String getUsername() {
        return username;
    }

    public String getVtag() {
        return vtag;
    }

    public String getZone_time() {
        return zone_time;
    }

    public List<SegsEntity> getSegs() {
        return segs;
    }

    public static class UrlInfoEntity {
        private String Referer;
        private String User_Agent;
        private String url;

        public void setReferer(String Referer) {
            this.Referer = Referer;
        }

        public void setUser_Agent(String User_Agent) {
            this.User_Agent = User_Agent;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getReferer() {
            return Referer;
        }

        public String getUser_Agent() {
            return User_Agent;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class UserInfoEntity {
        private String avatar;
        private int classX;
        private String dm_link;
        private int order;
        private String type;
        private String uid;
        private String username;

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setClassX(int classX) {
            this.classX = classX;
        }

        public void setDm_link(String dm_link) {
            this.dm_link = dm_link;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public int getClassX() {
            return classX;
        }

        public String getDm_link() {
            return dm_link;
        }

        public int getOrder() {
            return order;
        }

        public String getType() {
            return type;
        }

        public String getUid() {
            return uid;
        }

        public String getUsername() {
            return username;
        }
    }

    public static class SegsEntity {
        private String desc;
        private String seg_type;
        private String type;
        private String url;

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setSeg_type(String seg_type) {
            this.seg_type = seg_type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDesc() {
            return desc;
        }

        public String getSeg_type() {
            return seg_type;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }
    }
}
