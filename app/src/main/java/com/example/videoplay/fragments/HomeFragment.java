package com.example.videoplay.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.example.videoplay.BaseListFragment;
import com.example.videoplay.R;
import com.example.videoplay.adapters.HomeListAdapter;
import com.example.videoplay.adapters.ImagePagerAdapter;
import com.example.videoplay.http.HttpUrls;
import com.example.videoplay.http.okHttps.RequestParam;
import com.example.videoplay.model.HomePagerData;
import com.example.videoplay.utils.LogUtil;
import com.example.videoplay.views.indicator.CirclePageIndicator;
import com.example.videoplay.views.recyclerload.ERecyclerView;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

public class HomeFragment extends BaseListFragment{

    private ViewPager viewPager;
    private CirclePageIndicator indicator;
    private HomeListAdapter mAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loadData();
    }

    private void initView() {
        //头视图
        View header = LayoutInflater.from(mContext).inflate(R.layout.header_home_fragment, null);
        viewPager = (ViewPager) header.findViewById(R.id.focusViewPager);
        indicator = (CirclePageIndicator) header.findViewById(R.id.focusIndicator);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerview.setLayoutManager(manager);
        mAdapter = new HomeListAdapter(mContext);
        mRecyclerview.setAdapter(mAdapter);
//        mRecyclerview.addHeaderView(header);
    }

    private void loadPager() {
        //请求头部ViewPager图片
        RequestParam param = new RequestParam(HttpUrls.PANDA);
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.PANDA_HOME_PAGER, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_GET);
    }

    private void loadList() {
        //请求视频列表
        RequestParam param = new RequestParam(HttpUrls.PANDA);
        param.put("pagenum", HttpUrls.PAGENUM);
        param.put("hotroom", 1);
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.makeUrl(HttpUrls.PANDA_HOME_LIST, param)))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_REFRESH);
    }

    @Override
    public void onHttpSuccess(int reqcode, String data) {
        super.onHttpSuccess(reqcode, data);
        switch (reqcode) {
            case HttpUrls.REQ_CODE_GET:
                List<HomePagerData> pagerData = JSON.parseArray(JSON.parseObject(data).getString("data"), HomePagerData.class);
                viewPager.setAdapter(new ImagePagerAdapter(mContext, pagerData));
                indicator.setViewPager(viewPager);
                break;
            case HttpUrls.REQ_CODE_REFRESH:
                mAdapter.addAll(null);
                break;
        }
    }

    @Override
    public void onRefresh() {
        loadPager();
        loadList();
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onListItemClick(View view, int position) {
        LogUtil.e("onItemClick","home:"+position);
    }
}
