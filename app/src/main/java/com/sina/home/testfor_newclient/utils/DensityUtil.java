package com.sina.home.testfor_newclient.utils;

import android.content.Context;

/**
 * Created by Walter on 2016/2/15.
 * 转换px2dp sp2dx
 */
public class DensityUtil {
    private static final String TAG = "DensityUtil";

    public static float dp2dx(Context context,final float dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dp*scale + 0.5f);
    }

    public static float dx2dp(Context context,final float dx){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dx / scale + 0.5f);
    }

    public static float sp2dx(Context context,final float sp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (sp * scale + 0.5f);
    }

    public static float dx2sp(Context context,final float dx){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dx / scale + 0.5f);
    }
}
