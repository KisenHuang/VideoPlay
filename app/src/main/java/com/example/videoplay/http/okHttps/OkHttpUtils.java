package com.example.videoplay.http.okHttps;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.videoplay.utils.LogUtil;
import com.example.videoplay.utils.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Huangwy
 * @TIME 2016/3/10 21:08
 */
public class OkHttpUtils {

    private static final String TAG = LogUtil.makeTag(OkHttpUtils.class);

    private static OkHttpClient httpClient;
    private static OkHttpUtils okHttpUtils;

    private static final String CHARSET_NAME = "UTF-8";

    private OkHttpUtils() {
        httpClient = getOkHttpClick();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null)
            okHttpUtils = new OkHttpUtils();
        return okHttpUtils;
    }

    private static OkHttpClient getOkHttpClick() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Utils.DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        builder.readTimeout(Utils.DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        builder.writeTimeout(Utils.DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        builder.connectionPool(new ConnectionPool(3, 5000, TimeUnit.MILLISECONDS));
        return builder.build();
    }

    /**
     * 该不会开启异步线程。
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return httpClient.newCall(request).execute();
    }


    /**
     * 开启异步线程访问网络
     *
     * @param request
     */

    public void enqueue(Request request, final int reqCode, final HttpCallBack callBack) {
        LogUtil.e(TAG, request.url().toString());
        final UIHandler handler = new UIHandler(callBack);
        callBack.onHttpStart(reqCode);
        httpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                Message message = Message.obtain();
                message.what = UIHandler.CALLBACK_FAILURE;
                message.obj = e.toString();
                message.arg1 = reqCode;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.obj = response.body().string();
                message.arg1 = reqCode;
                if (response.isSuccessful()) message.what = UIHandler.CALLBACK_SUCCESS;
                else message.what = UIHandler.CALLBACK_FAILURE;
                handler.sendMessage(message);
            }
        });
    }

    public String getStringFromServer(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private static class UIHandler extends Handler {

        public static final int CALLBACK_FAILURE = 0;
        public static final int CALLBACK_SUCCESS = 1;

        private HttpCallBack callBack;

        public UIHandler(HttpCallBack callBack) {
            super(Looper.getMainLooper());
            this.callBack = callBack;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CALLBACK_SUCCESS:
                    callBack.onHttpSuccess(msg.arg1, (String) msg.obj);
                    callBack.onHttpFinished(msg.arg1);
                    LogUtil.e(TAG, "请求成功：" + msg.obj.toString());
                    break;
                case CALLBACK_FAILURE:
                    callBack.onHttpFailed(msg.arg1, (String) msg.obj);
                    callBack.onHttpFinished(msg.arg1);
                    LogUtil.e(TAG, "请求失败：" + msg.obj.toString());
                    break;
            }
        }
    }
}