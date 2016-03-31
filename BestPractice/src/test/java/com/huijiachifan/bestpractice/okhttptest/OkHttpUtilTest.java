package com.huijiachifan.bestpractice.okhttptest;

import android.util.Log;

import com.huijiachifan.bestpractice.util.okhttp.OkHttpUtil;
import com.huijiachifan.bestpractice.util.okhttp.callback.StringCallback;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/2/24.
 */
public class OkHttpUtilTest {

    private static final String TAG  = "OkHttpUtilTest";
    private String mBaseUrl = "http://10.138.114.147:8080/okHttpServer/";

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            log("onBefore:" + request.toString());
        }

        @Override
        public void onAfter() {
            super.onAfter();
            log("onAfter");
        }

        @Override
        public void onError(Call call, Exception e) {
            log("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response) {
            log("onResponse:" + response);
        }

        @Override
        public void inProgress(float progress) {
            log("inProgress:" + (int) (100 * progress));
        }
    }

    @Test
    public void getHtml() {
        String url = "http://www.csdn.net/";
        OkHttpUtil
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    private void log(String str) {
        System.out.println(str);
    }

}
