package com.example.videoplay.views.recyclerload;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.List;

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

    public static OnItemClickListener mOnItemClickListener = null;

    protected OnLoadListener mOnLoadListener;

    //是否还有更多数据
    private boolean hasMoreItems = false;
    //是否正在加载更多
    private boolean mIsLoading = false;

//    /**
//     * 标记是否正在加载更多，防止再次调用加载更多接口
//     */
//    private boolean mIsLoadingMore;
//
//    /**
//     * 标记加载更多的position
//     */
//    private int mLoadMorePosition;

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
            if (adapter instanceof AutoLoadAdapter)
                mAutoLoadAdapter = (AutoLoadAdapter) adapter;
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


    public static abstract class AutoLoadAdapter<T> extends RecyclerView.Adapter<ItemClickViewHolder> {

        protected List<T> mList;

        protected List<Object> mHeaderList;

        protected Context context;
        /**
         * 数据adapter
         */
        private boolean mIsHeaderShow;

        private boolean mIsFooterShow;

        private View mHeaderView;

        private View mFooterView;

        public AutoLoadAdapter(Context context) {
            this.context = context;
            mList = new ArrayList<>();
            mIsHeaderShow = false;
        }

        public void addAll(List<T> list) {
            if (list != null && list.size() != 0) {
                mList.addAll(list);
                notifyDataSetChanged();
            }
        }

        public List<T> getList() {
            return mList;
        }

        public T getPositionData(int position) {
            return position < mList.size() && position > -1 ? mList.get(position) : null;
        }

        public void clear() {
            if (mList != null && mList.size() != 0)
                mList.clear();
        }

        public int getHeaderCount() {
            return mHeaderView != null ? 1 : 0;
        }

        @Override
        public int getItemViewType(int position) {
            int headerPosition = 0;
            int footerPosition = getItemCount() - 1;

            if (headerPosition == position && mIsHeaderShow && (mHeaderView != null)) {
                return TYPE_HEADER;
            }
            if (footerPosition == position && mIsFooterShow && (mFooterView != null)) {
                return TYPE_FOOTER;
            }
            /**
             * 这么做保证layoutManager切换之后能及时的刷新上对的布局子类可重写用于切换列表和宫格试图
             */
//            if (getLayoutManager() instanceof LinearLayoutManager) {
//                return TYPE_LIST;
//            } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
//                return TYPE_STAGGER;
//            }
            return TYPE_NORMAL;
        }

        @Override
        public ItemClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                return new HeaderViewHolder(mHeaderView);
            }
            if (viewType == TYPE_FOOTER) {
                return new FooterViewHolder(mFooterView);
            } else { // type normal
                return this.onCreateHolder(parent, viewType);
            }
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

        public class FooterViewHolder extends ItemClickViewHolder {

            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }

        public class HeaderViewHolder extends ItemClickViewHolder {
            public HeaderViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public void onBindViewHolder(ItemClickViewHolder holder, int position) {
            int viewType = getItemViewType(position);
            if (mIsHeaderShow)
                position = position - getHeaderCount();
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
            int count = mList.size();
            if (mIsFooterShow) count++;
            if (mIsHeaderShow) count++;
            return count;
        }

        public void setHeaderShow(boolean enable) {
            mIsHeaderShow = enable;
        }

        public void addHeaderView(View header) {
            mHeaderView = header;
        }

        public void setFooterShow(boolean enable) {
            mIsFooterShow = enable;
        }

        public void addFooterView(View footer) {
            mFooterView = footer;
        }
    }

    /***************************************************已完成********************************************************/


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