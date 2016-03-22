package com.example.videoplay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videoplay.R;
import com.example.videoplay.model.DotaHeroes;
import com.example.videoplay.utils.GlideUtils;
import com.example.videoplay.views.ScaleImageView;
import com.example.videoplay.views.recyclerload.ERecyclerView;
import com.example.videoplay.views.recyclerload.WrapperRecyclerView;
import com.example.videoplay.views.recyclerload.WrapperRecyclerView;

/**
 * Created by Huangwy on 2015/11/28.
 */
public class ImageViewAdapter extends ERecyclerView.AutoLoadAdapter<DotaHeroes> {

    public ImageViewAdapter(Context context) {
        super(context);
    }

    @Override
    public ERecyclerView.ItemClickViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_open, null);
        return new MyViewHolder(view);
    }

    @Override
    public void getView(ERecyclerView.ItemClickViewHolder holder, int position, int viewType) {
//        if (viewType == WrapperRecyclerView.TYPE_NORMAL) {
            String url = mList.get(position).getIcon_url();
            GlideUtils.getInstance().loadUrl(context, url, ((MyViewHolder) holder).imageView);
//        }
    }

    class MyViewHolder extends ERecyclerView.ItemClickViewHolder {

        public ScaleImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ScaleImageView) itemView.findViewById(R.id.imageview);
        }
    }
}
