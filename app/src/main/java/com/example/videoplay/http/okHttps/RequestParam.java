package com.example.videoplay.http.okHttps;

import android.text.TextUtils;

import com.example.videoplay.http.HttpUrls;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/12 12:38
 */
public class RequestParam {

    private List<NameValuePair> params;

    public RequestParam() {
        params = new ArrayList<>();
    }

    public RequestParam(int tag) {
        this();
        switch (tag) {
            case HttpUrls.PANDA:
                put("__version", "1.0.0.1147");
                put("__plat", "android");
                break;
        }
    }

    /**
     * put key-value
     *
     * @param key
     * @param value
     * @return
     */
    public RequestParam put(String key, Object value) {
        for (NameValuePair pair : params) {
            if (TextUtils.equals(key, pair.getKey()))
                return this;
        }
        NameValuePair pair = new NameValuePair(key, value);
        params.add(pair);
        return this;
    }

    /**
     * 获取字符串
     *
     * @return
     */
    public String getParams() {
        String url = "";
        for (NameValuePair pair : params) {
            url += pair.getKeyValue() + "&";
        }
        if (!TextUtils.isEmpty(url)) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }
}
