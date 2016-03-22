package com.example.videoplay.http;

import com.example.videoplay.http.okHttps.RequestParam;

/**
 * 定义Url类
 *
 * @author Huangwy
 * @TIME 2016/3/9 21:28
 */
public class HttpUrls {
    /*************************************** dota网页地址******************************************/
    public static final String OLD_DOTA_URL = "http://dota.tgbus.com/hero/";//Dota地址
    public static final String NEW_DOTA_URL = "http://dota2.tgbus.com/heroes/"; //Dota2地址
    /******************************************dota新闻***********************************************/
    public static final String DOTA2_NEWS = "http://www.dota2.com.cn/wapnews/govnews/";//dota新闻

    /****************************************熊猫TV视频地址****************************************/
    public static final String PANDA_TV = "http://api.m.panda.tv/";//服务器地址
    /**
     * 首页顶部ViewPager
     */
    public static final String PANDA_HOME_PAGER = "ajax_rmd_ads_get";
    /**
     * 首页视频列表
     */
    public static final String PANDA_HOME_LIST = "ajax_get_live_list_by_multicate";

    /*****************************************
     * 请求返回码
     ***************************************************/
    public static final int REQ_CODE_GET = 101;//get 请求码

    public static final int REQ_CODE_REFRESH = 105;//refresh 请求码

    public static final int REQ_CODE_LOAD = 106;//load 请求码

    public static final int REQ_CODE_POST = 107;//post 请求码

    /**
     * 熊猫TV常量
     */
    public static final int PANDA = 1;

    /**
     * 请求调条目内子条目数量
     */
    public static final int PAGENUM = 4;

    /**
     * 熊猫TV接口拼接
     *
     * @param url
     * @param params
     * @return
     */
    public static String makeUrl(String url, RequestParam params) {
        return PANDA_TV + url + "?" + params.getParams();
    }

    public static String makeUrl(String url,String str){
        return url+str;
    }


}