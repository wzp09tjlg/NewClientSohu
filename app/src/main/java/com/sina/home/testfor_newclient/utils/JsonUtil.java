package com.sina.home.testfor_newclient.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

/**
 * Created by Walter on 2016/1/27.
 * 管理json转换数据的处理
 */
public class JsonUtil {
   private static final String TAG = "JsonUtil";

    /** 获取json串 */
   public static String toJson(Object obj){
       if(null == obj) return "";
       return JSON.toJSONString(obj);
   }

   /** 转换成对象*/
   public static <T> T fromJson(String json,Class<T> clazz){
       if(TextUtils.isEmpty(json)) return null;
       return (T)JSON.parse(json);
   }
}
