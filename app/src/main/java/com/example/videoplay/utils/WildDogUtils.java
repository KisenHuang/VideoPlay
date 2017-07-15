package com.example.videoplay.utils;

import com.wilddog.client.Wilddog;

/**
 * WildDogUtils 野狗服务器工具类
 *
 * @author Huangwy
 * @TIME 2016/4/13 12:58
 */
public class WildDogUtils {

    public static final String ROOT_URL = "https://small-black.wilddogio.com/";

    private static WildDogUtils instance;
    private final Wilddog ref;

    private WildDogUtils() {
        ref = new Wilddog(ROOT_URL);
//        ref.goOnline();
    }

    public static WildDogUtils getInstance() {
        if (instance == null)
            instance = new WildDogUtils();
        return instance;
    }

    /**
     * 获取根目录
     *
     * @return
     */
    public Wilddog getDefaultRef() {
        return ref;
    }
}
