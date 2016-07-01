package com.sina.home.testfor_newclient.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Wuzp on 2016/7/1.
 */
public class NewViewPagerAdapter extends PagerAdapter {
    private static final String TAG = NewViewPagerAdapter.class.getSimpleName();

    private List<View> mData;
    private Context mContext;

    public NewViewPagerAdapter(Context context,List<View> data){
        mData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mData.get(position));
        return mData.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mData.get(position));
    }
}
