package com.example.videoplay;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.example.videoplay.views.recyclerload.ERecyclerView;
import com.example.videoplay.views.recyclerload.RecyclerLoadSupportEmptyLayout;

/**
 * @author Huangwy
 * @TIME 2016/3/23 19:21
 */
public abstract class BaseListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        ERecyclerView.OnLoadListener, ERecyclerView.OnItemClickListener {
    private View mEmptyView;
    protected TextView mEmptyTextView;
    protected RecyclerLoadSupportEmptyLayout mRecyclerview;
    protected ERecyclerView mList;
    protected int mPage;
    protected boolean isFirstLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEmptyView = null;
        mRecyclerview = null;
        mList = null;
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (mRecyclerview != null)
            return;
        mEmptyView = findViewById(android.R.id.empty);
        mEmptyTextView = (TextView) findViewById(R.id.txt_empty);
        mRecyclerview = (RecyclerLoadSupportEmptyLayout) findViewById(R.id.recyclerlayout);
        if (mEmptyView != null) {
            mRecyclerview.setEmptyView(mEmptyView);
        }
        mList = mRecyclerview.getRecycleView();
        mRecyclerview.setOnLoadListener(this);
        mRecyclerview.setOnRefreshListener(this);
        mRecyclerview.setOnItemClickListener(this);
    }

    @Override
    public void onHttpSuccess(int reqcode, String data) {
        super.onHttpSuccess(reqcode, data);
    }

    @Override
    public void onHttpFinished(int reqcode) {
        super.onHttpFinished(reqcode);
        mRecyclerview.setRefreshing(false);
        mRecyclerview.setIsLoading(false);
        isFirstLoad = false;
    }

    @Override
    public void onItemClick(View view, int position) {
        position = position - mRecyclerview.getRecycleView().getHeaderCount();
        onListItemClick(view, position);
    }

    public void onListItemClick(View view, int position) {
    }

    protected void loadData() {
        if (mRecyclerview != null && !mRecyclerview.isRefreshing()) {
            mRecyclerview.setRefreshing(true);
            isFirstLoad = true;
            onRefresh();
        }
    }
}
