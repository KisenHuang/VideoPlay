package com.example.videoplay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videoplay.R;
import com.example.videoplay.model.MaxUserData;
import com.example.videoplay.utils.GlideUtils;
import com.example.videoplay.utils.TimeUtils;
import com.example.videoplay.views.recyclerload.AutoLoadAdapter;
import com.example.videoplay.views.recyclerload.ERecyclerView;
import com.example.videoplay.views.recyclerload.ItemClickViewHolder;

/**
 * @author Huangwy
 * @TIME 2016/3/13 12:38
 */
public class HomeListAdapter extends AutoLoadAdapter<MaxUserData> {

    public HomeListAdapter(Context context) {
        super(context);
    }

    @Override
    public ItemClickViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.listitem_home, parent, false);
        return new HomeClickViewHolder(item);
    }

    @Override
    public void getView(ItemClickViewHolder holder, int position, int viewType) {
        switch (viewType) {
            case ERecyclerView.TYPE_NORMAL:
                MaxUserData maxUserData = mList.get(position);
                HomeClickViewHolder homeHolder = (HomeClickViewHolder) holder;

                GlideUtils.getInstance().loadUrl(context, maxUserData.getAvatar(), homeHolder.userIcon);
                homeHolder.userName.setText(maxUserData.getUsername());
                homeHolder.videoNum.setText(String.valueOf(maxUserData.getCount()));
                homeHolder.updataTime.setText(TimeUtils.convertYMDHM(((long) maxUserData.getRecent_time()) * 1000));
                break;
        }
    }

    class HomeClickViewHolder extends ItemClickViewHolder {

        TextView userName;
        TextView videoNum;
        TextView updataTime;
        ImageView userIcon;

        public HomeClickViewHolder(View itemView) {
            super(itemView);
            userIcon = (ImageView) itemView.findViewById(R.id.user_icon);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            videoNum = (TextView) itemView.findViewById(R.id.video_num);
            updataTime = (TextView) itemView.findViewById(R.id.updata_time);
        }
    }
}
