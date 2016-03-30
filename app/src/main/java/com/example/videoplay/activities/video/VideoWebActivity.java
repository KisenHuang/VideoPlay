package com.example.videoplay.activities.video;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videoplay.BaseActivity;
import com.example.videoplay.R;
import com.example.videoplay.utils.MyWebChromeClient;
import com.example.videoplay.utils.UIUtils;

public class VideoWebActivity extends BaseActivity implements View.OnClickListener, MyWebChromeClient.OnShowCustomView {

    private String url;
    private String titleName;
    private WebView mWebView;
    private FrameLayout video_fullView;
    private FrameLayout title_layout;
    private ImageView back;
    private TextView title;
    private MyWebChromeClient xwebchromeclient;
    private boolean isVideoViewPopShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_web);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        url = getIntent().getStringExtra("url");
        titleName = getIntent().getStringExtra("title");
        setTitle(titleName);
        mWebView = (WebView) findViewById(R.id.webview);
        video_fullView = (FrameLayout) findViewById(R.id.video_fullView);
        title_layout = (FrameLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        back = (ImageView) findViewById(R.id.back);
    }

    private void initData() {
        title.setText(titleName);
        loadWeb(url);
    }

    private void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                hideCustomView();
                break;
        }
    }

    /**
     * 全屏时按返加键执行退出全屏方法
     */
    public void hideCustomView() {
        xwebchromeclient.onHideCustomView();
    }

    /**
     * 判断是否是全屏
     *
     * @return
     */
    public boolean inCustomView() {
        return xwebchromeclient.inCustomView();
    }


    public void setTitleLayoutVisible() {
        if (isVideoViewPopShow) {
            isVideoViewPopShow = !isVideoViewPopShow;
            title_layout.setVisibility(View.GONE);
        } else {
            isVideoViewPopShow = !isVideoViewPopShow;
            title_layout.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleLayoutGone() {
        isVideoViewPopShow = false;
        title_layout.setVisibility(View.GONE);
    }

    private void loadWeb(String newsUrl) {
        newsUrl = UIUtils.checkUrl(newsUrl);
        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(false);// 隐藏缩放按钮
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSaveFormData(true);// 保存表单数据
        ws.setJavaScriptEnabled(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setDomStorageEnabled(true);
        ws.setSupportMultipleWindows(true);// 新加
        try {
            mWebView.loadUrl(newsUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        xwebchromeclient = new MyWebChromeClient(this, video_fullView, mWebView);
        xwebchromeclient.setOnShowCustomView(this);
        mWebView.setWebViewClient(new DetailsWebViewClient());
        mWebView.setWebChromeClient(xwebchromeclient);
        showProgressDialog();
    }

    @Override
    public void showCustom() {
        setTitleLayoutVisible();
    }

    @Override
    public void hideCustom() {
        setTitleLayoutGone();
    }

    class DetailsWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.equals("http://app.dota2.com.cn/", url)) {
                url = UIUtils.checkUrl(url);
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            dismissProgressDialog();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            dismissProgressDialog();
            showMsg("网页加载出错,请稍后再试!");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
        mWebView.pauseTimers();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        mWebView.resumeTimers();
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.loadUrl("about:blank");
        mWebView.stopLoading();
        video_fullView.removeAllViews();
        mWebView.setWebChromeClient(null);
        mWebView.setWebViewClient(null);
        mWebView.destroy();
        mWebView = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (inCustomView()) {
                hideCustomView();
                return true;
            } else {
                mWebView.loadUrl("about:blank");
                finish();
            }
        }
        return false;
    }
}
