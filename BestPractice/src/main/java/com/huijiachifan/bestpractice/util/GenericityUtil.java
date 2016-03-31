/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import java.lang.reflect.ParameterizedType;

/**
 * Description: 操作泛型的工具类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年3月2日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class GenericityUtil<T> {

    public Class<T> getRealType(T t) {
        ParameterizedType parameterizedType = (ParameterizedType) t.getClass().getGenericSuperclass();
        Class clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        Logger.i("clazz ==>> " + clazz);
        return clazz;
    }

}
