package com.example.videoplay.activities.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.example.videoplay.BaseListActivity;
import com.example.videoplay.R;
import com.example.videoplay.adapters.VideoListAdapter;
import com.example.videoplay.http.HttpUrls;
import com.example.videoplay.http.okHttps.RequestParam;
import com.example.videoplay.model.MaxUserData;
import com.example.videoplay.model.UserVideoInfo;
import com.example.videoplay.utils.UIUtils;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * 主播视频列表
 */
public class VideoListActivity extends BaseListActivity implements View.OnClickListener {

    private String dm_uid;
    private VideoListAdapter mAdapter;
    private MaxUserData maxUserData;
    private ImageView collect;
    private boolean isCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_video_list);
    }

    private void initListener() {
        findViewById(R.id.collection).setOnClickListener(this);
    }

    private void initView() {
        collect = (ImageView) findViewById(R.id.collect);
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
        startActivity(new Intent(this, VideoWebActivity.class).putExtra("url", positionData.getUrl()).putExtra("title", positionData.getTitle()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collection:
                collect();
                break;
        }
    }

    private void collect() {
        isCollection = !isCollection;

        if (isCollection) collect.setImageResource(R.drawable.is_collect);
        else collect.setImageResource(R.drawable.no_collect);
    }
}
