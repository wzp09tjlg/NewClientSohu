package com.sina.home.testfor_newclient.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Wuzp on 2016/7/1.
 */
public class SplashViewAdapter extends FragmentPagerAdapter {
    private static final String TAG = SplashViewAdapter.class.getSimpleName();
    private List<Fragment> mData;
    private Context mContext;

    public SplashViewAdapter(FragmentManager manager,Context context,List<Fragment> data){
        super(manager);
        mContext = context;
        mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }
}
