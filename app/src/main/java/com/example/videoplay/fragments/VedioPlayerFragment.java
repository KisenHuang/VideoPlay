package com.example.videoplay.fragments;

import android.view.KeyEvent;

/**
 * @author Huangwy
 * @TIME 2016/3/21 20:22
 */
public class VedioPlayerFragment {
//    private WebView webView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉应用标题
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
//        waitdialog = new ProgressDialog(this);
//        waitdialog.setTitle("提示");
//        waitdialog.setMessage("视频页面加载中...");
//        waitdialog.setIndeterminate(true);
//        waitdialog.setCancelable(true);
//        waitdialog.show();
//        webView = (WebView) findViewById(R.id.webView);
//        WebSettings ws = webView.getSettings();
//        ws.setBuiltInZoomControls(true);// 隐藏缩放按钮
//// ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕
//        ws.setUseWideViewPort(true);// 可任意比例缩放
//        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
//        ws.setSavePassword(true);
//        ws.setSaveFormData(true);// 保存表单数据
//        ws.setJavaScriptEnabled(true);
//        ws.setGeolocationEnabled(true);// 启用地理定位
//        ws.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
//        ws.setDomStorageEnabled(true);
//        ws.setSupportMultipleWindows(true);// 新加
//        xwebchromeclient = new myWebChromeClient();
//        webView.setWebChromeClient(xwebchromeclient);
//        webView.setWebViewClient(new myWebViewClient());
//        webView.loadUrl("http://look.appjx.cn/mobile_api.php?mod=news&id=12604");
//    }
//
//    public class myWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return false;
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            waitdialog.dismiss();
//        }
//    }
//
//    public class myWebChromeClient extends WebChromeClient {
//        private View xprogressvideo;
//
//        // 播放网络视频时全屏会被调用的方法
//        @Override
//        public void onShowCustomView(View view, CustomViewCallback callback) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            webView.setVisibility(View.INVISIBLE);
//// 如果一个视图已经存在，那么立刻终止并新建一个
//            if (xCustomView != null) {
//                callback.onCustomViewHidden();
//                return;
//            }
//            video_fullView.addView(view);
//            xCustomView = view;
//            xCustomViewCallback = callback;
//            video_fullView.setVisibility(View.VISIBLE);
//        }
//
//        // 视频播放退出全屏会被调用的
//        @Override
//        public void onHideCustomView() {
//            if (xCustomView == null)// 不是全屏播放状态
//                return;
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            xCustomView.setVisibility(View.GONE);
//            video_fullView.removeView(xCustomView);
//            xCustomView = null;
//            video_fullView.setVisibility(View.GONE);
//            xCustomViewCallback.onCustomViewHidden();
//            webView.setVisibility(View.VISIBLE);
//        }
//
//        // 视频加载时进程loading
//        @Override
//        public View getVideoLoadingProgressView() {
//            if (xprogressvideo == null) {
//                LayoutInflater inflater = LayoutInflater
//                        .from(MainActivity.this);
//                xprogressvideo = inflater.inflate(
//                        R.layout.video_loading_progress, null);
//            }
//            return xprogressvideo;
//        }
//    }

}
