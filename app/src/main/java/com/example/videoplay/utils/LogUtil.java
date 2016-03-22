package com.example.videoplay.utils;

import android.util.Log;

import com.example.videoplay.BuildConfig;

/**
 * log工具类
 * Created by Huangwy on 2015/11/27.
 */
public class LogUtil {
    private final static boolean isLog = BuildConfig.DEBUG;

    public static String makeTag(Class<?> clazz){
        return makeTag(clazz.getSimpleName());
    }

    public static String makeTag(String str){
        return str;
    }

    public static void i(String tag, String content) {
        if (isLog) {
            Log.i(tag, content);
        }
    }

    public static void w(String tag, String content) {
        if (isLog) {
            Log.w(tag, content);
        }
    }

    public static void e(String tag, String content) {
        if (isLog) {
            Log.e(tag, content);
        }
    }
}
