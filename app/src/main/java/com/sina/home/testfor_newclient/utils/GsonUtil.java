package com.sina.home.testfor_newclient.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by Walter on 2016/1/27.
 * 关于使用Gson 进行数据的转换 (基本的两种 转换toGson 和 fromGson方法)
 */
public class GsonUtil {
    private static final String TAG = "GsonUtil";

    private static final Gson mGson = new Gson();

    /** 转换成json串*/
    public static String toGson(Object obj){
        if(null == obj)
            return "";
        return mGson.toJson(obj);
    }

    /** 转换成 T对象*/
    public static <T>  T fromGson(String gson,Class<T> clazz){
        if(TextUtils.isEmpty(gson)) return null;
        return mGson.fromJson(gson,clazz);
    }
}
