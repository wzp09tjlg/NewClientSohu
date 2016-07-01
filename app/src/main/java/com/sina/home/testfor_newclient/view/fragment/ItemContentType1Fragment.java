package com.sina.home.testfor_newclient.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseFragment;

/**
 * Created by Walter on 2016/1/14.
 */
public class ItemContentType1Fragment extends BaseFragment {
    private  static final String TAG = "";
    //widget
    private TextView mText;
    private ListView mListView;
    //data
    private Bundle bundle;
    //interface
    public static  ItemContentType1Fragment getInstance(Bundle bundle){
       ItemContentType1Fragment fragment = new ItemContentType1Fragment();
       fragment.setArguments(bundle);
       return fragment;
   }

    @Override
    protected int getContentView() {
        return R.layout.item_content_type1;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void findView(View view) {
        mText = $(view,R.id.text_item_content_type1);
        mListView = $(view,R.id.lv_item_content_type1);
    }

    @Override
    protected void initView() {
        bundle = getArguments();
    }
}
