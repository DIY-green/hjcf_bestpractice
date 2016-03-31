/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.builder;

import com.huijiachifan.bestpractice.util.okhttp.request.RequestCall;

import java.util.Map;

/**
 * Description: OkHttp请求构造抽象类（基类）
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public abstract class OkHttpRequestBuilder {

    protected String mUrl;
    protected Object mTag;
    protected Map<String, String> mHeaders;
    protected Map<String, String> mParams;

    public abstract OkHttpRequestBuilder url(String url);

    public abstract OkHttpRequestBuilder tag(Object tag);

    public abstract OkHttpRequestBuilder params(Map<String, String> params);

    public abstract OkHttpRequestBuilder addParams(String key, String val);

    public abstract OkHttpRequestBuilder headers(Map<String, String> headers);

    public abstract OkHttpRequestBuilder addHeader(String key, String val);

    public abstract RequestCall build();

}
