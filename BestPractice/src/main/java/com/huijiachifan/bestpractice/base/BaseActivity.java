/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.base;

import android.support.v7.app.AppCompatActivity;

import com.huijiachifan.bestpractice.AppManager;

/**
 * Description:
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月24日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        // 保存当前Activity
        // 比较可靠的方式，在Activity的onResume方法中，将当前的Activity实例保存到一个变量中
        AppManager.getAppManager().setCurrentActivity(this);
    }
}
