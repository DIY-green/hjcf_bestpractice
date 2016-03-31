/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.callback;

import okhttp3.Response;

import java.io.IOException;

/**
 * Description: 将返回解析为字符串的Callback实现类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public abstract class StringCallback extends Callback<String> {

    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().string();
    }

}
