package com.example.videoplay.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.videoplay.BaseListFragment;
import com.example.videoplay.adapters.ImageViewAdapter;
import com.example.videoplay.html.Html;
import com.example.videoplay.http.HttpUrls;
import com.example.videoplay.model.DotaHeroes;
import com.example.videoplay.utils.LogUtil;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

public class OpenFragment extends BaseListFragment {

    private static final String TAG = LogUtil.makeTag(OpenFragment.class);
    private ImageViewAdapter mAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(mActivity, 3);
        mRecyclerview.setLayoutManager(manager);

        mAdapter = new ImageViewAdapter(mContext);
        mRecyclerview.setAdapter(mAdapter);
    }

    private void initData() {
        onRefresh();
    }

    @Override
    public void onHttpSuccess(int reqcode, String data) {
        super.onHttpSuccess(reqcode, data);
        switch (reqcode) {
            case HttpUrls.REQ_CODE_GET:
                mAdapter.clear();
                List<DotaHeroes> lists = Html.getHeroesHtmlDota2(data);
                LogUtil.e(TAG, "下载成功");
                mAdapter.addAll(lists);
                mRecyclerview.setHasMoreItems(true);
                break;
        }
    }

    @Override
    public void onLoad() {
        LogUtil.e("json", "-----------------------------------");
    }

    @Override
    public void onRefresh() {
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.NEW_DOTA_URL))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_GET);
    }

    @Override
    public void onListItemClick(View view, int position) {
        LogUtil.e("onItemClick", "---open------"+view.hashCode()+"-----------"+position+"------------------");
    }
}
