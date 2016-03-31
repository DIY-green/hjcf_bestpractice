/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.builder;

import com.huijiachifan.bestpractice.util.okhttp.request.PostFormRequest;
import com.huijiachifan.bestpractice.util.okhttp.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: OkHttp表单上传Builder
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class PostFormBuilder extends OkHttpRequestBuilder {

    private List<FileInput> mFiles = new ArrayList<>();

    @Override
    public RequestCall build() {
        return new PostFormRequest(mUrl, mTag, mParams, mHeaders, mFiles).build();
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        mFiles.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    //
    @Override
    public PostFormBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    @Override
    public PostFormBuilder tag(Object tag) {
        this.mTag = tag;
        return this;
    }

    @Override
    public PostFormBuilder params(Map<String, String> params) {
        this.mParams = params;
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return this;
    }

    @Override
    public PostFormBuilder headers(Map<String, String> headers) {
        this.mHeaders = headers;
        return this;
    }

    @Override
    public PostFormBuilder addHeader(String key, String val) {
        if (this.mHeaders == null) {
            mHeaders = new LinkedHashMap<>();
        }
        mHeaders.put(key, val);
        return this;
    }
}
