package com.example.videoplay.activities.video;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.videoplay.BaseListActivity;
import com.example.videoplay.adapters.VideoListAdapter;
import com.example.videoplay.http.HttpUrls;
import com.example.videoplay.http.okHttps.RequestParam;
import com.example.videoplay.model.MaxUserData;
import com.example.videoplay.model.UserVideoInfo;
import com.example.videoplay.utils.LogUtil;
import com.example.videoplay.utils.UIUtils;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

public class VideoListActivity extends BaseListActivity {

    private String dm_uid;
    private VideoListAdapter mAdapter;
    private MaxUserData maxUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        maxUserData = (MaxUserData) getIntent().getSerializableExtra("maxUserData");
        dm_uid = maxUserData.getDm_link();
        setTitle(maxUserData.getUsername());
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        mAdapter = new VideoListAdapter(mContext);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(manager);
    }

    private void initData() {
        loadData();
    }

    @Override
    public void onHttpSuccess(int reqcode, String data) {
        super.onHttpSuccess(reqcode, data);
        switch (reqcode) {
            case HttpUrls.REQ_CODE_REFRESH:
                mAdapter.clear();
            case HttpUrls.REQ_CODE_LOAD:
                List<UserVideoInfo> infos = JSON.parseArray(JSON.parseObject(data).getJSONObject("result").getString("video_list_by_time"), UserVideoInfo.class);
                mAdapter.addAll(infos);
                mRecyclerview.setHasMoreItems(UIUtils.checkHasMoreItems(infos));
                break;
        }
    }

    @Override
    public void onLoad() {
        LogUtil.e("onLoad", "VideoListActivity");
        RequestParam param = new RequestParam(HttpUrls.MAX);
        param.put("game_type", "dota2");
        param.put("dm_uid", dm_uid);
        param.put("offset", ++mPage * HttpUrls.PAGE_OFFSET);
        param.put("limit", HttpUrls.PAGE_OFFSET);
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.MAX_VIDEO_LIST, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_LOAD, true);
    }

    @Override
    public void onRefresh() {
        RequestParam param = new RequestParam(HttpUrls.MAX);
        mPage = 0;
        param.put("game_type", "dota2");
        param.put("dm_uid", dm_uid);
        param.put("offset", mPage);
        param.put("limit", HttpUrls.PAGE_OFFSET);
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.MAX_VIDEO_LIST, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_REFRESH, isFirstLoad);
    }

    @Override
    public void onListItemClick(View view, int position) {
        super.onListItemClick(view, position);
        UserVideoInfo positionData = mAdapter.getPositionData(position);

    }
}
