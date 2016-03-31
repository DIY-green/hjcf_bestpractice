/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.interfaces;

/**
 * Description: Http请求的回调接口
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public interface HttpCallback {

    /**
     * 加载中，显示进度
     */
    void onLoading(int taskId, float progress);

    /**
     * 成功时回调
     */
    <T> void onSuccess(int taskId, T t);

    /**
     * 失败时回调
     */
    void onError(int taskId, String error);

    /**
     * 没有网络时回调
     */
    void onNoNetwork();

}
