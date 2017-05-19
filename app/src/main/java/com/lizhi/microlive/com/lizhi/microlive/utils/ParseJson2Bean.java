package com.lizhi.microlive.com.lizhi.microlive.utils;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class ParseJson2Bean<T> {

    public T parseJson2Object(String json) {
        Class<T> clazz = gettClass();

        T bean = JSON.parseObject(json, clazz);
        return bean;
    }

    public List<T> parseJson2List(String json) {
        // 通过反射获取T的真是类型
        Class<T> clazz = gettClass();

        List<T> bean = JSON.parseArray(json, clazz);
        return bean;
    }

    // 通过反射获取T的真是类型
    private Class<T> gettClass() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) pt.getActualTypeArguments()[0];
    }

}

