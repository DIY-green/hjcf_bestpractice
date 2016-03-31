/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.request;

import com.huijiachifan.bestpractice.util.ExceptionUtil;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description: 封装普通Post请求
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class PostStringRequest extends OkHttpRequest {

    private static final MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String mContent;
    private MediaType mMediaType;

    public PostStringRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, String content, MediaType mediaType) {
        super(url, tag, params, headers);
        this.mContent = content;
        this.mMediaType = mediaType;
        if (mContent == null) {
            ExceptionUtil.illegalArgument("the content can not be null !");
        }
        if (mMediaType == null) {
            this.mMediaType = MEDIA_TYPE_PLAIN;
        }
    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mMediaType, mContent);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return mBuilder.post(requestBody).build();
    }

    @Override
    public String toString() {
        return super.toString() + ", requestBody{content=" + mContent + "} ";
    }
}
