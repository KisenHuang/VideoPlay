package com.example.videoplay.fragments;

/**
 * Created by Huangwy on 2015/11/27.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.videoplay.R;
import com.example.videoplay.views.ScaleImageView;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;


public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int mSectionNum;
    private ContextMenuDialogFragment mMenuDialogFragment;
//    private int[] images = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12, R.drawable.img_13, R.drawable.img_14, R.drawable.img_15};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Bundle bundle = getArguments();
        mSectionNum = bundle.getInt(ARG_SECTION_NUMBER);
        ListView id_listview = (ListView) rootView.findViewById(R.id.id_listview);
        TextView tv = new TextView(getActivity());
        tv.setText("nihao");
        id_listview.addHeaderView(tv);
        switch (mSectionNum) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

        return rootView;
    }

}

class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    public int[] images;

    public MyRecycleAdapter(int[] imgs) {
        this.images = imgs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.item_tv.setText("RecycleView"+":"+position);
        holder.item_tv.setImageResource(images[position]);
    }

    private static final int TYPE_00 = 0, TYPE_01 = 1;

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public ScaleImageView item_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_tv = (ScaleImageView) itemView.findViewById(R.id.item_tv);
        }
    }
}
