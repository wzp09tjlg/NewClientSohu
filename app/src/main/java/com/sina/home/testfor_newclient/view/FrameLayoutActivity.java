package com.sina.home.testfor_newclient.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sina.home.testfor_newclient.CApplication;
import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseActivity;
import com.sina.home.testfor_newclient.base.BaseFragment;
import com.sina.home.testfor_newclient.bean.ChannelItem;
import com.sina.home.testfor_newclient.bean.ChannelManage;
import com.sina.home.testfor_newclient.bean.VideoManager;
import com.sina.home.testfor_newclient.dao.ChannelDao;
import com.sina.home.testfor_newclient.db.SQLHelper;
import com.sina.home.testfor_newclient.jpush.ExampleUtil;
import com.sina.home.testfor_newclient.listener.OnItemClickListener;
import com.sina.home.testfor_newclient.utils.GlobalConstant;
import com.sina.home.testfor_newclient.view.adapter.ContentViewPagerAdapter;
import com.sina.home.testfor_newclient.view.fragment.ItemContentType3Fragment;
import com.sina.home.testfor_newclient.view.testview.LaunchActivity;
import com.sina.home.testfor_newclient.widget.DragGrid.DragGrid;
import com.sina.home.testfor_newclient.widget.DragGrid.OtherGridView;
import com.sina.home.testfor_newclient.widget.DragGrid.adapter.DragAdapter;
import com.sina.home.testfor_newclient.widget.DragGrid.adapter.OtherAdapter;
import com.sina.home.testfor_newclient.widget.ProjectToast.LongToast;
import com.sina.home.testfor_newclient.widget.ProjectToast.ShortToast;
import com.sina.home.testfor_newclient.widget.TitleHorizontalScrollView.ItemTitleView;
import com.sina.home.testfor_newclient.widget.TitleHorizontalScrollView.TitleHoriontalScrollView;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Walter on 2016/1/7.
 * //这里使用的是framelayout 的布局
 * 然后存在嵌套的层
 */
