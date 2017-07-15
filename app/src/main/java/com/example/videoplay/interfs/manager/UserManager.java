package com.example.videoplay.interfs.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.videoplay.AppContext;
import com.example.videoplay.model.User;

import java.util.Map;

/**
 * @author Huangwy
 * @TIME 2016/4/10 12:05
 */
public class UserManager {

    private static UserManager instance;

    private User user;

    private Context context;

    private UserManager() {
        context = AppContext.getInstance();
    }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    public void setUser(Map<String, Object> luser) {
        if (luser != null) {
            user.setPhone((String) luser.get("phone"));
            user.setPassword((String) luser.get("password"));

            SharedPreferences sp = context.getSharedPreferences("user_pre", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("phone", (String) luser.get("phone"));
            editor.putString("password", (String) luser.get("password"));
            editor.commit();
        }
    }

    public User getUser() {
        if (user == null) {
            SharedPreferences sp = context.getSharedPreferences("user_pre", Context.MODE_PRIVATE);
            user = new User();
            user.setPhone(sp.getString("phone", ""));
            user.setPassword(sp.getString("password", ""));
        }
        return user;
    }

    /**
     * 验证是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return !TextUtils.isEmpty(getUser().getPhone());
    }

    /**
     * 退出登录
     */
    public void logout() {
        SharedPreferences sp = context.getSharedPreferences("user_pre", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        user = null;
    }
}
