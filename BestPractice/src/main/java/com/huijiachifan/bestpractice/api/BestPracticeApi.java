/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.api;

import android.graphics.Bitmap;

import com.huijiachifan.bestpractice.interfaces.HttpCallback;
import com.huijiachifan.bestpractice.model.FileInputBean;

import java.io.File;
import java.util.Map;

/**
 * Description: BestPractice API
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月25日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class BestPracticeApi {

    private static final String HOST = "";
    private final static String HTTPS = "https://";
    private final static String HTTP = "http://";
    public final static String BASE_URL = "http://10.138.114.147:8080/okHttpServer/";
//    public final static String BASE_URL = HTTP + HOST;

    //==========Demo演示用==========//
    public static final class Demo {
        public static final int GET_TEST = 10000;
        public static final int GET_TEST2 = 10007;
        public static final int POST_TEST = 10001;
        public static final int POST_FILE_TEST = 10002;
        public static final int UPLOAD_FILE_TEST = 10003;
        public static final int UPLOAD_FILES_TEST = 10004;
        public static final int DOWNLOAD_TEST = 10005;
        public static final int DOWNLOAD_BITMAP_TEST = 10006;
    }
    public static void getHtml(String url, HttpCallback httpCallback) {
        OkHttpHelp.get(Demo.GET_TEST, url, httpCallback);
    }
    public static void getUser(String url, Map<String, String> params, HttpCallback httpCallback) {
        OkHttpHelp.get(Demo.GET_TEST, url, params, httpCallback);
    }
    public static void post(String url, Map<String, String> params, HttpCallback httpCallback) {
        OkHttpHelp.post(Demo.POST_TEST, url, params, httpCallback);
    }
    public static void postFile(String url, File file, HttpCallback httpCallback) {
        OkHttpHelp.uploadFile(Demo.POST_FILE_TEST, url, file, httpCallback);
    }
    public static void uploadFile(String url, File file, Map<String, String> params, HttpCallback httpCallback) {
        FileInputBean fileInputBean = FileInputBean.getTestFileInputBean("test.txt", file);
        fileInputBean.setParams(params);
        OkHttpHelp.uploadFile(Demo.UPLOAD_FILE_TEST, url, fileInputBean, httpCallback);
    }
    public static void uploadFile(String url, File file, Map<String, String> params, Map<String, String> headers, HttpCallback httpCallback) {
        FileInputBean fileInputBean = FileInputBean.getTestFileInputBean("test.txt", file);
        fileInputBean.setParams(params);
        fileInputBean.setHeaders(headers);
        OkHttpHelp.uploadFile(Demo.UPLOAD_FILE_TEST, url, fileInputBean, httpCallback);
    }
    public static void uploadFiles(String url, File[] files, Map<String, String> params, Map<String, String> headers, HttpCallback httpCallback) {
        FileInputBean fileInputBean = new FileInputBean();
        if (files != null) {
            int len = files.length;
            FileInputBean.FileInfo[] fileInfoArr = new FileInputBean.FileInfo[len];
            for (int i = 0; i < len; i++) {
                fileInfoArr[i] = new FileInputBean.FileInfo("mFile", "message"+i+".txt", files[i]);
            }
            fileInputBean.setFileInfoArr(fileInfoArr);
        }
        fileInputBean.setHeaders(headers);
        fileInputBean.setParams(params);
        OkHttpHelp.uploadFiles(Demo.UPLOAD_FILES_TEST, url, fileInputBean, httpCallback);
    }
    public static void download(String url, HttpCallback httpCallback) {
        OkHttpHelp.get(Demo.DOWNLOAD_TEST, url, httpCallback);
    }
    public static void downloadBitmap(String url, HttpCallback httpCallback) {
        OkHttpHelp.downloadBitmap(Demo.DOWNLOAD_BITMAP_TEST, url, httpCallback);
    }
    //==========Demo演示用==========//

}
