package com.sina.home.testfor_newclient.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by Walter on 2016/1/14.
 */
public class ContentViewPagerAdapter extends FragmentStatePagerAdapter { //PagerAdapter  FragmentPagerAdapter FragmentStatePagerAdapter 针对内容是fragment 这里使用后两种adapter，但是后两种adapter在加载数据有差异。使用时得注意
    private static final String TAG = "ContentViewPagerAdapter";
    private ArrayList<Fragment> mListFragment;
    private Context mContext;
    //使用fragementadapter 需要传递FragmentManager参数
    public ContentViewPagerAdapter(FragmentManager manager,Context context,ArrayList<Fragment> list){
        super(manager);
        mContext = context;
        mListFragment = list;
    }

//    @Override //这里得注意 看父类是什么adapter 这里相应的重写什么方法
//    public Object instantiateItem(ViewGroup container, int position) {
//        ((ViewPager)container).addView(mListFragment.get(position));
//        return super.instantiateItem(container, position);
//    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment == null ? 0 : mListFragment.size();
    }

//    @Override //这里得注意 看父类是什么adapter 这里相应的重写什么方法
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//    }

    @Override //这个方法是 处理动态添加和删除fragment 是否真的将内存中保存的数据处理掉。否则会出现调用删除的动作 但是布局会重叠的情况
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
