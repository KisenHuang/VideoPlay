package com.example.videoplay;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.videoplay.views.recyclerload.ERecyclerView;
import com.example.videoplay.views.recyclerload.ERecyclerView.OnItemClickListener;
import com.example.videoplay.views.recyclerload.ERecyclerView.OnLoadListener;
import com.example.videoplay.views.recyclerload.RecyclerLoadSupportEmptyLayout;

/**
 * @author Huangwy
 * @TIME 2016/3/12 17:45
 */
public abstract class BaseListFragment extends BaseFragment implements OnRefreshListener,
        OnLoadListener, OnItemClickListener {

    private View mEmptyView;
    protected TextView mEmptyTextView;
    protected RecyclerLoadSupportEmptyLayout mRecyclerview;
    protected ERecyclerView mList;
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
        position = position - mRecyclerview.getRecycleView().getHeaderCount();
        onListItemClick(view, position);
    }

    public void onListItemClick(View view, int position) {
    }

    protected void loadData() {
        if (mRecyclerview != null && !mRecyclerview.isRefreshing()) {
            mRecyclerview.setRefreshing(true);
            onRefresh();
        }
    }
}
