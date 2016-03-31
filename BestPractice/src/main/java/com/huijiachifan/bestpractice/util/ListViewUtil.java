/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Description: ListView工具类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月7日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class ListViewUtil {

    /**
     * 为ListView设置EmptyView
     * @param listView
     * @param emptyView
     */
    public static void setEmptyView(@NonNull ListView listView, @NonNull View emptyView) {
        ViewGroup lvParentVG = (ViewGroup)listView.getParent();
        lvParentVG = getParentLayout(lvParentVG);
        int childCount = lvParentVG.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View temp = lvParentVG.getChildAt(i);
            if (temp == emptyView) return;
        }
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        lvParentVG.addView(emptyView);
        listView.setEmptyView(emptyView);
    }

    private static ViewGroup getParentLayout(ViewGroup lvParentVG) {
        if (!(lvParentVG instanceof LinearLayout)
                && !(lvParentVG instanceof RelativeLayout)
                && !(lvParentVG instanceof FrameLayout)) {
            lvParentVG = (ViewGroup) lvParentVG.getParent();
            lvParentVG = getParentLayout(lvParentVG);
        }
        return lvParentVG;
    }

    /**
     * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
     * @param listView
     */
    static public void setListViewHeight(ListView listView) {

        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    static public void setListViewHeight(ListView listView, int added) {

        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + added;
        listView.setLayoutParams(params);
    }

}
