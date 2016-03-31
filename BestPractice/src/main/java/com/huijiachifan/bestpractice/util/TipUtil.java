/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.huijiachifan.bestpractice.BestPracticeApp;
import com.huijiachifan.bestpractice.R;

/**
 * Description: 提示信息显示工具类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月27日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class TipUtil {

    //==========常量==========
    private static final String TAG = "TipUtil";
    public static final int LONG = Toast.LENGTH_LONG;
    public static final int SHORT = Toast.LENGTH_SHORT;
    public static final TipGravity CENTER = new TipGravity(Gravity.CENTER, 0, 0);

    //==========普通静态变量==========
    private static Toast sToast = null;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private static ThreadToastTask sThreadToastTask = new ThreadToastTask();
    private static AlertDialog.Builder sBuilder;                        // 一个Context下只产生一个AlertDialog.Builder实例
    private static ProgressDialog sCycleProgressDialog;
    private static ProgressDialog sHorizontalProgressDialog;
    private static Context sLastContext = null;

    //==========Toast==========//
    public static void toast(String msg) {
        toast(msg, Toast.LENGTH_SHORT, null);
    }

    public static void toast(String msg, int duration) {
        toast(msg, duration, null);
    }

    private static void toast(String msg, int duration, TipGravity gravity) {
        if (TextUtils.isEmpty(msg)) return;
        if (UiUtil.isMainThread()) {
            unRepeatToast(msg, duration, gravity);
        } else {
            sHandler.post(sThreadToastTask.getInstance(msg, duration, gravity));
        }
    }

    /**
     * 防止Toast重复显示
     * @param msg
     * @param duration
     * @param gravity
     */
    private static void unRepeatToast(String msg, int duration, TipGravity gravity) {
        if (duration < 0) {
            duration = Toast.LENGTH_SHORT;
        }
        if (sToast == null) {
            sToast = Toast.makeText(BestPracticeApp.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
            sToast.setDuration(duration);
        }
        if (gravity != null) {
            sToast.setGravity(gravity.gravity, gravity.xOffset, gravity.yOffset);
        }
        sToast.show();
    }
    //==========Toast==========//

    //==========AlertDialog==========//
    public static void displayOneBtnDialog(@NonNull Context context, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        TipInfo tipInfo = TipInfo.createTipInfo(msg);
        displayOneBtnDialog(context, tipInfo, null);
    }

    public static void displayOneBtnDialog(@NonNull Context context, TipInfo tipInfo, DialogInterface.OnClickListener sureListener) {
        if (tipInfo == null) return;
        AlertDialog.Builder builder = getBuilder(context, tipInfo);
        builder = addAlertDialogPositiveButton(tipInfo.sureBtnText, sureListener, builder);
        builder.setNegativeButton(null, null);
        // 显示出该对话框
        builder.show();
    }

    public static void displayTwoBtnDialog(@NonNull Context context, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        TipInfo tipInfo = TipInfo.createTipInfo(msg);
        displayTwoBtnDialog(context, tipInfo, null, null);
    }

    public static void displayTwoBtnDialog(@NonNull Context context, String msg, DialogInterface.OnClickListener sureListener) {
        if (TextUtils.isEmpty(msg)) return;
        TipInfo tipInfo = TipInfo.createTipInfo(msg);
        displayTwoBtnDialog(context, tipInfo, null, sureListener);
    }

    public static void displayTwoBtnDialog(@NonNull Context context, String msg, DialogInterface.OnClickListener cancelListener, DialogInterface.OnClickListener sureListener) {
        if (TextUtils.isEmpty(msg)) return;
        TipInfo tipInfo = TipInfo.createTipInfo(msg);
        displayTwoBtnDialog(context, tipInfo, cancelListener, sureListener);
    }

    public static void displayTwoBtnDialog(@NonNull Context context, TipInfo tipInfo, DialogInterface.OnClickListener cancelListener, DialogInterface.OnClickListener sureListener) {
        if (tipInfo == null) return;
        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = getBuilder(context, tipInfo);
        builder = addAlertDialogPositiveButton(tipInfo.sureBtnText, sureListener, builder);
        builder = addAlertDialogNegativeButton(tipInfo.cancelBtnText, cancelListener, builder);
        // 显示出该对话框
        builder.show();
    }

    @NonNull
    private static AlertDialog.Builder getBuilder(@NonNull Context context, TipInfo tipInfo) {
        AlertDialog.Builder builder;
        if (context == sLastContext) {
            if (sBuilder != null) {
                builder = sBuilder;
            } else {
                builder = createNewBuilder(context);
            }
        } else {
            builder = createNewBuilder(context);
            sBuilder = null;
            sLastContext = null;
            sLastContext = context;
            sBuilder = builder;
        }
        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        // 设置Title的图标
        builder.setIcon(tipInfo.iconResId);
        // 设置Title的内容
        builder.setTitle(tipInfo.title);
        // 设置Content来显示一个信息
        builder.setMessage(tipInfo.msg);
        return builder;
    }

    @NonNull
    private static AlertDialog.Builder createNewBuilder(@NonNull Context context) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        sBuilder = builder;
        return builder;
    }

    private static AlertDialog.Builder addAlertDialogPositiveButton(String btnText, DialogInterface.OnClickListener listener, final AlertDialog.Builder builder) {
        listener = getDefaultOnClickListener(listener);
        // 设置一个PositiveButton
        builder.setPositiveButton(btnText, listener);
        return builder;
    }

    private static AlertDialog.Builder addAlertDialogNegativeButton(String btnText, DialogInterface.OnClickListener listener, final AlertDialog.Builder builder) {
        listener = getDefaultOnClickListener(listener);
        // 设置一个PositiveButton
        builder.setNegativeButton(btnText, listener);
        return builder;
    }

    @NonNull
    private static DialogInterface.OnClickListener getDefaultOnClickListener(DialogInterface.OnClickListener listener) {
        if (listener != null) return listener;
        listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Logger.e(TAG, "AlertDialog Button Click!");
            }
        };
        return listener;
    }
    //==========AlertDialog==========//

    //==========ProgressDialog==========//
    public static void displayProgressDialog(@NonNull Context context, String msg) {
        ProgressDialog progressDialog = createCycleProgressDialog(context, msg);
        progressDialog.show();
    }

    public static void displayProgressDialog(@NonNull Context context, String msg, boolean cancelable) {
        ProgressDialog progressDialog = createCycleProgressDialog(context, msg);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public static void displayHorizontalProgressDialog(@NonNull Context context, String title) {
        ProgressDialog progressDialog = createHorizontalProgressDialog(context, title);
        progressDialog.show();
    }

    public static void updateHorizontalProgressDialog(@NonNull Context context, int progress) {
        if (context != sLastContext) return;
        if (sHorizontalProgressDialog == null) return;
        sHorizontalProgressDialog.setProgress(progress);
    }

    /**
     * 更新水平进度对话框的进度
     * @param context
     * @param numberFormat 例："%1d kb/%2d kb"
     * @param currentValue
     */
    public static void updateHorizontalProgressDialog(@NonNull Context context, String numberFormat, String currentValue) {
        if (context != sLastContext) return;
        if (sHorizontalProgressDialog == null) return;
        sHorizontalProgressDialog.setProgressNumberFormat(numberFormat);
    }

    public static void hideProgressDialog(@NonNull Context context) {
        if (context != sLastContext) return;
        if (sCycleProgressDialog != null) {
            sCycleProgressDialog.cancel();
        } else if (sHorizontalProgressDialog != null) {
            sHorizontalProgressDialog.cancel();
        }
    }

    @NonNull
    private static ProgressDialog createCycleProgressDialog(@NonNull Context context, String msg) {
        ProgressDialog progressDialog = getProgressDialog(context, true);
        progressDialog.setMessage(msg);
        return progressDialog;
    }

    @NonNull
    private static ProgressDialog createHorizontalProgressDialog(@NonNull Context context, String title) {
        ProgressDialog progressDialog = getProgressDialog(context, false);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        progressDialog.setMax(100);
        return progressDialog;
    }

    @NonNull
    private static ProgressDialog getProgressDialog(@NonNull Context context, boolean isCycle) {
        ProgressDialog progressDialog;
        if (context == sLastContext) {
            if (isCycle) {
                if (sCycleProgressDialog != null) {
                    progressDialog = sCycleProgressDialog;
                } else {
                    progressDialog = new ProgressDialog(context);
                }
            } else {
                if (sHorizontalProgressDialog != null) {
                    progressDialog = sHorizontalProgressDialog;
                } else {
                    progressDialog = new ProgressDialog(context);
                }
            }
        } else {
            progressDialog = new ProgressDialog(context);
            if (isCycle) {
                sCycleProgressDialog = null;
                sCycleProgressDialog = progressDialog;
            } else {
                sHorizontalProgressDialog = null;
                sHorizontalProgressDialog = progressDialog;
            }
            sLastContext = null;
            sLastContext = context;
        }
        progressDialog.getProgress();
        return progressDialog;
    }
    //==========ProgressDialog==========//

    //==========逻辑方法==========//
    public static void destory(@NonNull Context context) {
        if (context != sLastContext) {
            context = null;
            return;
        }
        hideProgressDialog(context);
        context = null;
        sLastContext = null;
        sCycleProgressDialog = null;
        sBuilder = null;
    }
    //==========逻辑方法==========//

    //==========内部类==========
    public static final class TipGravity {

        public int gravity;
        public int xOffset;
        public int yOffset;

        public TipGravity(int gravity, int xOffset, int yOffset) {
            this.gravity = gravity;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }
    }

    public static final class TipInfo {

        public int iconResId = R.drawable.ic_launcher;
        public String title = "提示";
        public String msg;
        private String sureBtnText = "确定";
        private String cancelBtnText = "取消";

        public TipInfo(int iconResId, String msg, String title) {
            if (iconResId > 0) {
                this.iconResId = iconResId;
            }
            if (!TextUtils.isEmpty(title)) {
                this.title = title;
            }
            this.msg = msg;
        }

        public static TipInfo createTipInfo(String msg) {
            return new TipInfo(-1, msg, null);
        }

        public static TipInfo createTipInfo(String msg, String title) {
            return new TipInfo(-1, msg, title);
        }

        public String getCancelBtnText() {
            return cancelBtnText;
        }

        public void setCancelBtnText(String cancelBtnText) {
            this.cancelBtnText = cancelBtnText;
        }

        public String getSureBtnText() {
            return sureBtnText;
        }

        public void setSureBtnText(String sureBtnText) {
            this.sureBtnText = sureBtnText;
        }
    }

    private static final class ThreadToastTask implements Runnable {

        private String msg;
        private int duration;
        private TipGravity gravity;

        private static final ThreadToastTask sInstance = new ThreadToastTask();

        private ThreadToastTask() {}

        public static ThreadToastTask getInstance(String msg, int duration, TipGravity gravity) {
            sInstance.msg = msg;
            sInstance.duration = duration;
            sInstance.gravity = gravity;
            return sInstance;
        }

        @Override
        public void run() {
            unRepeatToast(msg, duration, gravity);
        }
    }

}
