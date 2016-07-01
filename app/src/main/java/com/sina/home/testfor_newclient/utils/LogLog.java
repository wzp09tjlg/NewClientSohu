package com.sina.home.testfor_newclient.utils;

import android.util.Log;

/**
 * Created by Walter on 2016/1/27.
 * 一般在自己的应用的都需要使用写日志的习惯，方便跟踪和排错。但是在正式的环境中却是不需要写日志的，因此这里区分是否为调试模式来写日志
 * 1.只传消息
 * 2.传Tag和传消息
 */
public class LogLog {
    private static final String TAG = "LogLog";

    public static boolean DEBUG = true; //记得是什么模式下的日志

    //--------只传消息---------------------------

    public static void e(final String msg){
        if(DEBUG)
            Log.e(TAG,msg);
    }

    public static void i(final String msg){
        if(DEBUG)
            Log.i(TAG,msg);
    }

    public static void v(final String msg){
        if(DEBUG)
            Log.v(TAG,msg);
    }

    public static void d(String msg){
        if(DEBUG)
            Log.d(TAG,msg);
    }

    //-----传TAG和消息------------------------------------

    public static void e(final String TAG,String msg){
        if(!DEBUG)
            Log.e(TAG,msg);
    }

    public static void i(final String TAG,String msg){
      if(!DEBUG)
          Log.i(TAG,msg);
    }

    public static void v(final String TAG,String msg){
        if(!DEBUG)
            Log.v(TAG,msg);
    }

    public static void d(final String TAG,String msg){
        if(!DEBUG)
            Log.d(TAG,msg);
    }

    public static void w(final String TAG,String msg){
        if(!DEBUG)
            Log.w(TAG,msg);
    }


}
