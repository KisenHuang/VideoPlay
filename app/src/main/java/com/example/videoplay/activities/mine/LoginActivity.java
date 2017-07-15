package com.example.videoplay.activities.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.videoplay.BaseActivity;
import com.example.videoplay.R;
import com.example.videoplay.interfs.manager.UserManager;
import com.example.videoplay.utils.UIUtils;
import com.example.videoplay.utils.eventBus.EventUtil;
import com.example.videoplay.utils.eventBus.MessageEvent;
import com.wilddog.client.DataSnapshot;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.Wilddog;
import com.wilddog.client.WilddogError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

/**
 * 登录页面
 *
 * @author Huangwy
 * @TIME 2016/4/10 11:35
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText loginPhone;
    private EditText loginPwd;
    private TextView forgetPwd;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        initView();
        initListener();
    }

    private void initView() {
        setTitle("登录");
        loginPhone = (EditText) findViewById(R.id.edLoginPhone);
        loginPwd = (EditText) findViewById(R.id.edLoginPass);
        forgetPwd = (TextView) findViewById(R.id.tvForgetPass);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
    }

    private void initListener() {
        forgetPwd.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvForgetPass://忘记密码
                startActivity(new Intent(mContext, ForgetPwdActivity.class)
                        .putExtra("type", ForgetPwdActivity.TYPE_FORGET));
                break;
            case R.id.loginBtn://登录
                login();
                break;
            case R.id.registerBtn://注册
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String phone = loginPhone.getText().toString().trim();
        String password = loginPwd.getText().toString().trim();

        showProgressDialog("正在登陆");

        if (checkLogin(phone, password)) {
            // TODO: 2016/4/10 检测本地数据库登录
            Wilddog users = wDRef.child("users");
            users.orderByKey().equalTo(phone).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dismissProgressDialog();
                    Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                    checkIsLogin(value);
                }

                @Override
                public void onCancelled(WilddogError wilddogError) {
                }
            });
        }
    }

    private void checkIsLogin(Map<String, Object> value) {
        String phone = loginPhone.getText().toString().trim();
        String pass = loginPwd.getText().toString().trim();
        if (value == null) {
            showMsg("手机号未注册");
        } else {
            Map<String, Object> map = (Map<String, Object>) value.get(phone);
            String password = (String) map.get("password");
            if (TextUtils.equals(password, pass)) {
                // TODO: 2016/4/13 事件机制通知需要更新的界面更新数据
                EventBus.getDefault().post(new EventUtil(MessageEvent.USER_LOGIN, null));
                UserManager.getInstance().setUser(map);
                showMsg("登陆成功");
                finish();
            } else {
                showMsg("密码不正确");
            }
        }
    }

    /**
     * 检测手机号，密码合法性
     *
     * @param phone
     * @param password
     * @return
     */
    private boolean checkLogin(String phone, String password) {
        if (TextUtils.isEmpty(phone))
            showMsg("手机号不能为空");
        else if (TextUtils.isEmpty(password))
            showMsg("密码不能为空");
        else if (!UIUtils.checkPhone(phone))
            showMsg("手机号码不正确");
        else if (!UIUtils.checkPwd(password))
            showMsg("密码不少于6位");
        else
            return true;
        return false;
    }

    @Subscribe
    public void onEvent(EventUtil event) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
