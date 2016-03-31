/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.huijiachifan.bestpractice.util.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Description: 应用程序配置类：用于保存用户相关信息及设置
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月25日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class AppConfig {

    //==========常量==========
    private static final String APP_CONFIG = "config";
    private static final String TAG = "AppConfig";

	public static final String DEFAULT_SAVE_IMAGE_PATH = Environment    // 默认存放图片的路径
			.getExternalStorageDirectory()
			+ File.separator
			+ "HJCFBestPractice"
			+ File.separator
			+ "image"
			+ File.separator;
	public static final String DEFAULT_SAVE_FILE_PATH = Environment     // 默认存放图片的路径
			.getExternalStorageDirectory()
			+ File.separator
			+ "HJCFBestPractice"
			+ File.separator
			+ "download"
			+ File.separator;

    //==========普通变量==========
	private Context mContext;
	private static AppConfig sAppConfig;

    private AppConfig() {}

	public static AppConfig getAppConfig(Context context) {
		if (sAppConfig == null) {
            Logger.mTag = TAG;
			sAppConfig = new AppConfig();
			sAppConfig.mContext = context;
		}
		return sAppConfig;
	}

	/**
	 * 获取Preference设置
	 */
	public static SharedPreferences getSharedPreferences(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	public String get(String key) {
		Properties props = get();
		return (props != null) ? props.getProperty(key) : null;
	}

	public Properties get() {
		FileInputStream fis = null;
		Properties props = new Properties();
		try {
			// 读取files目录下的config
			// fis = activity.openFileInput(APP_CONFIG);

			// 读取app_config目录下的config
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			fis = new FileInputStream(dirConf.getPath() + File.separator
					+ APP_CONFIG);

			props.load(fis);
		} catch (Exception e) {
            Logger.e(e);
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
                Logger.e(e);
			}
		}
		return props;
	}

	private void setProps(Properties p) {
		FileOutputStream fos = null;
		try {
			// 把config建在files目录下
			// fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

			// 把config建在(自定义)app_config的目录下
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			File conf = new File(dirConf, APP_CONFIG);
			fos = new FileOutputStream(conf);

			p.store(fos, null);
			fos.flush();
		} catch (Exception e) {
            Logger.e(e);
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
                Logger.e(e);
			}
		}
	}

	public void set(Properties ps) {
		Properties props = get();
		props.putAll(ps);
		setProps(props);
	}

	public void set(String key, String value) {
		Properties props = get();
		props.setProperty(key, value);
		setProps(props);
	}

	public void remove(String... key) {
		Properties props = get();
		for (String k : key)
			props.remove(k);
		setProps(props);
	}
}
