package com.example.videoplay;

import android.app.Application;

import com.example.videoplay.utils.GlideUtils;
import com.wilddog.client.Wilddog;

/**
 * 程序入口类
 * Created by Huangwy on 2015/11/28.
 */
public class AppContext extends Application {

    private static AppContext appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Wilddog.setAndroidContext(this);
        appContext = this;
        initGlide();
    }

    private void initGlide() {
        GlideUtils.getInstance().setImageCacheDir(appContext);
    }

    public static AppContext getInstance() {
        return appContext;
    }

}
