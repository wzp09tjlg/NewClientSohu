package com.sina.home.testfor_newclient.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by Walter on 2016/2/15.
 * 这是SDCard的工具类
 * 1.sdcard是否可用
 * 2.sdcard的路径
 * 3.sdcard的剩余可用容量字节数
 * 4.获取指定路径下的剩余容量
 * 5.获取系统存储路径
 */
public class SDCardUtil {
    private static final String TAG = "SDCardUtil";

    //不允许初始化
    private SDCardUtil(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //sdcard是否可用
    public static boolean isSDCardEnable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //检查sdcar是否已经挂载
    }
    //sd卡路径
    public static String getSDCardPath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    //获取sdcard的剩余可用容量字节数
    public static long getSDCardAllSize(){
        if (isSDCardEnable())
        {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    //获取指定路径下的剩余容量
    public static long getFreeBytes(String filePath){
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath()))
        {
            filePath = getSDCardPath();
        } else
        {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    //获取系统存储路径
    public static String getRootPath(){
        return Environment.getRootDirectory().getAbsolutePath();
    }

}
