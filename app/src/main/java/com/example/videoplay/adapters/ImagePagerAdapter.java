package com.example.videoplay.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videoplay.R;
import com.example.videoplay.model.HomePagerData;
import com.example.videoplay.utils.GlideUtils;

import java.util.List;

/**
 * @author Huangwy
 * @TIME 2016/3/12 20:45
 */
public class ImagePagerAdapter extends PagerAdapter {

    Context context;
    List<HomePagerData> mList;
    private LayoutInflater mInflater;

    public ImagePagerAdapter(Context context, List<HomePagerData> mList) {
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
        View view = mInflater.inflate(R.layout.pageritem_home, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_imageview);
        TextView textView = (TextView) view.findViewById(R.id.item_tv);

        HomePagerData homePagerData = mList.get(position);
        GlideUtils.getInstance().loadUrl(context, homePagerData.getBigimg(), imageView);
        textView.setText(homePagerData.getTitle());
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
