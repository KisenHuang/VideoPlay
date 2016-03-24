package com.example.videoplay.activities.video;

import android.os.Bundle;

import com.example.videoplay.BaseListActivity;
import com.example.videoplay.http.HttpUrls;
import com.example.videoplay.http.okHttps.RequestParam;

import okhttp3.HttpUrl;
import okhttp3.Request;

public class VideoListActivity extends BaseListActivity {

    private String dm_uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        dm_uid = getIntent().getStringExtra("dm_uid");

    }

    private void initData() {
        loadData();
    }

    @Override
    public void onHttpSuccess(int reqcode, String data) {
        super.onHttpSuccess(reqcode, data);
        switch (reqcode) {
            case HttpUrls.REQ_CODE_REFRESH:

                break;
            case HttpUrls.REQ_CODE_LOAD:

                break;
        }
    }

    @Override
    public void onLoad() {
        RequestParam param = new RequestParam(HttpUrls.MAX);
        param.put("game_type", "dota2");
        param.put("dm_uid", dm_uid);
        param.put("offset", ++mPage);
        param.put("limit", HttpUrls.PAGE_SIZE);
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.MAX_VIDEO_LIST, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_REFRESH);
    }

    @Override
    public void onRefresh() {
        RequestParam param = new RequestParam(HttpUrls.MAX);
        mPage = 0;
        param.put("game_type", "dota2");
        param.put("dm_uid", dm_uid);
        param.put("offset", mPage);
        param.put("limit", HttpUrls.PAGE_SIZE);
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.MAX_VIDEO_LIST, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_REFRESH);
    }
}
