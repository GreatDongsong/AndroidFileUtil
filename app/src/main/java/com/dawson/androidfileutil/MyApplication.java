package com.dawson.androidfileutil;

import android.app.Application;

public class MyApplication extends Application {
    public static MyApplication getContext() {
        return context;
    }

    private static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }
}
