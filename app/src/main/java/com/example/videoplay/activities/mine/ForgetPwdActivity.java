package com.example.videoplay.activities.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.videoplay.BaseActivity;
import com.example.videoplay.R;
import com.example.videoplay.interfs.manager.UserManager;
import com.example.videoplay.utils.UIUtils;
import com.wilddog.client.DataSnapshot;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.Wilddog;
import com.wilddog.client.WilddogError;

import java.util.Map;

/**
 * @author Huangwy
 * @TIME 2016/4/10 13:18
 */
public class ForgetPwdActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 忘记密码
     */
    public static final String TYPE_FORGET = "forget";
    /**
     * 修改密码
     */
    public static final String TYPE_CHANGE = "change";

    private View lyNowPass;
    private View lyPhone;
    private EditText edoldPass;
    private EditText edPhone;
    private EditText edNewPass;
    private EditText edNewPass2;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        lyNowPass = findViewById(R.id.lyNowPass);
        lyPhone = findViewById(R.id.lyPhone);

        edoldPass = (EditText) findViewById(R.id.edoldPass);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edNewPass = (EditText) findViewById(R.id.edNewPass);
        edNewPass2 = (EditText) findViewById(R.id.edNewPass2);

    }

    private void initData() {
        type = getIntent().getStringExtra("type");
        switch (type) {
            case TYPE_FORGET://忘记密码
                lyNowPass.setVisibility(View.GONE);
                break;
            case TYPE_CHANGE://修改密码
                lyPhone.setVisibility(View.GONE);
                break;
        }
    }

    private void initListener() {
        findViewById(R.id.nextBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextBtn:
                commitData();
                break;
        }
    }

    private void commitData() {
        String oldPass = edoldPass.getText().toString().trim();
        String phone = edPhone.getText().toString().trim();
        String newPassword = edNewPass.getText().toString().trim();
        String newPassword2 = edNewPass2.getText().toString().trim();

        showProgressDialog("正在修改数据");

        switch (type) {
            case TYPE_CHANGE://修改密码
                if (checkChange(oldPass, newPassword, newPassword2)) {
                    changePass(newPassword);
                }
                break;
            case TYPE_FORGET://忘记密码
                if (checkForget(phone, newPassword, newPassword2)) {
                    forgetPass(phone, newPassword);
                }
                break;
        }
    }

    /**
     * 忘记找回密码
     *
     * @param phone
     * @param newPassword
     */
    private void forgetPass(final String phone, final String newPassword) {
        Wilddog users = wDRef.child("users");
        users.orderByKey().equalTo(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                writeWD(value, phone, newPassword);
            }

            @Override
            public void onCancelled(WilddogError wilddogError) {

            }
        });
    }

    private void writeWD(Map<String, Object> value, String phone, String newPassword) {
        if (value == null) {
            dismissProgressDialog();
            showMsg("手机号未注册");
            return;
        }
        value.put("phone", phone);
        value.put("password", newPassword);
        Wilddog user = wDRef.child("users/" + phone);
        user.setValue(value, new Wilddog.CompletionListener() {
            @Override
            public void onComplete(WilddogError wilddogError, Wilddog wilddog) {
                dismissProgressDialog();
                if (wilddogError == null) {
                    showMsg("密码重置成功");
                    finish();
                } else
                    showMsg("密码重置失败");
            }
        });
    }


    /**
     * 修改密码
     *
     * @param newPassword
     */
    private void changePass(String newPassword) {
        String phone = UserManager.getInstance().getUser().getPhone();
        wDRef.child("users/" + phone + "/password").setValue(newPassword, new Wilddog.CompletionListener() {
            @Override
            public void onComplete(WilddogError wilddogError, Wilddog wilddog) {
                dismissProgressDialog();
                if (wilddogError == null) {
                    showMsg("密码重置成功");
                    finish();
                } else
                    showMsg("密码重置失败");
            }
        });
    }

    /**
     * 密码合法性
     *
     * @param oldPass
     * @param newPassword
     * @param newPassword2
     */
    private boolean checkChange(String oldPass, String newPassword, String newPassword2) {
        if (TextUtils.isEmpty(oldPass))
            showMsg("当前密码不能为空");
        else if (!TextUtils.equals(UserManager.getInstance().getUser().getPassword(), oldPass))
            showMsg("当前密码输入不正确");
        else if (TextUtils.isEmpty(newPassword))
            showMsg("密码不能为空");
        else if (!UIUtils.checkPwd(newPassword))
            showMsg("密码不少于6位");
        else if (!TextUtils.equals(newPassword, newPassword2))
            showMsg("两次输入密码不一致");
        else
            return true;
        return false;
    }

    /**
     * 检测手机号，密码合法性
     *
     * @param phone
     * @param newPassword
     * @param newPassword2
     */
    private boolean checkForget(String phone, String newPassword, String newPassword2) {
        if (TextUtils.isEmpty(phone))
            showMsg("手机号不能为空");
        else if (TextUtils.isEmpty(newPassword))
            showMsg("密码不能为空");
        else if (!UIUtils.checkPhone(phone))
            showMsg("手机号码不正确");
        else if (!UIUtils.checkPwd(newPassword))
            showMsg("密码不少于6位");
        else if (!TextUtils.equals(newPassword, newPassword2))
            showMsg("两次输入密码不一致");
        else
            return true;
        return false;
    }
}
