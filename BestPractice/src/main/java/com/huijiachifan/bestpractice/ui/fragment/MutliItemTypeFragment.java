/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import com.huijiachifan.bestpractice.R;
import com.huijiachifan.bestpractice.adapter.MutilItemTypeAdapter;
import com.huijiachifan.bestpractice.model.bean.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 多种类型条目演示
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class MutliItemTypeFragment extends ListFragment {

    private List<ChatMessage> mDataList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(null);
        setListAdapter(new MutilItemTypeAdapter(getActivity(), mDataList));
    }

    private void initDatas() {
        ChatMessage msg = null;
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);

        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_renma, "renma", "where are you ",
                null, true);
        mDataList.add(msg);
        msg = new ChatMessage(R.mipmap.ic_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDataList.add(msg);
    }
}