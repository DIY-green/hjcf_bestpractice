/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.utiltest;

import android.net.Uri;

import com.huijiachifan.bestpractice.util.ResourceUtil;

import org.junit.Test;

/**
 * Description:
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月26日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class ResourceUtilTest {

    @Test
    public void getDrawableUrl_test() {
        Uri uri = ResourceUtil.getResourceUrl("ic_launcher.png");
        System.out.println(uri.toString());
    }
}
