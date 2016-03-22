package com.example.videoplay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.videoplay.R;
import com.example.videoplay.model.HomePagerData;
import com.example.videoplay.views.recyclerload.WrapperRecyclerView;

/**
 * @author Huangwy
 * @TIME 2016/3/13 12:38
 */
public class HomeListAdapter extends WrapperRecyclerView.AutoLoadAdapter<HomePagerData> {

    public HomeListAdapter(Context context) {
        super(context);
    }

    @Override
    public WrapperRecyclerView.ItemClickViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.listitem_home, parent, false);
        return new HomeClickViewHolder(item);
    }

    @Override
    public void getView(WrapperRecyclerView.ItemClickViewHolder holder, int position, int viewType) {

    }

    class HomeClickViewHolder extends WrapperRecyclerView.ItemClickViewHolder {

        ImageView imageView;

        public HomeClickViewHolder(View itemView) {
            super(itemView);
//            imageView = (ImageView) itemView.findViewById(R.layout.listitem_home);
        }
    }
}
