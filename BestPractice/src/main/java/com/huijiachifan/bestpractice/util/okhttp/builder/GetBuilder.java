/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.builder;

import com.huijiachifan.bestpractice.util.okhttp.request.GetRequest;
import com.huijiachifan.bestpractice.util.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Get请求构造器
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class GetBuilder extends OkHttpRequestBuilder {

    @Override
    public OkHttpRequestBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public OkHttpRequestBuilder tag(Object tag) {
        this.mTag = tag;
        return this;
    }

    @Override
    public OkHttpRequestBuilder params(Map<String, String> params) {
        this.mParams = params;
        return this;
    }

    @Override
    public OkHttpRequestBuilder addParams(String key, String val) {
        if (this.mParams == null) {
            this.mParams = new LinkedHashMap<>();
        }
        this.mParams.put(key, val);
        return this;
    }

    @Override
    public OkHttpRequestBuilder headers(Map<String, String> headers) {
        this.mHeaders = headers;
        return this;
    }

    @Override
    public OkHttpRequestBuilder addHeader(String key, String val) {
        if (this.mHeaders == null) {
            this.mHeaders = new LinkedHashMap<>();
        }
        this.mHeaders.put(key, val);
        return this;
    }

    @Override
    public RequestCall build() {
        if (mParams != null) {
            mUrl = appendParams(mUrl, mParams);
        }
        return new GetRequest(mUrl, mTag, mParams, mHeaders).build();
    }

    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key));
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }
}
