/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.adapter.common;

/**
 * Description: hongyang 封装的通用Adapter类，扩充支持多种type的能力
 * 参考：http://blog.csdn.net/lmj623565791/article/details/38902805/
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author  hongyang
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public interface MultiItemTypeSupportable<T> {

    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);

}