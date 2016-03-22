package com.example.videoplay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.videoplay.BaseListFragment;
import com.example.videoplay.activities.news.NewsDetailsActivity;
import com.example.videoplay.adapters.NewsAdapter;
import com.example.videoplay.http.HttpUrls;
import com.example.videoplay.model.NewsData;
import com.example.videoplay.utils.LogUtil;
import com.example.videoplay.utils.UIUtils;
import com.example.videoplay.views.recyclerload.ERecyclerView;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * DOTA 新闻页面
 *
 * @author Huangwy
 * @TIME 2016/3/12 15:46
 */
public class NewsFragment extends BaseListFragment {

    private NewsAdapter mAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        mAdapter = new NewsAdapter(mContext);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.addItemDecoration(new ERecyclerView.SpacesItemDecoration(4));
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
                List<NewsData> newsDataList = JSON.parseArray(JSON.parseObject(data).getString("data"), NewsData.class);
            case HttpUrls.REQ_CODE_LOAD:
                List<NewsData> newsDatas = JSON.parseArray(JSON.parseObject(data).getString("data"), NewsData.class);
                mAdapter.addAll(newsDatas);
                mRecyclerview.setHasMoreItems(UIUtils.checkHasMoreItems(newsDatas));
                break;
        }
    }

    @Override
    public void onLoad() {
        String param = "index" + (mPage++) + ".html";
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.DOTA2_NEWS, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_LOAD);
        LogUtil.e("newFragment", "onLoad");
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        String param = "index.html";
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.DOTA2_NEWS, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_REFRESH);
    }

    @Override
    public void onListItemClick(View view, int position) {
        mContext.startActivity(new Intent(mContext, NewsDetailsActivity.class)
                .putExtra("newsUrl", mAdapter.getItem(position).getUrl())
                .putExtra("newsTitle", mAdapter.getItem(position).getTitle()));
    }
}
