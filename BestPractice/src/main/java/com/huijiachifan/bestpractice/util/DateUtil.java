/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import java.util.Calendar;

/**
 * Description: 日期工具类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月26日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class DateUtil {

    private static final Calendar cal = Calendar.getInstance();

    /*
     * 获取目前年份
     */
    public static int getYear() {
        int year = cal.get(Calendar.YEAR); // 获取年份
        return year;
    }

    /*
     * 获取目前月份
     */
    public static int getMonth() {
        int month = cal.get(Calendar.MONTH); // 获取月份
        return month;
    }

    /*
     * 获取目前日
     */
    public static int getDay() {
        int day = cal.get(Calendar.DATE); // 获取日
        return day;
    }

    /*
     * 获取目前时
     */
    public static int getHour() {
        int hour = cal.get(Calendar.HOUR); // 小时
        return hour;
    }

    /*
     * 获取目前分
     */
    public static int getMinute() {
        int minute = cal.get(Calendar.MINUTE); // 分
        return minute;
    }

    /*
     * 获取目前秒
     */
    public static int getSec() {
        int sec = cal.get(Calendar.SECOND); // 秒
        return sec;
    }
}
