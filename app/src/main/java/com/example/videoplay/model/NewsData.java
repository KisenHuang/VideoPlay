package com.example.videoplay.model;

import java.io.Serializable;

/**
 * @author Huangwy
 * @TIME 2016/3/16 20:02
 */
public class NewsData implements Serializable {

    /**
     * id : 183031
     * title : DOTA2隆重推出全新的自定义游戏通行证系统
     * date : 2016-03-16
     * pic : http://img.dota2.com.cn/dota2/da/86/da86e2c479bb7dca80a70a14c02c98531458104750.jpg
     * url : http://www.dota2.com.cn/wapnews/article/details/20160316/183031.html
     * desc : 隆重推出全新的自定义游戏通行证系统，让那些致力于制作和更新自定义游戏的开发者获得支持和回报。
     * showComment : true
     * isVideo : false
     * newstype : 0
     * topicinfo : {}
     */

    private String id;
    private String title;
    private String date;
    private String pic;
    private String url;
    private String desc;
    private String showComment;
    private String isVideo;
    private String newstype;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setShowComment(String showComment) {
        this.showComment = showComment;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getPic() {
        return pic;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    public String getShowComment() {
        return showComment;
    }

    public String getIsVideo() {
        return isVideo;
    }

    public String getNewstype() {
        return newstype;
    }
}
