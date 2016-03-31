/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huijiachifan.bestpractice.ui.fragment.MutliItemTypeFragment;
import com.huijiachifan.bestpractice.ui.fragment.SingleItemTypeFragment;

/**
 * Description: 多类型条目Adapter示例
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class ListViewDemoAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 2;

    private Fragment mSingleItemTypeFragment = new SingleItemTypeFragment();
    private Fragment mMutilItemTypeFragment = new MutliItemTypeFragment();

    public ListViewDemoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return position==1 ? mMutilItemTypeFragment : mSingleItemTypeFragment;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position==1 ? "Mutli Item Type" : "Single Item Type";
    }
}
