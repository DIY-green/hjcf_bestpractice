/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * Description: 直接获取Bitmap的回调实现类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public abstract class BitmapCallback extends Callback<Bitmap> {

    @Override
    public Bitmap parseNetworkResponse(Response response) throws Exception {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
