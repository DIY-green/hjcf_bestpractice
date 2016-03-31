/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.ui.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huijiachifan.bestpractice.R;
import com.huijiachifan.bestpractice.api.BestPracticeApi;
import com.huijiachifan.bestpractice.util.DateUtil;
import com.huijiachifan.bestpractice.util.Logger;
import com.huijiachifan.bestpractice.util.ResourceUtil;
import com.huijiachifan.bestpractice.util.TipUtil;
import com.huijiachifan.bestpractice.util.UiUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: OkHttp二次封装使用演示
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class OkHttpDemoActivity extends BaseActivity {

    //==========常量==========

    //==========控件变量==========
    private TextView mShowResultTV;
    private ImageView mShowImageIV;

    //==========普通变量==========


    //==========初始化==========
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);

        initView();
    }

    private void initView() {
        this.mShowResultTV = (TextView) this.findViewById(R.id.tv_showresult);
        this.mShowImageIV = (ImageView) this.findViewById(R.id.iv_showimage);

        Uri uri = ResourceUtil.getResourceUrl(getPackageName(), R.drawable.ic_launcher);
        this.mShowImageIV.setImageURI(uri);
    }

    //==========点击事件==========
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test01:
                getTest();
                break;
            case R.id.btn_test02:
                postTest();
                break;
            case R.id.btn_test03:
                postFileTest();
                break;
            case R.id.btn_test04:
                uploadFileTest();
                break;
            case R.id.btn_test05:
                uploadFilesTest();
                break;
            case R.id.btn_test06:
                downloadTest();
                break;
            case R.id.btn_test07:
                downloadBitmapTest();
                break;
            case R.id.btn_getuertest:
                getTest2();
                break;
        }
    }

    private void getTest() {
        UiUtil.dimBackground(this, 1.0f, 0.5f);
        TipUtil.toast("Show Toast in Main Thread.");
        BestPracticeApi.getHtml("http://www.csdn.net/", this);
    }
    private void getTest2() {
        UiUtil.dimBackground(this, 0.5f, 1.0f);
        String url = BestPracticeApi.BASE_URL + "user!getUser";
        Map<String, String> params = new HashMap<>();
        params.put("username", "hyman");
        params.put("password", "123");
        BestPracticeApi.getUser(url, params, this);
    }

    private void postTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                TipUtil.toast("Show Toast in Other Thread.");
                TipUtil.toast(Thread.currentThread() + "我来自其他线程！" + System.currentTimeMillis());
            }
        }).start();
        BestPracticeApi.post(BestPracticeApi.BASE_URL+"user!postString", null, this);
    }

    private void postFileTest() {
        TipUtil.displayOneBtnDialog(this, "postFileTest()");
        File file = new File(Environment.getExternalStorageDirectory(), "img00.jpg");
        if (!file.exists()) {
            Toast.makeText(OkHttpDemoActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        BestPracticeApi.postFile(BestPracticeApi.BASE_URL+"user!postFile", file, this);
    }

    private void uploadFileTest() {
        TipUtil.displayTwoBtnDialog(this, "postFileTest()");
        File file = new File(Environment.getExternalStorageDirectory(), "test1.jpg");
        if (!file.exists())
        {
            Toast.makeText(OkHttpDemoActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");
        BestPracticeApi.uploadFile(BestPracticeApi.BASE_URL+"user!postFile", file, params, headers, this);
    }

    private void uploadFilesTest() {
        TipUtil.displayProgressDialog(this, "Loading...", true);
        File file = new File(Environment.getExternalStorageDirectory(), "img169.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory(), "test1.txt");
        if (!file.exists())
        {
            Toast.makeText(OkHttpDemoActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        String url = BestPracticeApi.BASE_URL + "user!uploadFile";
        BestPracticeApi.uploadFiles(url, new File[]{file, file2}, params, null, this);
    }

    private void downloadTest() {
        TipUtil.displayHorizontalProgressDialog(this, "Loading");
        TipUtil.updateHorizontalProgressDialog(this, DateUtil.getSec());
        String url = "https://github.com/hongyangAndroid/okhttp-utils/blob/master/gson-2.2.1.jar?raw=true";
        BestPracticeApi.download(url, this);
    }

    private void downloadBitmapTest() {
        String url = "http://images.csdn.net/20150817/1.jpg";
        BestPracticeApi.downloadBitmap(url, this);
    }

    //==========网络请求回调==========
    @Override
    public <T> void onSuccess(int taskId, T t) {
        super.onSuccess(taskId, t);
        switch (taskId) {
            case BestPracticeApi.Demo.GET_TEST:
            case BestPracticeApi.Demo.GET_TEST2:
            case BestPracticeApi.Demo.POST_TEST:
            case BestPracticeApi.Demo.POST_FILE_TEST:
            case BestPracticeApi.Demo.UPLOAD_FILE_TEST:
            case BestPracticeApi.Demo.UPLOAD_FILES_TEST:
                this.mShowResultTV.setText(t.toString());
                break;
            case BestPracticeApi.Demo.DOWNLOAD_TEST:
                Logger.e("Download Success!");
                break;
            case BestPracticeApi.Demo.DOWNLOAD_BITMAP_TEST:
                this.mShowImageIV.setImageBitmap((Bitmap) t);
                break;
        }
    }

    @Override
    public void onError(int taskId, String error) {
        super.onError(taskId, error);
    }

    @Override
    public void onLoading(int taskId, float progress) {
        super.onLoading(taskId, progress);
        switch (taskId) {
            case BestPracticeApi.Demo.UPLOAD_FILE_TEST:
            case BestPracticeApi.Demo.UPLOAD_FILES_TEST:
            case BestPracticeApi.Demo.DOWNLOAD_TEST:
            case BestPracticeApi.Demo.DOWNLOAD_BITMAP_TEST:
                this.mShowResultTV.setText(taskId + "======" + progress);
                break;
        }
    }
}
