/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.huijiachifan.bestpractice.util.okhttp.builder.GetBuilder;
import com.huijiachifan.bestpractice.util.okhttp.builder.PostFileBuilder;
import com.huijiachifan.bestpractice.util.okhttp.builder.PostFormBuilder;
import com.huijiachifan.bestpractice.util.okhttp.builder.PostStringBuilder;
import com.huijiachifan.bestpractice.util.okhttp.callback.Callback;
import com.huijiachifan.bestpractice.util.okhttp.cookie.SimpleCookieJar;
import com.huijiachifan.bestpractice.util.okhttp.https.HttpsUtil;
import com.huijiachifan.bestpractice.util.okhttp.request.RequestCall;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Description: OkHttp工具类，对外提供的接口
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月19日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

//==========//==========//==========//==========//==========//==========//==========//==========//==========//
public class OkHttpUtil {

    //==========常量=========
    private static final String TAG = "OkHttpUtil";                     //
    public static final long DEFAULT_MILLISECONDS = 10000;              //

    //==========普通变量==========
    private static OkHttpUtil sInstance;                                //
    private OkHttpClient mOkHttpClient;                                 //
    private Handler mDelivery;                                          //
    private boolean mIsDebug;                                           //
    private String mTag;                                                //

    //==========初始化==========
    private OkHttpUtil() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
        okHttpClientBuilder.cookieJar(new SimpleCookieJar());            // cookie enabled
//        this.mDelivery = new Handler(Looper.getMainLooper());
        this.mDelivery = new Handler();
        // TODO 具体作用未知
        if (true) {
            okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        }
        this.mOkHttpClient = okHttpClientBuilder.build();
    }

    public static OkHttpUtil getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpUtil.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpUtil();
                }
            }
        }
        return sInstance;
    }

    public Handler getDelivery() {
        return mDelivery;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public OkHttpUtil debug(String tag) {
        this.mIsDebug = true;
        this.mTag = tag;
        return this;
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (mIsDebug) {
            if (TextUtils.isEmpty(mTag)) {
                mTag = TAG;
            }
            Log.d(TAG, "{method:" + requestCall.getRequest().method() + ", detail:" + requestCall.getOkHttpRequest().toString() + "}");
        }
        if (callback == null) {
            callback = Callback.CALLBACK_DEFAULT;
        }
        final Callback finalCallback = callback;

        requestCall.getCall().enqueue(new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailResultCallback(call, e, finalCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() >= 400 && response.code() <= 599) {
                    try {
                        sendFailResultCallback(call, new RuntimeException(response.body().string()), finalCallback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    Object o = finalCallback.parseNetworkResponse(response);
                    sendSuccessResultCallback(o, finalCallback);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback);
                }
            }
        });
    }

    private void sendFailResultCallback(final Call call, final Exception e, final Callback callback) {
        if (callback == null) return;
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e);
                callback.onAfter();
            }
        });
    }

    private void sendSuccessResultCallback(final Object object,final Callback callback) {
        if (callback == null) return;
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public void setCertificates(InputStream... certificates) {
        mOkHttpClient = getOkHttpClient().newBuilder()
                .sslSocketFactory(HttpsUtil.getSslSocketFactory(certificates, null, null))
                .build();
    }

    public void setConnectTimeout(int timeout, TimeUnit units) {
        mOkHttpClient = getOkHttpClient().newBuilder()
                .connectTimeout(timeout, units)
                .build();
    }

}
