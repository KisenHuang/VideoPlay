package com.example.videoplay;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videoplay.http.okHttps.HttpCallBack;
import com.example.videoplay.http.okHttps.OkHttpUtils;
import com.example.videoplay.utils.LogUtil;
import com.example.videoplay.views.swipebacklayout.SwipeBackActivityBase;
import com.example.videoplay.views.swipebacklayout.SwipeBackActivityHelper;
import com.example.videoplay.views.swipebacklayout.SwipeBackLayout;

import okhttp3.Request;

/**
 * @author Huangwy
 * @TIME 2016/3/8 19:20
 */
public class BaseActivity extends AppCompatActivity implements SwipeBackActivityBase, HttpCallBack {

    private static final String TAG = LogUtil.makeTag(BaseFragment.class);
    private SwipeBackActivityHelper mHelper;
    protected AppContext appContext;
    protected Context mContext;
    // 标题栏
    protected Toolbar mToolbar;

    protected TextView mToolbarTitle;

    protected View mToolbarDivider;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        appContext = (AppContext) getApplicationContext();
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setProgressDrawable(getResources().getDrawable(R.drawable.empty));
        // 禁止滑动返回
        setSwipeBackEnable(true);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setSubtitle("");
            mToolbar.setTitle("");
            getSupportActionBar().setTitle("");
            getSupportActionBar().setSubtitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(null);
        }
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarDivider = findViewById(R.id.toolbar_divider);
    }

    public void closeToolBar() {
        mToolbar.setVisibility(View.GONE);
        mToolbarTitle.setVisibility(View.GONE);
        mToolbarDivider.setVisibility(View.GONE);
    }

    public void openToolBar() {
        mToolbar.setVisibility(View.VISIBLE);
        mToolbarTitle.setVisibility(View.VISIBLE);
        mToolbarDivider.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        // super.setTitle(titleId);
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(titleId);
        }
    }

    /**
     * 禁止滑动返回
     */
    public void closeSwipeBack() {
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.STATE_IDLE);
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    protected void handlerNet(Request request, final int reqCode) {
        handlerNet(request, reqCode, false);
    }

    protected void handlerNet(Request request, final int reqCode, boolean showDialog) {
        handlerNet(request, reqCode, showDialog, null);
    }

    protected void handlerNet(Request request, final int reqCode, boolean showDialog, String showMsg) {
        if (showDialog)
            showProgressDialog(showMsg);
        OkHttpUtils.getInstance().enqueue(request, reqCode, this);
    }

    @Override
    public void onHttpStart(int reqcode) {

    }

    @Override
    public void onHttpSuccess(int reqcode, String data) {

    }

    @Override
    public void onHttpFailed(int reqcode, String data) {

    }

    @Override
    public void onHttpFinished(int reqcode) {
        dismissProgressDialog();
    }

    protected void showMsg(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
    }

    public void showProgressDialog() {
        showProgressDialog(null);
    }

    public void showProgressDialog(String message) {
        showProgressDialog(message, false);
    }

    /**
     * 显示加载进度框
     *
     * @Description:
     */
    public void showProgressDialog(String message, boolean cancelable) {
        if (mProgressDialog == null)
            return;
        if (!TextUtils.isEmpty(message)) {
            mProgressDialog.setMessage(message);
        } else {
            mProgressDialog.setMessage(getString(R.string.str_loading));
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.setCanceledOnTouchOutside(false);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 取消进度框
     *
     * @Description:
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
