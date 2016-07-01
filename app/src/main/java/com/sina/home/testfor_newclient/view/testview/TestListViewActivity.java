package com.sina.home.testfor_newclient.view.testview;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walter on 2016/2/16.
 */
public class TestListViewActivity extends BaseActivity {
    private static final String TAG = "TestListViewActivity";
    //widget
    private ListView  mListView;
    //data
    private List<String> data;
    //interface

    @Override
    protected void setContextView() {
      setContentView(R.layout.activity_testlistview);
    }

    @Override
    protected void findView() {
        mListView = (ListView)findViewById(R.id.sina_test_lv);
    }

    @Override
    protected void initView() {
        data = new ArrayList<>();
        for(int i=0;i<1000;i++){
           data.add("abddesdsakfdkslafksdlafksda " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(adapter);
    }


}
