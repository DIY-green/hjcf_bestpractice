/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.builder;

import com.huijiachifan.bestpractice.util.okhttp.request.PostStringRequest;
import com.huijiachifan.bestpractice.util.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Description: 简单参数（只有普通字段，不包含文件）的Post请求构造器
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class PostStringBuilder extends OkHttpRequestBuilder {

    private String mContent;
    private MediaType mMediaType;

    public PostStringBuilder content(String content) {
        this.mContent = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType) {
        this.mMediaType = mediaType;
        return this;
    }

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
        if (mParams == null) {
            this.mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return this;
    }

    @Override
    public OkHttpRequestBuilder headers(Map<String, String> headers) {
        this.mHeaders = headers;
        return this;
    }

    @Override
    public OkHttpRequestBuilder addHeader(String key, String val) {
        if (mHeaders == null) {
            this.mHeaders = new LinkedHashMap<>();
        }
        this.mHeaders.put(key, val);
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(mUrl, mTag, mParams, mHeaders, mContent, mMediaType).build();
    }
}
