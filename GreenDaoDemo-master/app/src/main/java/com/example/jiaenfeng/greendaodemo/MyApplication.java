package com.example.jiaenfeng.greendaodemo;

import android.app.Application;

/**
 * Created by jiaenfeng on 17/9/30.
 */

public class MyApplication extends Application{
    public static MyApplication mMyApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication=this;
    }
}
