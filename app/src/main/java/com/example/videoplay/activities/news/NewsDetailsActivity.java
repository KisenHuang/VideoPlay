package com.example.videoplay.activities.news;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videoplay.BaseActivity;
import com.example.videoplay.R;
import com.example.videoplay.utils.UIUtils;

public class NewsDetailsActivity extends BaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private View xCustomView;
    private FrameLayout video_fullView;// 全屏时视频加载view
    private CustomViewCallback xCustomViewCallback;
    private MyWebChromeClient xwebchromeclient;
    private String newsUrl;
    private String newsTitle;
    private FrameLayout title_layout;
    private TextView title;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        newsUrl = getIntent().getStringExtra("newsUrl");
        newsTitle = getIntent().getStringExtra("newsTitle");
        setTitle("DOTA2 NEWS");
        mWebView = (WebView) findViewById(R.id.webview);
        video_fullView = (FrameLayout) findViewById(R.id.video_fullView);
        title_layout = (FrameLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        back = (ImageView) findViewById(R.id.back);
    }

    private void initData() {
        title.setText(newsTitle);
        loadWeb(newsUrl);
    }

    private void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                hideCustomView();
                break;
            case R.id.video:
                title_layout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void loadWeb(String newsUrl) {
        newsUrl = UIUtils.checkUrl(newsUrl);
//        mWebView.getSettings().setJavaScriptEnabled(true);// 是否开启JAVASCRIPT
//        mWebView.getSettings().setBuiltInZoomControls(false);// 是否开启缩放
//        mWebView.getSettings().setDomStorageEnabled(true);// 是否开启Dom存储Api
//        mWebView.getSettings().setSupportZoom(true);
////        mWebView.getSettings().setSupportMultipleWindows(true);
//        mWebView.getSettings().setLoadWithOverviewMode(true);
//        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        mWebView.setDownloadListener(new DownloadListener() {
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
//                                        long contentLength) {
//                if (url != null && url.startsWith("http://"))
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//            }
//        });
        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(false);// 隐藏缩放按钮
        // ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕
//        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
//        ws.setSavePassword(true);
        ws.setSaveFormData(true);// 保存表单数据
        ws.setJavaScriptEnabled(true);
        ws.setGeolocationEnabled(true);// 启用地理定位
        ws.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
        ws.setDomStorageEnabled(true);
        ws.setSupportMultipleWindows(true);// 新加
        try {
            mWebView.loadUrl(newsUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        xwebchromeclient = new MyWebChromeClient();
        mWebView.setWebViewClient(new DetailsWebViewClient());
        mWebView.setWebChromeClient(xwebchromeclient);
        showProgressDialog();
    }

    public class MyWebChromeClient extends WebChromeClient {
        private View xprogressvideo;

        // 播放网络视频时全屏会被调用的方法
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            full(true);
            view.setId(R.id.video);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mWebView.setVisibility(View.INVISIBLE);
            // 如果一个视图已经存在，那么立刻终止并新建一个
            if (xCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            video_fullView.addView(view);
            xCustomView = view;
            xCustomViewCallback = callback;
            video_fullView.setVisibility(View.VISIBLE);
        }

        // 视频播放退出全屏会被调用的
        @Override
        public void onHideCustomView() {
            if (xCustomView == null)// 不是全屏播放状态
                return;
            full(false);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            xCustomView.setVisibility(View.GONE);
            video_fullView.removeView(xCustomView);
            xCustomView = null;
            video_fullView.setVisibility(View.GONE);
            xCustomViewCallback.onCustomViewHidden();
            mWebView.setVisibility(View.VISIBLE);
        }

        // 视频加载时进程loading
        @Override
        public View getVideoLoadingProgressView() {
            if (xprogressvideo == null) {
                LayoutInflater inflater = LayoutInflater
                        .from(NewsDetailsActivity.this);
                xprogressvideo = inflater.inflate(
                        R.layout.video_loading_progress, null);
            }
            return xprogressvideo;
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
        return (xCustomView != null);
    }

    /**
     * 是否显示状态栏
     *
     * @param enable
     */
    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    class DetailsWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
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
    public void onPause() {// 继承自Activity
        super.onPause();
        mWebView.onPause();
        mWebView.pauseTimers();
    }

    @Override
    public void onResume() {// 继承自Activity
        super.onResume();
        mWebView.onResume();
        mWebView.resumeTimers();
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
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
                NewsDetailsActivity.this.finish();
            }
        }
        return false;
    }
}
