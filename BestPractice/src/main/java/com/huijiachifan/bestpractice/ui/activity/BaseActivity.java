/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.huijiachifan.bestpractice.C;
import com.huijiachifan.bestpractice.interfaces.HttpCallback;
import com.huijiachifan.bestpractice.util.Logger;

/**
 * Description: Activity 模板类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public abstract class BaseActivity extends AppCompatActivity implements HttpCallback {

    //==========初始化==========
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Logger.mTag = this.getClass().getSimpleName();
    }

    //==========逻辑方法==========

    //==========网络请求回调==========
    @Override
    public <T> void onSuccess(int taskId, T t) {
        Logger.i(C.logTag.OKHTTP_SUCCESS_TAG, t.toString());
    }

    @Override
    public void onError(int taskId, String error) {
        Logger.i(C.logTag.OKHTTP_ERROR_TAG, error);
    }

    @Override
    public void onLoading(int taskId, float progress) {
        Logger.i(C.logTag.OKHTTP_LOADING_TAG, "Current Progress = " + progress);
    }

    @Override
    public void onNoNetwork() {
        Logger.i(C.errorMsg.NETWORK_ERROR);
    }

}
