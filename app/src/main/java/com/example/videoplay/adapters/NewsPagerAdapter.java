package com.example.videoplay.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videoplay.R;
import com.example.videoplay.model.NewsData;
import com.example.videoplay.utils.GlideUtils;

import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/20 10:49
 */
public class NewsPagerAdapter extends PagerAdapter {
    Context context;
    List<NewsData> mList;
    private LayoutInflater mInflater;

    public NewsPagerAdapter(Context context, List<NewsData> mList) {
        this.context = context;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = mInflater.inflate(R.layout.pageritem_home, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_imageview);
        TextView textView = (TextView) view.findViewById(R.id.item_tv);

        NewsData newsData = mList.get(position);
        GlideUtils.getInstance().loadUrl(context, mList.get(position).getPic(), imageView);
        textView.setText(newsData.getTitle());
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        ImageView image = (ImageView) view.findViewById(R.id.item_imageview);
        if (image != null) {
            //取消加载任务
            image.setImageBitmap(null);
        }
        container.removeView((View) object);
    }
}