public class FrameLayoutActivity extends BaseActivity implements
  View.OnClickListener,
  DragGrid.RemoveUserItem,
  AdapterView.OnItemClickListener,
  OnItemClickListener<ChannelItem>
{
    private static final String TAG = "FrameLayoutActivity";
    private static final int NEWS = 0;
    private static final int VIDEO = 1;
    private static final int ME = 2;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    //widget
    private FrameLayout parent;
    private FrameLayout title;
    private FrameLayout search;
    private FrameLayout section;
    private TitleHoriontalScrollView titleHoriontalScrollView;
    private LinearLayout items;
    private ViewPager mContentViewPager;
    private RelativeLayout mLayoutNew;
    private RelativeLayout mLayoutVideo;
    private RelativeLayout mLayoutMe;
    private ImageView mImgMenu;
    private ImageView mImgMenuCancel;
    private View viewSearch;
    private View viewSection;
    private View mViewShadowLeft;
    private View mViewShadowRight;
    private ImageView mImgTitleIndex;
    /** 用户栏目的GRIDVIEW */
    private DragGrid userGridView;
    /** 其它栏目的GRIDVIEW */
    private OtherGridView otherGridView;
    //data
    /** 用户栏目对应的适配器，可以拖动 */
    DragAdapter userAdapter;
    /** 其它栏目对应的适配器 */
    OtherAdapter otherAdapter;
    /** 其它栏目列表 */
    ArrayList<ChannelItem> otherChannelList = new ArrayList<>();
    /** 用户栏目列表 */
    ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    /** 影视栏目列表*/
    ArrayList<ChannelItem> videoChannelList = new ArrayList<>();
    /** 用户内容列表*/
    ArrayList<Fragment> userChannelFragmentList = new ArrayList<>();
    /** Video内容列表*/
    ArrayList<Fragment> videoChannelFragmentList = new ArrayList<>();
    /** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
    boolean isMove = false;
    private Context mContext = this;
    /** New中当前标题的标题位置*/
    private int mNewCurIndexPosition = 0;
    /** Video中当前标题的标题位置*/
    private int mVideoCurIndexPosition = 0;
    /** 选中的Subject的Index  0:新闻 1:视频 2:我的*/
    private int SelectedSubjectIndex = 0;
    /** News内容的adapter*/
    private ContentViewPagerAdapter newsContentAdapter;
    /** Video内容的adapter*/
    private ContentViewPagerAdapter videoContentAdapter;
    /** title点击处理类型,处理contentViewpager 的滑动事件 */
    private boolean isTitleItemClick = false;
    /** ShortToast 自定义的toast*/
    private ShortToast shortToast;
    /** LongToast 自定义的toast*/
    private LongToast longToast;
    /** 用户自定义关注页面显示状态*/
    private boolean isUserPageShowing = false;
    /** 用户点击back键的次数*/
    private int mBackKeyCount = 0;
    /** 推送的广播监听*/
    private MessageReceiver mMessageReceiver;
    /** 是否现在在最前*/
    public static boolean isForeground = false;
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        /** viewpager滑动之后 对title做滚动处理*/
        @Override
        public void onPageSelected(int position) {
          titleHoriontalScrollView.setPositionSelected(position);
          titleHoriontalScrollView.doAjustPosition(titleHoriontalScrollView.getCurrentItem(position),position,titleHoriontalScrollView.getClickedKeepType());
        }

        @Override
        public void onPageScrollStateChanged(int state) { // state 1 -ing 2 -ed 0 -nothing to do
        }
    };
    //interface
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.sina_layout_new:
             if(SelectedSubjectIndex == NEWS) return;
             initSubjectState(NEWS);
             SelectedSubjectIndex = 0;
             initNewsTitles();
             mContentViewPager.setAdapter(newsContentAdapter);
             mContentViewPager.setOnPageChangeListener(pageChangeListener);
             mContentViewPager.setCurrentItem(mNewCurIndexPosition);
             titleHoriontalScrollView.setPositionSelected(mNewCurIndexPosition);
             titleHoriontalScrollView.doAjustPosition(titleHoriontalScrollView.getCurrentItem(mNewCurIndexPosition),mNewCurIndexPosition,titleHoriontalScrollView.getClickedKeepType());
             break;
         case R.id.sina_layout_video:
             if(SelectedSubjectIndex == VIDEO) return;
             initSubjectState(VIDEO);
             SelectedSubjectIndex = 1;
             initVideoTitles();
             mContentViewPager.setAdapter(videoContentAdapter);
             mContentViewPager.setOnPageChangeListener(pageChangeListener);
             mContentViewPager.setCurrentItem(mVideoCurIndexPosition);
             titleHoriontalScrollView.setPositionSelected(mVideoCurIndexPosition);
             titleHoriontalScrollView.doAjustPosition(titleHoriontalScrollView.getCurrentItem(mVideoCurIndexPosition),mVideoCurIndexPosition,titleHoriontalScrollView.getClickedKeepType());
             break;
         case R.id.sina_layout_me: //取消自定义的toast
            /* if(SelectedSubjectIndex == ME) return;
             initSubjectState(ME);
             SelectedSubjectIndex = 2;
             Intent mIntent  = new Intent(FrameLayoutActivity.this, LaunchActivity.class);  //PopupWindowActivity FutureTaskActivity
             startActivity(mIntent);*/
             LaunchActivity.launch(this);
             break;
         case R.id.img_menu:
             search.setVisibility(View.VISIBLE);
             section.setVisibility(View.VISIBLE);
             title.setVisibility(View.GONE);
             items.setVisibility(View.GONE);

             viewSearch = LayoutInflater.from(this).inflate(R.layout.layout_layout_search,null);
             mImgMenuCancel = (ImageView)viewSearch.findViewById(R.id.img_menu_cancel);
             mImgMenuCancel.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     refreshTitleData();
                     title.setVisibility(View.VISIBLE);
                     items.setVisibility(View.VISIBLE);
                     if (viewSearch != null)
                         search.removeView(viewSearch);
                     if (viewSection != null)
                         section.removeView(viewSection);
                     search.setVisibility(View.INVISIBLE);
                     section.setVisibility(View.INVISIBLE);
                     isUserPageShowing = false;
                 }
             });
             isUserPageShowing = true;
             search.addView(viewSearch);
             search.invalidate();

             viewSection = LayoutInflater.from(this).inflate(R.layout.layout_layout_section,null);
             userGridView = (DragGrid) viewSection.findViewById(R.id.userGridView);
             otherGridView = (OtherGridView) viewSection.findViewById(R.id.otherGridView);

             userGridView.setAdapter(userAdapter);
             userGridView.setRemoveUserItem(this);
             otherGridView.setAdapter(this.otherAdapter);
             otherGridView.setOnItemClickListener(this);

             section.addView(viewSection);
             section.invalidate();

             parent.invalidate();//重画 刷新一下
             break;
     }
    }

    /** 用户选择页item处理  */
    @Override
    public void doRemoveUserItem(AdapterView<?> parent, View view, final int position) {
        if(isMove){
            return;
        }
        boolean changed = false;
        if(position == mNewCurIndexPosition){
            mNewCurIndexPosition = 0;
            changed = true;
        }

        //删除user 数据
        if (position != 0 && position != 1) {
            final ImageView moveImageView = getView(view);
            if (moveImageView != null) {
                TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                final int[] startLocation = new int[2];
                newTextView.getLocationInWindow(startLocation);
                final ChannelItem channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                otherAdapter.setVisible(false);
                //添加到最后一个
                otherAdapter.addItem(channel);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        try {
                            int[] endLocation = new int[2];
                            //获取终点的坐标
                            otherGridView.getChildAt(otherGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
                            MoveAnim(moveImageView, startLocation , endLocation, channel,userGridView);
                            userAdapter.setRemove(position);
                        } catch (Exception localException) {
                        }
                    }
                }, 50L);
            }
            if(changed){
                refreshTitleData();
            }
        }
    }

    /** 用户未选择页点击处理 */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if(isMove){  //正在滑动的情况 就不能处理点击事件，避免频繁操作的空指针问题
            return;
        }
        final ImageView moveImageView = getView(view);
        if (moveImageView != null){
            TextView newTextView = (TextView) view.findViewById(R.id.text_item);
            final int[] startLocation = new int[2];
            newTextView.getLocationInWindow(startLocation);
            final ChannelItem channel = ((OtherAdapter) parent.getAdapter()).getItem(position);
            userAdapter.setVisible(false);
            //添加到最后一个
            userAdapter.addItem(channel);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    try {
                        int[] endLocation = new int[2];
                        //获取终点的坐标
                        userGridView.getChildAt(userGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
                        MoveAnim(moveImageView, startLocation , endLocation, channel,otherGridView);
                        otherAdapter.setRemove(position);
                    } catch (Exception localException) {
                    }
                }
            }, 50L);
        }
    }

    /** 针对标题栏的点击事件的监听 */
    @Override
    public void onItemClickListener(View view, int position, ChannelItem channelItem) {
        if(mNewCurIndexPosition == position) return;
        if(SelectedSubjectIndex == NEWS){
            mNewCurIndexPosition = position;
            titleHoriontalScrollView.setPositionSelected(position);
            titleHoriontalScrollView.doAjustPosition(view, position, titleHoriontalScrollView.getClickedKeepType()); //调整item的位置
            isTitleItemClick = true;
            mContentViewPager.setCurrentItem(position); //同步content的内容页
        }else if(SelectedSubjectIndex == VIDEO){
            mVideoCurIndexPosition = position;
            titleHoriontalScrollView.setPositionSelected(position);
            titleHoriontalScrollView.doAjustPosition(view, position, titleHoriontalScrollView.getClickedKeepType()); //调整item的位置
            isTitleItemClick = true;
            mContentViewPager.setCurrentItem(position); //同步content的内容页
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMessageReceiver);
    }

    @Override
    protected void setContextView() {
        setContentView(R.layout.activity_framelayout);
    }

    @Override
    protected void findView() {
        parent = $(R.id.layout_parent);

        title  = $(R.id.layout_titles);
        titleHoriontalScrollView = $(R.id.hscroller_title);
        mContentViewPager = $(R.id.viewpager_content);
        search = $(R.id.layout_search);
        section = $(R.id.layout_section);
        items = $(R.id.layout_items);
        mLayoutNew  = $(R.id.sina_layout_new);
        mLayoutVideo = $(R.id.sina_layout_video);
        mLayoutMe = $(R.id.sina_layout_me);

        mImgMenu = $(R.id.img_menu);

        mViewShadowLeft = $(R.id.title_shade_left);
        mViewShadowRight = $(R.id.title_shade_right);

        mImgTitleIndex = $(R.id.img_title_index);
    }

    @Override
    protected void initView() {
        getData();
        initNewsTitles();
        initSubjectState(-1);
        initPush();
        mLayoutNew.setOnClickListener(this);
        mLayoutVideo.setOnClickListener(this);
        mLayoutMe.setOnClickListener(this);

        mImgMenu.setOnClickListener(this);

        search.setVisibility(View.INVISIBLE);
        section.setVisibility(View.INVISIBLE);

        userAdapter = new DragAdapter(mContext, userChannelList);
        otherAdapter = new OtherAdapter(this, otherChannelList);

        newsContentAdapter = new ContentViewPagerAdapter(getSupportFragmentManager(),this,userChannelFragmentList);
        mContentViewPager.setAdapter(newsContentAdapter);
        mContentViewPager.setOnPageChangeListener(pageChangeListener);
        videoContentAdapter = new ContentViewPagerAdapter(getSupportFragmentManager(),this,videoChannelFragmentList);
    }

    /** 获取数据*/
    private void getData(){
        getListData();
        initNewsContent();
        initVideoContent();
    }

    /**  获取列表title数据*/
    private void getListData(){
        try {  //获取title的数据
            userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(CApplication.getApp().getSQLHelper()).getUserChannel());
            otherChannelList = ((ArrayList<ChannelItem>)ChannelManage.getManage(CApplication.getApp().getSQLHelper()).getOtherChannel());

            videoChannelList = (ArrayList<ChannelItem>)VideoManager.getManage(CApplication.getApp().getSQLHelper()).getVideoChannel();
        }catch (Exception e){
        }
    }

    /** 初始化News的title*/
    private void initNewsTitles(){
        titleHoriontalScrollView.getLayoutTitles().removeAllViews();
        for(int i=0;i<userChannelList.size();i++){
            ItemTitleView item = new ItemTitleView(this);
            item.setTextTitle(userChannelList.get(i).getName());
            item.setChannelItem(userChannelList.get(i));
            if(i==0)
                item.setTextSelected(true);
            else
                item.setTextSelected(false);
            item.setmOnItemClickListener(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            titleHoriontalScrollView.getLayoutTitles().addView(item,params);
        }
    }

    /** 初始化Video的title信息*/
    private void initVideoTitles(){
        titleHoriontalScrollView.getLayoutTitles().removeAllViews();
        for(int i=0;i<videoChannelList.size();i++){
            ItemTitleView item = new ItemTitleView(this);
            item.setTextTitle(videoChannelList.get(i).getName());
            item.setChannelItem(videoChannelList.get(i));
            if(i==0)
                item.setTextSelected(true);
            else
                item.setTextSelected(false);
            item.setmOnItemClickListener(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            titleHoriontalScrollView.getLayoutTitles().addView(item,params);
        }
    }

    private void initPush(){
        JPushInterface.init(getApplicationContext());
//        mMessageReceiver = new MessageReceiver();
        registerMessageReceiver();
    }

    /** 初始化News的ViewPager的content*/
    private void initNewsContent(){
        //获取内容的数据
        for(int i = 0 ;i<userChannelList.size();i++){
            Bundle bundle = new Bundle();
            bundle.putInt(GlobalConstant.SUBJECT_ID,userChannelList.get(i).getId());
            bundle.putBoolean(GlobalConstant.ISAUTOREFRESH,true);
            BaseFragment fragment = null;
//             if(i % 2 == 0)
//                 fragment = ItemContentType1Fragment.getInstance(bundle);
//             else
            fragment = ItemContentType3Fragment.getInstance(bundle);
            userChannelFragmentList.add(fragment);
        }
    }
    /** 初始化Video的ViewPager的content*/
    private void initVideoContent(){
        //获取内容的数据
        for(int i = 0 ;i<videoChannelList.size();i++){
            Bundle bundle = new Bundle();
            bundle.putInt(GlobalConstant.SUBJECT_ID,videoChannelList.get(i).getId());
            bundle.putBoolean(GlobalConstant.ISAUTOREFRESH,true);
            BaseFragment fragment = null;
//             if(i % 2 == 0)
//                 fragment = ItemContentType1Fragment.getInstance(bundle);
//             else
            fragment = ItemContentType3Fragment.getInstance(bundle);
            videoChannelFragmentList.add(fragment);
        }
    }

    /** 初始化Subject的状态*/
    private void initSubjectState(int index){
        switch (index){
            case NEWS:
                mLayoutVideo.setSelected(false);
                mLayoutMe.setSelected(false);
                mLayoutNew.setSelected(true);
                break;
            case VIDEO:
                mLayoutMe.setSelected(false);
                mLayoutNew.setSelected(false);
                mLayoutVideo.setSelected(true);
                break;
            case ME:
                mLayoutVideo.setSelected(false);
                mLayoutNew.setSelected(false);
                mLayoutMe.setSelected(true);
                break;
            default:
                mLayoutVideo.setSelected(false);
                mLayoutMe.setSelected(false);
                mLayoutNew.setSelected(true);
                break;
        }

    }

    /**
     * 获取点击的Item的对应View，
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
    }

    /**
     * 点击ITEM移动动画
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     * @param clickGridView
     */
    private void MoveAnim(View moveView, int[] startLocation,int[] endLocation, final ChannelItem moveChannel,
                          final GridView clickGridView) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);//动画时间
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                // instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
                if (clickGridView instanceof DragGrid) {
                    otherAdapter.setVisible(true);
                    otherAdapter.notifyDataSetChanged();
                    userAdapter.remove();
                }else{
                    userAdapter.setVisible(true);
                    userAdapter.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            otherAdapter.remove();  //如果这里的移动的速度非常快的法  就会出现OthereGrid 中删除Item出现异常
                        }
                    },50l);
                }
                isMove = false;
            }
        });
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    private ViewGroup getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(this);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }

    /** 退出时候保存选择后数据库的设置  */
    private void saveChannel() {
        try{
            //使用dao方式操作数据 清除数据
            //使用dao来写数据
            //使用dao来写数据
            ChannelDao channelDao = new ChannelDao(this);
            channelDao.clearFeedTable(SQLHelper.TABLE_CHANNEL);
            channelDao.addCache(userAdapter.getChannnelLst(),SQLHelper.TABLE_CHANNEL);
            channelDao.addCache(otherAdapter.getChannnelLst(),SQLHelper.TABLE_CHANNEL);
        }catch (Exception e){}
    }

    @Override
    public void onBackPressed() {

        //处理是在 处理添加用户关注页 按返回键，还是在主页时按返回键
        if(isUserPageShowing){  //在用户选择关注页面 按back键
            saveChannel();
            getListData();
            refreshTitleData();
            isUserPageShowing = false;
            mBackKeyCount = 0;
            mImgMenuCancel.callOnClick(); //这里不能再调用super.OnBackPressed();因为会存在循环调用，所以表现就是直接退出。替代方案是调用menuCancel的点击事件
        }else{ //在页面上按back键
            if(SelectedSubjectIndex != NEWS){  //如果当前的SubjectIndex 不是News 那么就先回到New之后在操作退出
                SelectedSubjectIndex = NEWS;
                initSubjectState(NEWS);
                mContentViewPager.setAdapter(newsContentAdapter);
                mContentViewPager.setOnPageChangeListener(pageChangeListener);
                mContentViewPager.setCurrentItem(mNewCurIndexPosition);
                titleHoriontalScrollView.setPositionSelected(mNewCurIndexPosition);
                titleHoriontalScrollView.doAjustPosition(titleHoriontalScrollView.getCurrentItem(mNewCurIndexPosition),mNewCurIndexPosition,titleHoriontalScrollView.getClickedKeepType());
                return;
            }

           if(mNewCurIndexPosition == 0){
               if(shortToast == null || !shortToast.isShowing()){
                   shortToast = ShortToast.makeText(this,getResources().getString(R.string.second_back_press_exit),2000);
                   shortToast.setGravity(Gravity.TOP, 0, 0);
                   shortToast.show();
                   mBackKeyCount = 1;
               }else{
                  mBackKeyCount += 1;
                  if(shortToast != null && shortToast.isShowing() && mBackKeyCount == 2) {
                      super.onBackPressed();
                      shortToast.cancelShow();
                  }
                   mBackKeyCount = 0;
               }
           }else{
                titleHoriontalScrollView.setPositionSelected(0);
                mContentViewPager.setCurrentItem(0);
               mNewCurIndexPosition = 0;
            }
        }
    }

    private void refreshTitleData(){
        if(SelectedSubjectIndex == NEWS){
            initNewsTitles();
            initNewsContent();
            newsContentAdapter.notifyDataSetChanged();
            mContentViewPager.setCurrentItem(mNewCurIndexPosition);
        }else if(SelectedSubjectIndex == VIDEO){
            initVideoTitles();
            initVideoContent();
            videoContentAdapter.notifyDataSetChanged();
            mContentViewPager.setCurrentItem(mVideoCurIndexPosition);
        }
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
            }
        }
    }
}
