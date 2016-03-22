package com.example.videoplay;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.videoplay.views.recyclerload.RecyclerLoadSupportEmptyLayout;
import com.example.videoplay.views.recyclerload.WrapperRecyclerView;
import com.example.videoplay.views.recyclerload.WrapperRecyclerView.OnItemClickListener;
import com.example.videoplay.views.recyclerload.WrapperRecyclerView.OnLoadListener;

/**
 * @author Huangwy
 * @TIME 2016/3/12 17:45
 */
public abstract class BaseListFragment extends BaseFragment implements OnRefreshListener,
        OnLoadListener, OnItemClickListener {

    private View mEmptyView;
    protected TextView mEmptyTextView;
    protected RecyclerLoadSupportEmptyLayout mRecyclerview;
    protected WrapperRecyclerView mList;
    protected int mPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ensureList();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            mRecyclerview.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEmptyView = null;
        mRecyclerview = null;
        mList = null;
    }

    private void ensureList() {
        View root = getView();
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        mEmptyView = root.findViewById(android.R.id.empty);
        mEmptyTextView = (TextView) root.findViewById(R.id.txt_empty);
        mRecyclerview = (RecyclerLoadSupportEmptyLayout) root.findViewById(R.id.recyclerlayout);
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
    }

    @Override
    public void onItemClick(View view, int position) {
        onListItemClick(view, position);
    }

    public void onListItemClick(View view, int position) {
    }

    protected void loadData() {
        onRefresh();
    }
}