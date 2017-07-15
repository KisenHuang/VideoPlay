package com.example.videoplay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videoplay.BaseFragment;
import com.example.videoplay.R;
import com.example.videoplay.activities.mine.AttentionActivity;
import com.example.videoplay.activities.mine.CollectActivity;
import com.example.videoplay.activities.mine.FeedBackActivity;
import com.example.videoplay.activities.mine.FollowActivity;
import com.example.videoplay.activities.mine.LoginActivity;
import com.example.videoplay.activities.mine.SettingActivity;
import com.example.videoplay.interfs.manager.UserManager;
import com.example.videoplay.model.UserData;
import com.example.videoplay.utils.LogUtil;
import com.example.videoplay.utils.eventBus.EventUtil;
import com.example.videoplay.utils.eventBus.MessageEvent;
import com.example.videoplay.views.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private CircleImageView avatar;
    private TextView userName;
    private TableRow collection;
    private TableRow attention;
    private TableRow feedback;
    private TableRow follow;
    private TableRow setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        initView(view);
        initListener();
    }

    private void initView(View view) {
        UserManager.getInstance().isLogin();
        avatar = (CircleImageView) view.findViewById(R.id.avatar);
        userName = (TextView) view.findViewById(R.id.user_name);
        collection = (TableRow) view.findViewById(R.id.collection);
        attention = (TableRow) view.findViewById(R.id.attention);
        feedback = (TableRow) view.findViewById(R.id.feedback);
        follow = (TableRow) view.findViewById(R.id.follow);
        setting = (TableRow) view.findViewById(R.id.settings);
    }

    private void initListener() {
        avatar.setOnClickListener(this);
        userName.setOnClickListener(this);
        collection.setOnClickListener(this);
        attention.setOnClickListener(this);
        feedback.setOnClickListener(this);
        follow.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar://头像
                Toast.makeText(mContext, "avatar-nothing", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_name://用户名
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.collection://我的收藏
                startActivity(new Intent(mContext, CollectActivity.class));
                break;
            case R.id.attention://我的关注
                startActivity(new Intent(mContext, AttentionActivity.class));
                break;
            case R.id.feedback://意见反馈
                startActivity(new Intent(mContext, FeedBackActivity.class));
                break;
            case R.id.follow://关于我们
                startActivity(new Intent(mContext, FollowActivity.class));
                break;
            case R.id.settings://设置
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
        }
    }

    @Subscribe
    public void onEvent(EventUtil event){
        switch (event.getStatus()){
            case MessageEvent.USER_LOGIN:
                LogUtil.e("onEvent","onEvent=user is login");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
