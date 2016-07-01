package com.sina.home.testfor_newclient.jpush;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sina.home.testfor_newclient.base.BaseActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Walter on 2016/1/25.
 */
public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    //widget
    //data
    //interface
    @Override
    protected void setContextView() {
        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            tv.setText("Title : " + title + "  " + "Content : " + content);
        }
        addContentView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initView() {

    }
}
