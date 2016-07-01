package com.sina.home.testfor_newclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.sina.home.testfor_newclient.CApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Walter on 2016/1/14.
 * 因为存在推送，所以这里得重写activity生命周期中的方法 调用推送的东西
 */
public abstract class BaseActivity extends FragmentActivity {
    private static final String TAG = "BaseActivity";
    //wdiget
    //data
    //interface
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        CApplication.getApp().addActivity(this);
        setContextView();
        findView();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        JPushInterface.stopPush(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CApplication.getApp().removeActivity(this);
        System.gc();//每个Activity 被销毁之后 都调用一下虚拟机的垃圾回收机制
    }

    protected <T extends View> T $(int id){
        return (T)super.findViewById(id);
    }

   abstract  protected void setContextView();

   abstract   protected void findView();

   abstract   protected void initView();
}
