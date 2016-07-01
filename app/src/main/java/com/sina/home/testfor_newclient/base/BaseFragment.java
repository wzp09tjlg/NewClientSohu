package com.sina.home.testfor_newclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Walter on 2016/1/14.
 * 具体实现 有两种:1.返回layout的id,重写getContentView(); 在使用LayoutInflate.inflate()返回View. 2.返回View,使用getLayoutView(),直接返回View.此时重写getContentView()返回小于0的值
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if(getContentView() > 0)
            view =  inflater.inflate(getContentView(),null);
        else
           view = getLayoutView();
        findView(view);
        initView();
        return view;
    }

    protected <T extends View> T $(View view,int id){
        return (T)view.findViewById(id);
    }

    abstract protected  int getContentView();

    abstract protected View getLayoutView();

    abstract protected void findView(View view);

    abstract protected void initView();
}
