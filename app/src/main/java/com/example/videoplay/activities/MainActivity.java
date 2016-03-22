package com.example.videoplay.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.videoplay.BaseActivity;
import com.example.videoplay.R;
import com.example.videoplay.fragments.HomeFragment;
import com.example.videoplay.fragments.MineFragment;
import com.example.videoplay.fragments.NewsFragment;
import com.example.videoplay.fragments.OpenFragment;
import com.example.videoplay.views.HiddenFragmentTabHost;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    private HiddenFragmentTabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        closeSwipeBack();
        initView();
        initData();
    }


    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbarDivider.setVisibility(View.GONE);
        tabhost = (HiddenFragmentTabHost) findViewById(R.id.tab_host);
        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabhost.addTab(tabhost.newTabSpec("home").setIndicator(getTabItemView("首页", R.drawable.selector_tab_home)), HomeFragment.class, null);
        tabhost.addTab(tabhost.newTabSpec("news").setIndicator(getTabItemView("新闻", R.drawable.selector_tab_news)), NewsFragment.class, null);
        tabhost.addTab(tabhost.newTabSpec("image").setIndicator(getTabItemView("图片", R.drawable.selector_tab_home)), OpenFragment.class, null);
        tabhost.addTab(tabhost.newTabSpec("mine").setIndicator(getTabItemView("我的", R.drawable.selector_tab_home)), MineFragment.class, null);
//        tabhost.addTab(tabhost.newTabSpec("home").setIndicator(getTabItemView("首页", R.drawable.selector_tab_home)), OpenFragment.class,null);

        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        tabhost.setOnTabChangedListener(this);
        onTabChanged("home");
    }

    private void initData() {

    }

    @Override
    public void onTabChanged(String tabId) {
        switch (tabId) {
            case "home":
                setTitle("首页");
                break;
            case "news":
                setTitle("新闻");
                break;
            case "image":
                setTitle("图片");
                break;
            case "mine":
                setTitle("");
                break;

        }
    }

    /**
     * 创建tab
     *
     * @param text
     * @param resId
     * @return
     */
    public View getTabItemView(String text, int resId) {
        View tabItemView = getLayoutInflater().inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) tabItemView.findViewById(R.id.imageview);
        TextView textview = (TextView) tabItemView.findViewById(R.id.textview);
        textview.setText(text);
        imageView.setImageResource(resId);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        tabItemView.setLayoutParams(params);
        return tabItemView;
    }
}
