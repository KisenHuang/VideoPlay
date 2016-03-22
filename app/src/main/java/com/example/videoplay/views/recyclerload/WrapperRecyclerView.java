package com.example.videoplay.views.recyclerload;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/20 14:45
 */
public class WrapperRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>();

    private ArrayList<View> mFootViews = new ArrayList<>();

    private Adapter mAdapter;

    private View emptyView;

    public static OnItemClickListener mOnItemClickListener = null;

    protected OnLoadListener mOnLoadListener;

    //是否还有更多数据
    private boolean hasMoreItems = false;
    //是否正在加载更多
    private boolean mIsLoading = false;
    /**
     * 自定义实现了头部和底部加载更多的adapter
     */
    private OnLayoutScrollListener mOnScrollListener;

    public WrapperRecyclerView(Context context) {
        this(context, null);
    }

    public WrapperRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapperRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) {
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addFootView(View view) {
        mFootViews.clear();
        mFootViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {

        if (mHeaderViews.isEmpty() && mFootViews.isEmpty()) {
            super.setAdapter(adapter);
        } else {
            adapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, adapter);
            super.setAdapter(adapter);
        }
        if (adapter != null) {
//            if (adapter instanceof AutoLoadAdapter)
//                mAutoLoadAdapter = (AutoLoadAdapter) adapter;
//            else throw new IllegalFormatConversionException('/', AutoLoadAdapter.class);
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        emptyObserver.onChanged();
        mAdapter = adapter;
    }

    public static abstract class AutoLoadAdapter<T> extends RecyclerView.Adapter<ItemClickViewHolder> {

        protected List<T> mList;

        protected Context context;

        public AutoLoadAdapter(Context context) {
            this.context = context;
            mList = new ArrayList<>();
        }

        public void addAll(List<T> list) {
            if (list != null && list.size() != 0) {
                mList.addAll(list);
                notifyDataSetChanged();
            }
        }

        public void clear() {
            if (mList != null && mList.size() != 0)
                mList.clear();
        }

        @Override
        public ItemClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return this.onCreateHolder(parent, viewType);
        }

        public abstract ItemClickViewHolder onCreateHolder(ViewGroup parent, int viewType);

        /**
         * 获取对应Id数据
         *
         * @param position
         * @return
         */
        public T getItem(int position) {
            return mList.get(position);
        }

        @Override
        public void onBindViewHolder(ItemClickViewHolder holder, int position) {
            int viewType = getItemViewType(position);
//            if (mIsHeaderShow)
//                position = position - getHeaderCount();
            getView(holder, position, viewType);
        }

        public abstract void getView(ItemClickViewHolder holder, int position, int viewType);

        /**
         * 需要计算上加载更多和添加的头部俩个
         *
         * @return
         */
        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    /****************************************************************************************************/

    /**
     * 设置条目点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 自定义带有点击事件的ViewHolder
     */
    public static class ItemClickViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        public ItemClickViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(v, getPosition());
        }
    }

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
                    WrapperRecyclerView.this.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    WrapperRecyclerView.this.setVisibility(View.VISIBLE);
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
