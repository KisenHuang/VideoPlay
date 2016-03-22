package com.example.videoplay.model;

import java.io.Serializable;

/**
 * @author Huangwy
 * @TIME 2016/3/22 21:33
 */
public class Dota2Info implements Serializable{

    /**
     * id : 2009
     * ver : 1
     * createtime : 2015-10-16 15:18:53
     * updatetime : 2016-03-22 21:21:05
     * name : 粉丝都冲到JJC第一了，我不能忍
     * hostid : 3014864
     * person_num : 276762
     * announcement :
     * classification : {"ename":"dota2","cname":"DOTA2"}
     * pictures : {"img":"http://i5.pdim.gs/d7221ac7acb71b23566f177312fda1fd/w338/h190.jpeg"}
     * status : 2
     * start_time : 1446206194
     * end_time : 1446206192
     * duration : 12446678
     * schedule : 0
     * remind_switch : 1
     * remind_content :
     * level : 10
     * stream_status : 1
     * classify_switch : 1
     * reliable : 1
     * banned_reason :
     * unlock_time : 0
     * speak_interval : 3
     * person_num_thres : 0
     * reduce_ratio : 0
     * person_switch : 0
     * watermark_switch : 2
     * watermark_loc : 1
     * room_key : dbcb5219e367aaf19832c039a9da0446
     * fans : 0
     * userinfo : {"nickName":"伍声2009","rid":3014864,"avatar":"http://i1.pdim.gs/t01c12c21d5c0529951.png","userName":"PandaTv3014864"}
     */

    private String id;
    private String ver;
    private String createtime;
    private String updatetime;
    private String name;
    private String hostid;
    private String person_num;
    private String announcement;
    /**
     * ename : dota2
     * cname : DOTA2
     */

    private ClassificationEntity classification;
    /**
     * img : http://i5.pdim.gs/d7221ac7acb71b23566f177312fda1fd/w338/h190.jpeg
     */

    private PicturesEntity pictures;
    private String status;
    private String start_time;
    private String end_time;
    private String duration;
    private String schedule;
    private String remind_switch;
    private String remind_content;
    private String level;
    private String stream_status;
    private String classify_switch;
    private String reliable;
    private String banned_reason;
    private String unlock_time;
    private String speak_interval;
    private String person_num_thres;
    private String reduce_ratio;
    private String person_switch;
    private String watermark_switch;
    private String watermark_loc;
    private String room_key;
    private String fans;
    /**
     * nickName : 伍声2009
     * rid : 3014864
     * avatar : http://i1.pdim.gs/t01c12c21d5c0529951.png
     * userName : PandaTv3014864
     */

    private UserinfoEntity userinfo;

    public void setId(String id) {
        this.id = id;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid;
    }

    public void setPerson_num(String person_num) {
        this.person_num = person_num;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public void setClassification(ClassificationEntity classification) {
        this.classification = classification;
    }

    public void setPictures(PicturesEntity pictures) {
        this.pictures = pictures;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setRemind_switch(String remind_switch) {
        this.remind_switch = remind_switch;
    }

    public void setRemind_content(String remind_content) {
        this.remind_content = remind_content;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStream_status(String stream_status) {
        this.stream_status = stream_status;
    }

    public void setClassify_switch(String classify_switch) {
        this.classify_switch = classify_switch;
    }

    public void setReliable(String reliable) {
        this.reliable = reliable;
    }

    public void setBanned_reason(String banned_reason) {
        this.banned_reason = banned_reason;
    }

    public void setUnlock_time(String unlock_time) {
        this.unlock_time = unlock_time;
    }

    public void setSpeak_interval(String speak_interval) {
        this.speak_interval = speak_interval;
    }

    public void setPerson_num_thres(String person_num_thres) {
        this.person_num_thres = person_num_thres;
    }

    public void setReduce_ratio(String reduce_ratio) {
        this.reduce_ratio = reduce_ratio;
    }

    public void setPerson_switch(String person_switch) {
        this.person_switch = person_switch;
    }

    public void setWatermark_switch(String watermark_switch) {
        this.watermark_switch = watermark_switch;
    }

    public void setWatermark_loc(String watermark_loc) {
        this.watermark_loc = watermark_loc;
    }

    public void setRoom_key(String room_key) {
        this.room_key = room_key;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public void setUserinfo(UserinfoEntity userinfo) {
        this.userinfo = userinfo;
    }

    public String getId() {
        return id;
    }

    public String getVer() {
        return ver;
    }

    public String getCreatetime() {
        return createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public String getName() {
        return name;
    }

    public String getHostid() {
        return hostid;
    }

    public String getPerson_num() {
        return person_num;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public ClassificationEntity getClassification() {
        return classification;
    }

    public PicturesEntity getPictures() {
        return pictures;
    }

    public String getStatus() {
        return status;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getDuration() {
        return duration;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getRemind_switch() {
        return remind_switch;
    }

    public String getRemind_content() {
        return remind_content;
    }

    public String getLevel() {
        return level;
    }

    public String getStream_status() {
        return stream_status;
    }

    public String getClassify_switch() {
        return classify_switch;
    }

    public String getReliable() {
        return reliable;
    }

    public String getBanned_reason() {
        return banned_reason;
    }

    public String getUnlock_time() {
        return unlock_time;
    }

    public String getSpeak_interval() {
        return speak_interval;
    }

    public String getPerson_num_thres() {
        return person_num_thres;
    }

    public String getReduce_ratio() {
        return reduce_ratio;
    }

    public String getPerson_switch() {
        return person_switch;
    }

    public String getWatermark_switch() {
        return watermark_switch;
    }

    public String getWatermark_loc() {
        return watermark_loc;
    }

    public String getRoom_key() {
        return room_key;
    }

    public String getFans() {
        return fans;
    }

    public UserinfoEntity getUserinfo() {
        return userinfo;
    }

    public static class ClassificationEntity {
        private String ename;
        private String cname;

        public void setEname(String ename) {
            this.ename = ename;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getEname() {
            return ename;
        }

        public String getCname() {
            return cname;
        }
    }

    public static class PicturesEntity {
        private String img;

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg() {
            return img;
        }
    }

    public static class UserinfoEntity {
        private String nickName;
        private int rid;
        private String avatar;
        private String userName;

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public int getRid() {
            return rid;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getUserName() {
            return userName;
        }
    }
}
