package com.lizhi.microlive;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.lizhi.microlive.model.User;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/5/13.
 */

public class MyApplication extends Application
{
    //登录用户
    private User user;

    //屏幕的高
    public static int screenHeight;

    @Override
    public void onCreate()
    {
        super.onCreate();
        initOkHttp();
        screenHeight = getScreenHeight(this);
    }

    /**
     * 得到屏幕的高
     * @param aty
     * @return
     */
    public int getScreenHeight(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    private void initOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
