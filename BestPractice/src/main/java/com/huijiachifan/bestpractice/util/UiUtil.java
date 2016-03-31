/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

/**
 * Description:
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月27日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class UiUtil {

    /**
     * 判断当前线程是否为主线程
     * @return
     */
    public static boolean isMainThread() {
        // Thread.currentThread() == Looper.getMainLooper().getThread()
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 实现activity变暗的效果
     * 窗口背景变暗
     *      UiUtil.dimBackground(activity, 1.0f,0.5f);
     * 窗口背景变亮
     *      UiUtil.dimBackground(activity, 0.5f,1.0f);
     * @param activity
     * @param from
     * @param to
     */
    public static void dimBackground(Activity activity, final float from, final float to) {
        final Window window = activity.getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });
        valueAnimator.start();
        activity = null;
    }

}
