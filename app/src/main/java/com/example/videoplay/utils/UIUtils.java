package com.example.videoplay.utils;

import android.text.TextUtils;

import java.util.ArrayList;
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
     * string转list
     *
     * @param string
     * @return
     * @Description:
     */
    public static List<String> stringToList(String string) {
        String s;
        if (TextUtils.isEmpty(string))
            return null;
        s = getSplits(string);
        String[] split = string.split(s);
        List<String> list = new ArrayList<>();
        for (String str : split) {
            list.add(str);
        }
        return list;
    }

    private static String getSplits(String s) {
        for (String split : splits) {
            if (s.contains(split))
                return split;
        }
        return " ";
    }

    private static String[] splits = new String[]{",", "，", ".", "。"};

    /**
     * list转string
     *
     * @param list
     * @return
     * @Description:
     */
    public static String listToString(List<String> list) {
        return listToString(list, ',');
    }

    /**
     * list转string
     *
     * @param list
     * @param split 分隔符
     * @return
     * @Description:
     */
    public static String listToString(List<String> list, char split) {
        if (list == null || list.isEmpty())
            return "";
        StringBuffer buffer = new StringBuffer();
        for (String str : list) {
            buffer.append(str).append(split);
        }
        if (buffer.length() > 0)
            buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
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

    /**
     * 手机号正则表达
     */
    public static final String MOBILE = "^(13[0-9]|15[0-35-9]|17[06-8]|18[0-9]|14[57])[0-9]{8}$";

    public static boolean checkPhone(String phone) {
        return phone.matches(MOBILE);
    }

    public static boolean checkPwd(String pwd) {
        return pwd.length() > 5;
    }
}
