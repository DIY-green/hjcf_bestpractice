/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.api;

import android.graphics.Bitmap;

import com.huijiachifan.bestpractice.interfaces.HttpCallback;
import com.huijiachifan.bestpractice.model.FileInputBean;
import com.huijiachifan.bestpractice.util.okhttp.OkHttpUtil;
import com.huijiachifan.bestpractice.util.okhttp.builder.PostFormBuilder;
import com.huijiachifan.bestpractice.util.okhttp.callback.BitmapCallback;
import com.huijiachifan.bestpractice.util.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import okhttp3.Call;

/**
 * Description: OkHttp
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月25日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class OkHttpHelp {

    public final static int TIMEOUT_CONNECTION = 20000;// 连接超时时间
    public final static int TIMEOUT_SOCKET = 20000;// socket超时

    public static void get(final int taskId, final String url, final HttpCallback httpCallback) {
        OkHttpUtil
                .get()
                .url(url)
                .build()
                .execute(new OkHttpStringCallback(taskId, httpCallback));
    }

    public static void get(final int taskId, final String url, final Map<String, String> params, final HttpCallback httpCallback) {
        OkHttpUtil
                .get()
                .url(url)
                .params(params)
                .build()
                .execute(new OkHttpStringCallback(taskId, httpCallback));
    }

    public static void post(final int taskId, final String url, final Map<String, String> params, final HttpCallback httpCallback) {
        OkHttpUtil
                .post()
                .url(url)
                .params(params)
                .build()
                .execute(new OkHttpStringCallback(taskId, httpCallback));
    }

    public static void post(final int taskId, final String url, final Map<String, String> params, final Map<String, String> headers, final HttpCallback httpCallback) {
        OkHttpUtil
                .post()
                .url(url)
                .headers(headers)
                .params(params)
                .build()
                .execute(new OkHttpStringCallback(taskId, httpCallback));
    }

    /**
     * 单文件上传，一般不同的服务器端要求传不同的参数，该方法只适合上传到hongyang示例的服务器上
     * @param taskId
     * @param url
     * @param file
     * @param httpCallback
     */
    public static void uploadFile(final int taskId, final String url, final File file, final HttpCallback httpCallback) {
        OkHttpUtil
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(new OkHttpStringCallback(taskId, httpCallback));
    }

    public static void uploadFile(final int taskId, final String url, final FileInputBean fileInputBean, final HttpCallback httpCallback) {
        OkHttpUtil
                .post()
                .url(url)
                .addFile(fileInputBean.getFileInfo().getServerField(), fileInputBean.getFileInfo().getFileName(), fileInputBean.getFileInfo().getFile())
                .params(fileInputBean.getParams())
                .headers(fileInputBean.getHeaders())
                .build()
                .execute(new OkHttpStringCallback(taskId, httpCallback));
    }

    public static void uploadFiles(final int taskId, final String url, final FileInputBean fileInputBean, final HttpCallback httpCallback) {
        PostFormBuilder postFormBuilder = OkHttpUtil
                .post()
                .url(url);
        if (fileInputBean != null && fileInputBean.getFileInfoArr() != null) {
            FileInputBean.FileInfo[] fileInfoArr = fileInputBean.getFileInfoArr();
            for (int i = 0, len = fileInfoArr.length; i < len; i++) {
                postFormBuilder.addFile(fileInfoArr[i].getServerField(), fileInfoArr[i].getFileName(), fileInfoArr[i].getFile());
            }
        }
        postFormBuilder
                .headers(fileInputBean.getHeaders())
                .params(fileInputBean.getParams())
                .build()
                .execute(new OkHttpStringCallback(taskId, httpCallback));
    }

    public static void downloadBitmap(final int taskId, final String url, final HttpCallback httpCallback) {
        OkHttpUtil
                .get()
                .url(url)
                .tag(taskId + url)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new OkHttpBitmapCallback(taskId, httpCallback));
    }

    private static final class OkHttpStringCallback extends StringCallback {

        private int mTaskId;
        private HttpCallback mHttpCallback;

        public OkHttpStringCallback(int taskId, HttpCallback httpCallback) {
            this.mTaskId = taskId;
            this.mHttpCallback = httpCallback;
        }

        @Override
        public void onError(Call call, Exception e) {
            mHttpCallback.onError(mTaskId, e.getMessage());
        }

        @Override
        public void onResponse(String response) {
            mHttpCallback.onSuccess(mTaskId, response);
        }

        @Override
        public void inProgress(float progress) {
            mHttpCallback.onLoading(mTaskId, progress);
        }
    }

    private static final class OkHttpBitmapCallback extends BitmapCallback {

        private int mTaskId;
        private HttpCallback mHttpCallback;

        public OkHttpBitmapCallback(int taskId, HttpCallback httpCallback) {
            this.mTaskId = taskId;
            this.mHttpCallback = httpCallback;
        }

        @Override
        public void onError(Call call, Exception e) {
            mHttpCallback.onError(mTaskId, e.getMessage());
        }

        @Override
        public void onResponse(Bitmap bitmap) {
            mHttpCallback.onSuccess(mTaskId, bitmap);
        }

        @Override
        public void inProgress(float progress) {
            mHttpCallback.onLoading(mTaskId, progress);
        }
    }

}
