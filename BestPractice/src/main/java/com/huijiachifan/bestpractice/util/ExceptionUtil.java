/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

/**
 * Description: Exception工具类，简化常见异常的抛出
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月17日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class ExceptionUtil {

    public static void illegalArgument(String msg) {
        throw new IllegalArgumentException(msg);
    }

}
