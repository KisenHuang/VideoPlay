package com.example.videoplay.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/16 20:58
 */
public class UIUtils {
    /**
     * 判断是否有下一页
     *
     * @param list
     * @return
     * @Description:
     */
    public static boolean checkHasMoreItems(List<?> list) {
        if (list == null || list.isEmpty())
            return false;
        return true;
    }

    public static boolean checkHasMoreItems(List<?> list, int maxItems) {
        if (list == null || list.isEmpty() || list.size() < maxItems)
            return false;
        return true;
    }

    /**
     * 检测Url是否合法
     *
     * @param url
     * @return
     */
    public static String checkUrl(String url) {
        if (TextUtils.isEmpty(url))
            return "";
        if (url.startsWith("http://") || url.startsWith("https://"))
            return url;
        else return "http://" + url;
    }
}
