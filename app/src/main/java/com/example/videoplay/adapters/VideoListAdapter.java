package com.example.videoplay.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videoplay.R;
import com.example.videoplay.model.UserVideoInfo;
import com.example.videoplay.utils.GlideUtils;
import com.example.videoplay.views.recyclerload.AutoLoadAdapter;
import com.example.videoplay.views.recyclerload.ItemClickViewHolder;

/**
 * @author Huangwy
 * @TIME 2016/3/24 21:34
 */
public class VideoListAdapter extends AutoLoadAdapter<UserVideoInfo> {

    public VideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public ItemClickViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listitem_video_card, parent, false);
        return new UserVideoHolder(view);
    }

    @Override
    public void getView(ItemClickViewHolder holder, int position, int viewType) {
        UserVideoHolder videoHolder = (UserVideoHolder) holder;
        UserVideoInfo userVideoInfo = mList.get(position);

        GlideUtils.getInstance().loadUrl(context, userVideoInfo.getThumb_img(), videoHolder.image);
        videoHolder.title.setText(userVideoInfo.getTitle());
        videoHolder.playTimes.setText(userVideoInfo.getPlay_times() + "次播放");
    }

    class UserVideoHolder extends ItemClickViewHolder {
        ImageView image;
        TextView title;
        TextView playTimes;
        TextView createTimes;

        public UserVideoHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_bg);
            title = (TextView) itemView.findViewById(R.id.title);
            playTimes = (TextView) itemView.findViewById(R.id.play_times);
        }
    }
}
