package com.example.videoplay;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.videoplay.http.okHttps.HttpCallBack;
import com.example.videoplay.http.okHttps.OkHttpUtils;
import com.example.videoplay.utils.LogUtil;

import okhttp3.Request;

/**
 * @author Huangwy
 * @TIME 2016/3/9 21:15
 */
public class BaseFragment extends Fragment implements HttpCallBack {

    private static final String TAG = LogUtil.makeTag(BaseFragment.class);
    protected Context mContext;
    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = (BaseActivity) getActivity();
    }

    protected void handlerNet(Request request, final int reqCode) {
        handlerNet(request, reqCode, false);
    }

    protected void handlerNet(Request request, final int reqCode, boolean showDialog) {
        handlerNet(request, reqCode, showDialog, null);
    }

    protected void handlerNet(Request request, final int reqCode, boolean showDialog, String showMsg) {
        if (showDialog)
            mActivity.showProgressDialog(showMsg);
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
        mActivity.dismissProgressDialog();
    }
}
