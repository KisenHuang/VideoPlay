package com.example.videoplay.http.okHttps;

/**
 * 自定义回调类
 * @author Huangwy
 * @TIME 2016/3/11 20:28
 */
public interface HttpCallBack{

    void onHttpStart(int reqcode);

    void onHttpSuccess(int reqcode, String data);

    void onHttpFailed(int reqcode, String data);

    void onHttpFinished(int reqcode);

}
