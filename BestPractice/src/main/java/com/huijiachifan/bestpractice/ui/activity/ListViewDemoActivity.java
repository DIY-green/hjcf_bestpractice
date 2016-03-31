/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.huijiachifan.bestpractice.R;
import com.huijiachifan.bestpractice.adapter.ListViewDemoAdapter;
import com.huijiachifan.bestpractice.util.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description: ListViewDemo页面
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class ListViewDemoActivity extends AppCompatActivity {

    @Bind(R.id.tl_tab)
    TabLayout tlTab;
    @Bind(R.id.vp_content)
    ViewPager vpContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        Logger.e("initData()");
        this.vpContent.setAdapter(new ListViewDemoAdapter(getSupportFragmentManager()));
        this.tlTab.setupWithViewPager(vpContent);
    }
}
