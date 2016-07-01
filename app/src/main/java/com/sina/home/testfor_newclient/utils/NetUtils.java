package com.sina.home.testfor_newclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Walter on 2016/2/15.
 * 网络的工具类
 * 1.网络时否连接
 * 2.网络是否为wifi
 * 3.打开网络设置界面
 */
public class NetUtils {
   private static final String TAG = "NetUtils";

   //判断网络是否连接
    public static boolean isConntected(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity)
        {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected())
            {
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

    //判断是否是wifi连接
    public static boolean isWifi(Context context){
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    //打开网络设置界面
    public static void openNetSetting(Activity activity){
        //网上当的这种方式 但是实际上不能打开设置页面，报没有在清单文件中声明activity的错
//        Intent intent = new Intent("/");
//        ComponentName cm = new ComponentName("com.android.settings",
//                "com.android.settings.WirelessSettings");
//        intent.setComponent(cm);
//        intent.setAction("android.intent.action.VIEW");
//        activity.startActivityForResult(intent, 0);

          //这两种方式是可行的，都能打开设置界面
        activity.startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
//        activity.startActivityForResult(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS), 0);
    }
}
