/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.request;

import com.huijiachifan.bestpractice.util.ExceptionUtil;

import com.huijiachifan.bestpractice.util.okhttp.callback.Callback;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description: 封装OkHttp请求基类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public abstract class OkHttpRequest {

    protected String mUrl;
    protected Object mTag;
    protected Map<String, String> mParams;
    protected Map<String, String> mHeaders;
    protected Request.Builder mBuilder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers) {
        this.mUrl = url;
        this.mTag = tag;
        this.mParams = params;
        this.mHeaders = headers;
        if (mUrl == null) {
            ExceptionUtil.illegalArgument("url can not be null.");
        }
    }

    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return requestBody;
    }

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public RequestCall build() {
        return new RequestCall(this);
    }

    public Request generateRequest(Callback callback) {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(mBuilder, requestBody);
    }

    private void prepareBuilder() {
        this.mBuilder.url(mUrl).tag(mTag);
        appendHeaders();
    }

    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (mHeaders == null || mHeaders.isEmpty()) return;
        for (String key : mHeaders.keySet()) {
            String value = mHeaders.get(key);
            value = value == null ? "" : value;
            headerBuilder.add(key, value);
        }
        this.mBuilder.headers(headerBuilder.build());
    }

    @Override
    public String toString() {
        return "OkHttpRequest{" +
                "url='" + mUrl + '\'' +
                ", tag=" + mTag +
                ", params=" + mParams +
                ", headers=" + mHeaders +
                '}';
    }

}
