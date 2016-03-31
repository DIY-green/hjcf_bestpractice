/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.huijiachifan.bestpractice.R;
import com.huijiachifan.bestpractice.adapter.common.CommonAdapter;
import com.huijiachifan.bestpractice.adapter.common.ViewHolder;
import com.huijiachifan.bestpractice.model.bean.SingleItemBean;
import com.huijiachifan.bestpractice.util.Logger;
import com.huijiachifan.bestpractice.util.TipUtil;

import java.util.List;

/**
 * Description: 单类型条目Adapter示例
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class SingleItemTypeAdapter extends CommonAdapter<SingleItemBean> {

    public SingleItemTypeAdapter(@NonNull Context context, @NonNull List<SingleItemBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    public void convert(@NonNull final ViewHolder viewHolder, @NonNull final SingleItemBean singleItemBean) {
        viewHolder.setText(R.id.tv_title, singleItemBean.getTitle())
                .setText(R.id.tv_desc, singleItemBean.getDesc())
                .setText(R.id.tv_time, singleItemBean.getTime())
                .setText(R.id.tv_phone, singleItemBean.getPhone());
        viewHolder.setOnClickListener(R.id.tv_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e(singleItemBean.getTitle());
                TipUtil.toast(singleItemBean.getTitle());
            }
        });
        viewHolder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e(singleItemBean.toString());
                TipUtil.toast(singleItemBean.toString());
            }
        });
    }
}
