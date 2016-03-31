/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.huijiachifan.bestpractice.util.Logger;

/**
 * Description: 重写Application
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月24日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class BestPracticeApp extends Application {

    //===========常量==========
    static private final String TAG = "BestPracticeApp";

    //===========普通变量==========
    static private BestPracticeApp sInstance;
    static public boolean mIsDevelopMode = true;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Logger.mTag = TAG;
        initRegister();
    }

    public static BestPracticeApp getInstance() {
        return sInstance;
    }

    static public Context getContext() {
        return sInstance;
    }

    private void initRegister() {
        // 通过Framework提供的回调来实现
        // Android自 API 14开始引入了一个方法，即Application的registerActivityLifecycleCallbacks方法，
        // 用来监听所有Activity的生命周期回调，比如onActivityCreated,onActivityResumed等
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                AppManager.getAppManager().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

}