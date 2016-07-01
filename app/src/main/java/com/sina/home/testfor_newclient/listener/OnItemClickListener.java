package com.sina.home.testfor_newclient.listener;

import android.view.View;

/**
 * Created by Walter on 2016/1/11.
 */
public interface OnItemClickListener<T> {
    void onItemClickListener(View view,int position,T t);
}
