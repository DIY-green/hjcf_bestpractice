/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util.okhttp.cookie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Description: 自定义简单CookieJar实现
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月19日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class SimpleCookieJar implements CookieJar {

    private final List<Cookie> mAllCookies = new ArrayList<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        mAllCookies.addAll(cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result = new ArrayList<>();
        for (Cookie cookie : mAllCookies) {
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }
}
