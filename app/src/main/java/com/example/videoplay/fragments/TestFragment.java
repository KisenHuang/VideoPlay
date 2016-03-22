package com.example.videoplay.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videoplay.BaseFragment;
import com.example.videoplay.R;
import com.example.videoplay.adapters.ImageViewAdapter;
import com.example.videoplay.html.Html;
import com.example.videoplay.http.HttpUrls;
import com.example.videoplay.model.DotaHeroes;
import com.example.videoplay.views.swiperefreshandload.SwipeRefreshLayout;
import com.example.videoplay.views.swiperefreshandload.SwipeRefreshLayout.OnLoadListener;
import com.example.videoplay.views.swiperefreshandload.SwipeRefreshLayout.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @author Huangwy
 * @TIME 2016/3/15 19:43
 */
public class TestFragment extends BaseFragment implements OnRefreshListener, OnLoadListener {
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private ImageViewAdapter mAdapter;
    private List<DotaHeroes> lists = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        GridLayoutManager manager = new GridLayoutManager(mActivity, 3);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ImageViewAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeLayout.setMode(SwipeRefreshLayout.Mode.BOTH);
        mSwipeLayout.setLoadNoFull(false);
    }

    private void initData() {
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.NEW_DOTA_URL))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_GET);
    }

    @Override
    public void onHttpSuccess(int reqcode, String data) {
        super.onHttpSuccess(reqcode, data);
        switch (reqcode) {
            case HttpUrls.REQ_CODE_GET:
                lists.clear();
                lists.addAll(Html.getHeroesHtmlDota2(data));
                mAdapter.clear();
                mAdapter.addAll(lists);
                mSwipeLayout.setRefreshing(false);
                break;
        }
    }

    @Override
    public void onLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setLoading(false);
                mAdapter.addAll(lists);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        Request request = new Request.Builder()
                .url(HttpUrl.parse(HttpUrls.NEW_DOTA_URL))
                .build();
        handlerNet(request, HttpUrls.REQ_CODE_GET);
    }
}
