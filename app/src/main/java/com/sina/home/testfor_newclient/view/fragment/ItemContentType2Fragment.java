package com.sina.home.testfor_newclient.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseFragment;

/**
 * Created by Walter on 2016/1/14.
 */
public class ItemContentType2Fragment extends BaseFragment {
    private static final String TAG = "ItemContentType2Fragment";
    //widget
    private ImageView mImg;
    private ListView mListView;
    //data
    private int[] icons = {R.drawable.icon_cat1,R.drawable.icon_cat2,R.drawable.icon_cat3,R.drawable.icon_cat4,R.drawable.icon_cat5};
    //interface
    public static  ItemContentType2Fragment getInstance(Bundle bundle){
        ItemContentType2Fragment fragment = new ItemContentType2Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.item_content_type2;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void findView(View view) {
        mImg = $(view,R.id.img_item_content_type2);
        mListView = $(view,R.id.lv_item_content_type2);
    }

    @Override
    protected void initView() {
        int random = (int)(Math.random()*10) % 5;
       mImg.setImageDrawable(getActivity().getResources().getDrawable(icons[random]));
    }
}
