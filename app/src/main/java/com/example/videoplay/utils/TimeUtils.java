package com.example.videoplay.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Huangwy
 * @TIME 2016/3/12 14:37
 */
public class TimeUtils {
    public static final String time = "";
    private static final SimpleDateFormat DATEFORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATEFORMAT_YMD2 = new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat DATEFORMAT_YMDHM2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private static final SimpleDateFormat DATEFORMAT_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat DATEFORMAT_MDHM = new SimpleDateFormat("MM月dd日 HH:mm");
    private static final SimpleDateFormat DATEFORMAT_DH = new SimpleDateFormat("dd天HH小时");

    private static final SimpleDateFormat DATEFORMAT_MDHM2 = new SimpleDateFormat("MM-dd HH:mm");
    private static final SimpleDateFormat IMAGE_DETAIL_CREATE_ON = new SimpleDateFormat("MM月dd日HH:mm");

    public static String convertYMD(long time) {
        return DATEFORMAT_YMD.format(new Date(time));
    }

    public static String convertYMD2(long time) {
        return DATEFORMAT_YMD2.format(new Date(time));
    }

    public static String convertYMDHM(long time) {
        return DATEFORMAT_YMDHM.format(new Date(time));
    }

    public static String convertDH(long time) {
        return DATEFORMAT_DH.format(new Date(time));
    }

    public static String convertYMDHM2(long time) {
        return DATEFORMAT_YMDHM2.format(new Date(time));
    }

    public static String convertMDHM(long time) {
        return DATEFORMAT_MDHM.format(new Date(time));
    }

    public static String convertMDHM2(long time) {
        return DATEFORMAT_MDHM2.format(new Date(time));
    }

    public static String convertSS(long milliSec) {
        return String.valueOf(milliSec / 1000);
    }

    public static String formatForImageDetail(long milliTime) {
        return IMAGE_DETAIL_CREATE_ON.format(new Date(milliTime));
    }
}
