package com.example.videoplay.http.okHttps;

/**
 * @author Huangwy
 * @TIME 2016/3/12 13:37
 */
public class NameValuePair {

    private String key;

    private String value;

    public NameValuePair(String key, Object value) {
        this.key = key;
        this.value = String.valueOf(value);
    }

    public String getKey() {
        return key;
    }

    public String getKeyValue() {
        return key + "=" + value;
    }
}
