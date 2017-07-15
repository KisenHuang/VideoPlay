package com.example.videoplay.views.recyclerload;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.videoplay.views.recyclerload.ERecyclerView.OnItemClickListener;

/**
 * @author Huangwy
 * @TIME 2016/3/24 20:49
 */
public class ItemClickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener mOnItemClickListener;

    public ItemClickViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(v, getLayoutPosition());
    }
}
