/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.adapter.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description: hongyang 封装的通用Adapter类，支持多种item类型
 * 参考：http://blog.csdn.net/lmj623565791/article/details/38902805/
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author  hongyang
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemTypeSupportable<T> mMultiItemTypeSupportable;

    public MultiItemCommonAdapter(Context context, List<T> dataList,
                                  MultiItemTypeSupportable<T> multiItemTypeSupportable) {
        super(context, dataList, -1);
        mMultiItemTypeSupportable = multiItemTypeSupportable;
    }

    @Override
    public int getViewTypeCount() {
        if (mMultiItemTypeSupportable != null) {
            return mMultiItemTypeSupportable.getViewTypeCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupportable != null) {
            return mMultiItemTypeSupportable.getItemViewType(position,
                    mDataList.get(position));
        }
        return super.getItemViewType(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mMultiItemTypeSupportable == null) {
            return super.getView(position, convertView, parent);
        }
        int layoutId = mMultiItemTypeSupportable.getLayoutId(position,
                getItem(position));
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

}
