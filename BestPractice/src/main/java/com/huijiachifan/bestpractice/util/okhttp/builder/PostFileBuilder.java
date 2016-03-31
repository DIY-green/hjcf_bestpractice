/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.builder;

import com.huijiachifan.bestpractice.util.okhttp.request.PostFileRequest;
import com.huijiachifan.bestpractice.util.okhttp.request.RequestCall;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Description: OkHttp文件上传Builder
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class PostFileBuilder extends OkHttpRequestBuilder {

    private File mFile;
    private MediaType mMediaType;

    public OkHttpRequestBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType) {
        this.mMediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostFileRequest(mUrl, mTag, mParams, mHeaders, mFile, mMediaType).build();
    }

    @Override
    public PostFileBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public PostFileBuilder tag(Object tag) {
        this.mTag = tag;
        return this;
    }

    @Override
    public PostFileBuilder params(Map<String, String> params) {
        this.mParams = params;
        return this;
    }

    @Override
    public PostFileBuilder addParams(String key, String val) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return this;
    }

    @Override
    public PostFileBuilder headers(Map<String, String> headers) {
        this.mHeaders = headers;
        return this;
    }

    @Override
    public PostFileBuilder addHeader(String key, String val) {
        if (this.mHeaders == null) {
            mHeaders = new LinkedHashMap<>();
        }
        mHeaders.put(key, val);
        return this;
    }

}
