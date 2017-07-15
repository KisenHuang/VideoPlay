package com.example.videoplay.activities.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.videoplay.BaseActivity;
import com.example.videoplay.R;
import com.example.videoplay.utils.UIUtils;
import com.wilddog.client.DataSnapshot;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.Wilddog;
import com.wilddog.client.WilddogError;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText loginPhone;
    private EditText loginPwd;
    private Button registerBtn;
    private EditText loginPwdSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initListener();
    }

    private void initView() {
        loginPhone = (EditText) findViewById(R.id.edLoginPhone);
        loginPwd = (EditText) findViewById(R.id.edLoginPass);
        loginPwdSure = (EditText) findViewById(R.id.edLoginPassSure);
        registerBtn = (Button) findViewById(R.id.registerBtn);
    }

    private void initListener() {
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerBtn:
                register();
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        String phone = loginPhone.getText().toString().trim();
        String password = loginPwd.getText().toString().trim();
        String passSure = loginPwdSure.getText().toString().trim();

        showProgressDialog("正在注册用户");

        if (checkLogin(phone, password, passSure)) {
            // TODO: 2016/4/10 检测本地数据库登录
            Wilddog users = wDRef.child("users");
            users.orderByKey().equalTo(phone).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                    writeWD(value);
                }

                @Override
                public void onCancelled(WilddogError wilddogError) {

                }
            });
        }
    }

    private void writeWD(Map<String, Object> value) {
        final String phone = loginPhone.getText().toString().trim();
        String password = loginPwd.getText().toString().trim();
        if (value != null) {
            dismissProgressDialog();
            showMsg("手机号已注册");
            return;
        }
        value = new HashMap<>();
        value.put("phone", phone);
        value.put("password", password);
        Wilddog user = wDRef.child("users/" + phone);
        user.setValue(value, new Wilddog.CompletionListener() {
            @Override
            public void onComplete(WilddogError wilddogError, Wilddog wilddog) {
                dismissProgressDialog();
                if (wilddogError == null) {
                    showMsg("注册成功");
                    finish();
                } else
                    showMsg("注册失败");
            }
        });
    }

    /**
     * 检测手机号，密码合法性
     *
     * @param phone
     * @param password
     * @return
     */
    private boolean checkLogin(String phone, String password, String passSure) {
        if (TextUtils.isEmpty(phone))
            showMsg("手机号不能为空");
        else if (TextUtils.isEmpty(password))
            showMsg("密码不能为空");
        else if (!UIUtils.checkPhone(phone))
            showMsg("手机号码不正确");
        else if (!UIUtils.checkPwd(password))
            showMsg("密码不少于6位");
        else if (!TextUtils.equals(password, passSure))
            showMsg("两次输入密码不一致");
        else
            return true;
        return false;
    }
}
