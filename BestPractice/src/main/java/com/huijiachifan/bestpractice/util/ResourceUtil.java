/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description: 操作Resource资源的工具类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月26日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class ResourceUtil {

    private static final String TAG = "ResourceUtil";

    private ResourceUtil() {
        ExceptionUtil.illegalArgument("非法操作");
    }

    /**
     * 获取res包下的资源文件的Uri
     * @param resId
     * @return
     */
    public static Uri getResourceUrl(String packageName, int resId) {
        Uri uri = Uri.parse("android.resource://" + packageName + "/" + resId);
        Logger.i(TAG, uri.toString());
        return uri;
    }

    /**
     * 获取资源的id
     * 使用方法：
     * 获取当前应用的资源id
     *      getResourceId("ic_launcher", "drawable", mContext.getPackageName())
     * 获取其他应用的资源id
     *      getResourceId("icon", "drawable", "org.anddev.android.testproject")
     *      也可以这么做
     *      getResourceId("org.loveandroid.androidtest:drawable/icon",null,null)
     * 获取系统资源
     *      getResourceId("actionbar_bg", "drawable","android");  //注意，最后一个参数必须是“android”
     * @param context
     * @param name
     * @param type
     * @param packageName
     * @return < 0 表示获取失败
     */
    public static int getResourceId(Context context,String name,String type,String packageName){
        Resources themeResources = null;
        PackageManager pm = context.getPackageManager();
        try {
            themeResources = pm.getResourcesForApplication(packageName);
            return themeResources.getIdentifier(name, type, packageName);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, e);
        }
        return -1;
    }

    /**
     * 从Assets中读取文本文件内容
     * @param context
     * @param fileName
     * @param encoding
     * @return
     */
    public static String getStringFromAssets(Context context, String fileName, String encoding) {
        String result = "";
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            // 获取文件的字节数
            int lenght = in.available();
            // 创建byte数组
            byte[] buffer = new byte[lenght];
            // 将文件中的数据读到byte数组中
            in.read(buffer);
            result = new String(buffer, encoding);
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return result;
    }

    /**
     * 从Raw文件获取文本内容
     * @return
     */
    public String getStringFromRaw(Context context, int resId){
        String result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return result;
    }

}
