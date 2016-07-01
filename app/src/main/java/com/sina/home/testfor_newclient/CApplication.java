package com.sina.home.testfor_newclient;

import android.app.Application;

import com.sina.home.testfor_newclient.base.BaseActivity;
import com.sina.home.testfor_newclient.db.SQLHelper;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Walter on 2016/1/7.
 */
public class CApplication extends Application {
    private static CApplication mApplication;
    private SQLHelper sqlHelper;
    private ArrayList<BaseActivity> mListActivity;  //看过其他的方式使用stack 来保存相应的activity，

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mListActivity = new ArrayList<>();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    /** 获取Application */
    public static CApplication getApp() {
        return mApplication;
    }

    /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mApplication);
        return sqlHelper;
    }

    /** 摧毁应用进程时候调用 */
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
    }

    public void addActivity(BaseActivity activity){
        if(mListActivity == null){
            mListActivity = new ArrayList<>();
        }
        mListActivity.add(activity);
    }

    public void removeActivity(BaseActivity activity){
        if(mListActivity.size() > 0 && activity != null)
            mListActivity.remove(activity);
    }

    public void finishAllActivity(){
        for(BaseActivity activity:mListActivity){
           if(activity != null)
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void clearAppCache() {
    }
}
