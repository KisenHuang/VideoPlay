package com.example.videoplay.views.recyclerload;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.videoplay.views.recyclerload.ERecyclerView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/24 20:32
 */
public abstract class AutoLoadAdapter<T> extends RecyclerView.Adapter<ItemClickViewHolder> {

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
    private OnItemClickListener onItemClickListener;

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
            return ERecyclerView.TYPE_HEADER;
        }
        if (footerPosition == position && mIsFooterShow && (mFooterView != null)) {
            return ERecyclerView.TYPE_FOOTER;
        }
        /**
         * 这么做保证layoutManager切换之后能及时的刷新上对的布局子类可重写用于切换列表和宫格试图
         */
//            if (getLayoutManager() instanceof LinearLayoutManager) {
//                return TYPE_LIST;
//            } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
//                return TYPE_STAGGER;
//            }
        return ERecyclerView.TYPE_NORMAL;
    }

    @Override
    public ItemClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ERecyclerView.TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView);
        }
        if (viewType == ERecyclerView.TYPE_FOOTER) {
            return new FooterViewHolder(mFooterView);
        } else { // type normal
            ItemClickViewHolder viewHolder = this.onCreateHolder(parent, viewType);
            viewHolder.setOnItemClickListener(onItemClickListener);
            return viewHolder;
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
