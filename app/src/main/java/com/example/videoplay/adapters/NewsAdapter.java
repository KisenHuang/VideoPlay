package com.example.videoplay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videoplay.R;
import com.example.videoplay.model.NewsData;
import com.example.videoplay.utils.GlideUtils;
import com.example.videoplay.views.recyclerload.AutoLoadAdapter;
import com.example.videoplay.views.recyclerload.ERecyclerView;
import com.example.videoplay.views.recyclerload.ItemClickViewHolder;
import com.example.videoplay.views.recyclerload.WrapperRecyclerView;

import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/16 19:27
 */
public class NewsAdapter extends AutoLoadAdapter<NewsData> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public ItemClickViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void getView(ItemClickViewHolder holder, int position, int viewType) {
        if (viewType == ERecyclerView.TYPE_NORMAL) {
            NewsHolder newsHolder = (NewsHolder) holder;
            NewsData newsData = mList.get(position);
            GlideUtils.getInstance().loadUrl(context, newsData.getPic(), newsHolder.newsIcon);
            newsHolder.newsTitle.setText(newsData.getTitle());
            newsHolder.newsDesc.setText(newsData.getDesc());
            newsHolder.newsDate.setText(newsData.getDate());
        }
    }

    class NewsHolder extends ItemClickViewHolder {

        public ImageView newsIcon;
        public TextView newsTitle;
        public TextView newsDesc;
        public TextView newsDate;

        public NewsHolder(View itemView) {
            super(itemView);
            newsIcon = (ImageView) itemView.findViewById(R.id.news_icon);
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsDesc = (TextView) itemView.findViewById(R.id.news_desc);
            newsDate = (TextView) itemView.findViewById(R.id.news_date);
        }
    }
}
