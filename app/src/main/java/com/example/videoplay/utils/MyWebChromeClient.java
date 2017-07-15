package com.example.videoplay.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.example.videoplay.R;

import java.lang.ref.WeakReference;

/**
 * 自定义WebChromeClient
 *
 * @author Huangwy
 * @TIME 2016/3/26 14:36
 */
public class MyWebChromeClient extends WebChromeClient {
    private View xCustomView;
    private View xprogressvideo;
    private WeakReference<Activity> reference;
    private Window window;
    private FrameLayout fullView;// 全屏时视频加载view
    private WebView webView;
    private CustomViewCallback xCustomViewCallback;
    private OnShowCustomView mShowCustomView;

    public MyWebChromeClient(Activity activity, FrameLayout fullView, WebView webView) {
        this.fullView = fullView;
        this.webView = webView;
        reference = new WeakReference<Activity>(activity);
        window = reference.get().getWindow();
    }

    // 播放网络视频时全屏会被调用的方法
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        full(true);
        mShowCustomView.showCustom();
        reference.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        webView.setVisibility(View.INVISIBLE);
        // 如果一个视图已经存在，那么立刻终止并新建一个
        if (xCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }
        fullView.addView(view);
        xCustomView = view;
        xCustomViewCallback = callback;
        fullView.setVisibility(View.VISIBLE);
    }

    // 视频播放退出全屏会被调用的
    @Override
    public void onHideCustomView() {
        if (xCustomView == null)// 不是全屏播放状态
            return;
        full(false);
        mShowCustomView.hideCustom();
        reference.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        xCustomView.setVisibility(View.GONE);
        fullView.removeView(xCustomView);
        xCustomView = null;
        fullView.setVisibility(View.GONE);
        xCustomViewCallback.onCustomViewHidden();
        webView.setVisibility(View.VISIBLE);
    }

    // 视频加载时进程loading
    @Override
    public View getVideoLoadingProgressView() {
        if (xprogressvideo == null) {
            LayoutInflater inflater = LayoutInflater
                    .from(reference.get());
            xprogressvideo = inflater.inflate(
                    R.layout.video_loading_progress, null);
        }
        return xprogressvideo;
    }

    //网页标题
    @Override
    public void onReceivedTitle(WebView view, String title) {
//        reference.get().setTitle(title);
    }

    /**
     * 判断是否是全屏
     *
     * @return
     */
    public boolean inCustomView() {
        return xCustomView != null;
    }

    /**
     * 是否显示状态栏
     *
     * @param enable
     */
    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            WindowManager.LayoutParams attr = window.getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setAttributes(attr);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void setOnShowCustomView(OnShowCustomView showCustomView) {
        mShowCustomView = showCustomView;
    }

    public interface OnShowCustomView {
        void showCustom();

        void hideCustom();
    }
}