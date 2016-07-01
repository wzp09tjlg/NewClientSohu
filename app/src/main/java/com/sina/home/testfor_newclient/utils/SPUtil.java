package com.sina.home.testfor_newclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Walter on 2016/2/15.
 * 针对shereprefence的使用和控制
 * 1.暴露的方法有 put get clear contain remove
 */
public class SPUtil {
    public static final String FILE_NAME = "share_data";

    //保存数据
    public static void put(Context context,final String key,Object object){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(object instanceof String){
            editor.putString(key,(String)object);
        }else if(object instanceof Integer){
            editor.putInt(key,(Integer)object);
        }else if(object instanceof Long){
            editor.putLong(key,(Long)object);
        }else if(object instanceof Float){
            editor.putFloat(key,(Float)object);
        }else if(object instanceof  Boolean){
            editor.putBoolean(key,(Boolean)object);
        }else{
            editor.putString(key,object.toString());
        }
        editor.apply();
    }

    //获取数据
    public static Object get(Context context,final String key,Object defualtObject){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        if(defualtObject instanceof String){
            return sp.getString(key,(String)defualtObject);
        }else if(defualtObject instanceof Integer){
            return sp.getInt(key,(Integer)defualtObject);
        }else if(defualtObject instanceof Long){
            return sp.getLong(key,(Long)defualtObject);
        }else if(defualtObject instanceof Float){
            return sp.getFloat(key,(Float)defualtObject);
        }
        return null;
    }

    //包含
    public static boolean contain(Context context,final String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    //移除
    public static void remove(Context context,final String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    //清除
    public static void clear(Context context){
       SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
