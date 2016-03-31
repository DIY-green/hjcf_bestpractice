/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.huijiachifan.bestpractice.util.Logger;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月26日
 *
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */
public class AppManager {

    //==========常量==========
    private static final String TAG = "AppManager";

    //==========普通变量==========
	private static Stack<Activity> sActivityStack;
	private static AppManager sInstance;

	private WeakReference<Activity> sCurrentActivityWeakRef;
	
	private AppManager(){}

	public static AppManager getAppManager(){
		if(sInstance == null){
            Logger.mTag = TAG;
			sInstance =new AppManager();
		}
		return sInstance;
	}

    //==========逻辑方法==========
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(@NonNull Activity activity){
		if(sActivityStack == null){
			sActivityStack = new Stack<Activity>();
		}
		sActivityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity(){
		Activity activity = sActivityStack.lastElement();
		return activity;
	}

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    /**
     * 通过反射获取当前 Activity
     * 思路：
     * 1 获取ActivityThread中所有的ActivityRecord
     * 2 从ActivityRecord中获取状态不是pause的Activity并返回
     * 不是很推荐，主要是有以下的不足：
     * · 反射通常会比较慢
     * · 不稳定性，这个才是不推荐的原因，Android框架代码存在修改的可能性，
     * 谁要无法100%保证mActivities，paused固定不变。所以可靠性不是完全可靠。
     * @return
     */
    public static Activity getCurrentActivityReflect() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            Logger.e(e);
        } catch (NoSuchMethodException e) {
            Logger.e(e);
        } catch (IllegalAccessException e) {
            Logger.e(e);
        } catch (InvocationTargetException e) {
            Logger.e(e);
        } catch (NoSuchFieldException e) {
            Logger.e(e);
        }
        return null;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void removeActivity(){
		Activity activity = sActivityStack.lastElement();
		removeActivity(activity);
	}

	/**
	 * 删除指定的Activity
	 */
	public void removeActivity(@NonNull Activity activity){
        finishActivity(activity);
        sActivityStack.remove(activity);
    }

    /**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(@NonNull Class<?> cls){
		for (Activity activity : sActivityStack) {
			if(activity.getClass().equals(cls) ){
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(){
        for (Activity activity : sActivityStack) {
            finishActivity(activity);
        }
		sActivityStack.clear();
	}

    /**
     * 安全结束一个Activity
     */
    private void finishActivity(@NonNull Activity activity) {
        if (!activity.isFinishing()) {
            activity.finish();
            activity = null;
        }
    }

    /**
	 * 退出应用程序
	 */
	public void exitApp(@NonNull Context context) {
		try {
			finishAllActivity();
			/*
			Intent intent = new Intent(context, MainActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(    
            		context, 0, intent,    
                    Intent.FLAG_ACTIVITY_NEW_TASK);                                                 
            //退出程序                                          
            AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);    
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,    
                    restartIntent); // 1秒钟后重启应用
            */
			// 杀死该应用进程
			android.os.Process.killProcess(android.os.Process.myPid());
		} catch (Exception e) {
            Logger.e(e);
        } finally {
            System.exit(0);
        }
    }
}