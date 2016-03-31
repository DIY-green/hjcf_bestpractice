package com.huijiachifan.bestpractice.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import com.huijiachifan.bestpractice.R;
import com.huijiachifan.bestpractice.adapter.SingleItemTypeAdapter;
import com.huijiachifan.bestpractice.model.bean.SingleItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: hongyang 封装的通用Adapter，单一类型条目实例Fragment
 * 参考：http://blog.csdn.net/lmj623565791/article/details/38902805/
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author hongyang
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class SingleItemTypeFragment extends ListFragment {

    private List<SingleItemBean> mDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(new SingleItemTypeAdapter(getActivity(), mDataList, R.layout.item_single_listview));
    }

    private void initDatas() {
        mDataList = new ArrayList<SingleItemBean>();
        SingleItemBean singleItemBean = new SingleItemBean("Android新技能Get 1",
                "Android打造万能的ListView和GridView适配器", "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 2", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 3", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 4", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 5", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 6", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 7", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 8", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
        singleItemBean = new SingleItemBean("Android新技能Get 9", "Android打造万能的ListView和GridView适配器",
                "2014-12-12", "10086");
        mDataList.add(singleItemBean);
    }
}
