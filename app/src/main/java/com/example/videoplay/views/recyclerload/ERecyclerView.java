package com.example.videoplay.views.recyclerload;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Map;

/**
 * 支持Empty的RecyclerView
 * 1、加载更多已经实现
 * 2、setEmpty已实现
 *
 * @author Huangwy
 * @TIME 2016/3/12 20:07
 */
public class ERecyclerView extends RecyclerView {

    /**
     * item 类型
     */
    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_HEADER = 1;//头部--支持头部增加一个headerView
    public final static int TYPE_FOOTER = 2;//底部--往往是loading_more
    public final static int TYPE_LIST = 3;//代表item展示的模式是list模式
    public final static int TYPE_STAGGER = 4;//代码item展示模式是网格模式
    private float mLastY = -1; // save event y
    private boolean isTouchingScreen = false;//手指是否触摸屏幕
    private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
    private final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >= 50px
    private boolean mEnablePullLoad;
    private boolean mPullLoading;

    private View emptyView;

    public OnItemClickListener mOnItemClickListener;

    protected OnLoadListener mOnLoadListener;

    //是否还有更多数据
    private boolean hasMoreItems = false;
    //是否正在加载更多
    private boolean mIsLoading = false;

    /**
     * 自定义实现了头部和底部加载更多的adapter
     */
    private OnLayoutScrollListener mOnScrollListener;
    private AutoLoadAdapter mAutoLoadAdapter;

    public ERecyclerView(Context context) {
        this(context, null);
    }

    public ERecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ERecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }


    @Override
    public AutoLoadAdapter getAdapter() {
        return mAutoLoadAdapter;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            if (adapter instanceof AutoLoadAdapter) {
                mAutoLoadAdapter = (AutoLoadAdapter) adapter;
                mAutoLoadAdapter.setOnItemClickListener(mOnItemClickListener);
            }
            else throw new IllegalFormatConversionException('/', AutoLoadAdapter.class);
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        emptyObserver.onChanged();
    }

    /**
     * 添加头部view并展示
     *
     * @param header
     */
    public void addHeaderView(View header) {
        mAutoLoadAdapter.addHeaderView(header);
        setHeaderEnable(true);
    }

    /**
     * 获取header数量
     *
     * @return
     */
    public int getHeaderCount() {
        return mAutoLoadAdapter.getHeaderCount();
    }

    /**
     * 设置头部view是否展示
     *
     * @param enable
     */
    public void setHeaderEnable(boolean enable) {
        mAutoLoadAdapter.setHeaderShow(enable);
    }

    /**
     * 添加尾部view并展示
     *
     * @param footer
     */
    public void addFooterView(View footer) {
        mAutoLoadAdapter.addFooterView(footer);
        setFooterEnable(true);
    }

    /**
     * 设置尾部view是否展示
     *
     * @param enable
     */
    public void setFooterEnable(boolean enable) {
        mAutoLoadAdapter.setFooterShow(enable);
    }

    /***************************************************已完成********************************************************/


    /**
     * 设置加载监听
     *
     * @param listener
     */
    public void setOnLoadListener(OnLoadListener listener) {
        this.mOnLoadListener = listener;
    }

    /**
     * 设置加载状态
     *
     * @param isLoading
     */
    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    /**
     * 返回当前是否正在刷新或加载
     *
     * @return
     * @Description:
     */
    public boolean isLoading() {
        return mIsLoading;
    }

    /**
     * 设置当前是否还有更多数据
     *
     * @param has
     * @Description:
     */
    public void setHasMoreItems(boolean has) {
        this.hasMoreItems = has;
        this.mIsLoading = false;
    }

    /**
     * 返回当前是否还有更多数据
     *
     * @return
     * @Description:
     */
    public boolean hasMoreItems() {
        return this.hasMoreItems;
    }

    /**
     * 设置滚动监听
     */
    public void setOnScrollListener() {
        if (mOnScrollListener == null)
            mOnScrollListener = new OnLayoutScrollListener();
        addOnScrollListener(mOnScrollListener);
    }

    /**
     * 重写观察者，监听是否显示空视图
     */
    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && emptyView != null) {
                if (adapter.getItemCount() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                    ERecyclerView.this.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    ERecyclerView.this.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    /**
     * 设置空视图
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    /**
     * 条目点击接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    /**
     * 加载更多监听
     */
    public interface OnLoadListener {
        void onLoad();
    }

    /**
     * 实现RecyclerView的OnScrollListener
     */
    public class OnLayoutScrollListener extends OnScrollListener {

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            try {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    if (lastCompletelyVisibleItemPosition + 1 == recyclerView.getAdapter().getItemCount() && !isLoading() && hasMoreItems) {
                        mIsLoading = true;
                        mOnLoadListener.onLoad();
                    }
                }
            } catch (Exception e) {
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }

    /**
     * 自定义ItemDecoration
     */
    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }
}