package com.example.videoplay.views.recyclerload;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.videoplay.R;

/**
 * @author Huangwy
 * @TIME 2016/3/12 22:56
 */
public class RecyclerLoadSupportEmptyLayout extends SwipeRefreshLayout {

    private ERecyclerView recycleView;

    private LinearLayoutManager layoutManager;

    public RecyclerLoadSupportEmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        createRecyclerView(context, attrs);
        setColorSchemeColors(context.getResources().getColor(R.color.colorPrimary));
    }

    /**
     * 创建RecyclerView
     *
     * @param context
     * @param attrs
     */
    private void createRecyclerView(Context context, AttributeSet attrs) {
        recycleView = new ERecyclerView(context, attrs);
        recycleView.setId(android.R.id.list);
        recycleView.setOnScrollListener();
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        addView(recycleView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager != null)
            recycleView.setLayoutManager(layoutManager);
    }

    /**
     * 设置Item间隔
     */
    public void addItemDecoration(RecyclerView.ItemDecoration decor, int index) {
        recycleView.addItemDecoration(decor, index);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        recycleView.addItemDecoration(decor);
    }

    /**
     * 设置加载更多的事件监听
     *
     * @param listener
     * @Description:
     */
    public void setOnLoadListener(ERecyclerView.OnLoadListener listener) {
        recycleView.setOnLoadListener(listener);
    }

    public void addHeaderView(View header) {
        recycleView.addHeaderView(header);
    }

    /**
     * 条目点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(ERecyclerView.OnItemClickListener listener) {
        recycleView.setOnItemClickListener(listener);
    }

    public void setEmptyView(View emptyView) {
        recycleView.setEmptyView(emptyView);
    }

    /**
     * 返回当前是否还有更多数据
     *
     * @return
     * @Description:
     */
    public boolean hasMoreItems() {
        return recycleView.hasMoreItems();
    }

    /**
     * 设置当前是否还有更多数据
     *
     * @param has
     * @Description:
     */
    public void setHasMoreItems(boolean has) {
        recycleView.setHasMoreItems(has);
        recycleView.setIsLoading(false);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recycleView.setAdapter(adapter);
    }

    /**
     * 返回当前是否正在刷新或加载
     *
     * @return
     * @Description:
     */
    public boolean isLoading() {
        return isRefreshing() || recycleView.isLoading();
    }

    public void setIsLoading(boolean isLoading) {
        recycleView.setIsLoading(isLoading);
    }

    public ERecyclerView getRecycleView() {
        return recycleView;
    }

}
