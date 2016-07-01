package com.sina.home.testfor_newclient.view.testview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseActivity;
import com.sina.home.testfor_newclient.utils.AppUtil;
import com.sina.home.testfor_newclient.utils.HttpUtil;
import com.sina.home.testfor_newclient.utils.KeyBoardUtil;
import com.sina.home.testfor_newclient.utils.LogLog;
import com.sina.home.testfor_newclient.utils.NetUtils;
import com.sina.home.testfor_newclient.utils.SDCardUtil;
import com.sina.home.testfor_newclient.utils.ScreenUtil;

/**
 * Created by Walter on 2016/2/14.
 */
public class CardViewActivity extends BaseActivity  implements
        View.OnClickListener,
        HttpUtil.CallBack
{
    private static final String TAG = "CardViewActivity";
    private static final int MSG_DOWNLOAD = 0X10010;
    //widget
    private CardView mCardView;
    private ImageView mImg;
    private TextView mText;
    private SeekBar mSeekBarCorner;
    private SeekBar mSeekBarRadius;

    //几个测试类
    private Button mBtnApp;
    private Button mBtnSensity;
    private Button mBtnHttp;
    private Button mBtnKeyboard;
    private Button mBtnLog;
    private Button mBtnNet;
    private Button mBtnScreen;
    private Button mBtnSdcard;
    private Button mBtnSp;

    private ImageView mImgHttp;
    private EditText mEdt;
    //data
    private Context mContext = this;
    private int count = 0;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_DOWNLOAD:
                 Bitmap bitmap = (Bitmap)msg.obj;
                    mImgHttp.setImageBitmap(bitmap);
                    mImgHttp.invalidate();
                    break;
            }
        }
    };
    //interface
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.sina_btn_appUtil:
             String appName = "";
             appName =  AppUtil.getAppName(this);

             String appVersionName = "";
             appVersionName = AppUtil.getCurAppVersionName(this);

             LogLog.i(TAG,"appName:" + appName + "  appVersionName:" + appVersionName);
             break;
         case R.id.sina_btn_densityUtil:
             //这里是进行dp dx sp 之间的转换
             break;
         case R.id.sina_btn_httpUtil:
             //网络下载数据  "http://avatar.csdn.net/C/6/8/1_bz419927089.jpg";//
             final String URL1 = "http://www.itotii.com/wp-content/uploads/2016/02/04/cute-japanese-girl-mirai-chan-kotori-kawashima-12.jpg";
             final String URL2 ="http://avatar.csdn.net/C/6/8/1_bz419927089.jpg" ;//"http://www.itotii.com/wp-content/uploads/2016/02/04/cute-japanese-girl-mirai-chan-kotori-kawashima-15.jpg";
            try{
                //get
                LogLog.e("---------------abcd");
                HttpUtil.doGetAsyn(URL1, this);

                //post
//                HttpUtil.doPostAsyn(URL2,"",this);
                LogLog.e("---------------defc");
            }catch (Exception e){}
             break;
         case R.id.sina_btn_keyboardUtil:
            if(count++ % 2 == 0)
                KeyBoardUtil.openKeyBoard(mEdt,this);
             else
                KeyBoardUtil.closeKeyBoard(mEdt,this);
             break;
         case R.id.sina_btn_LogUtil:
             //已经在使用LogLog的方式 记录日志，是debug模式还是正式的模式
             break;
         case R.id.sina_btn_NetUtil:
             boolean isConnect = NetUtils.isConntected(this);
             boolean isWifi =  NetUtils.isWifi(this);
             LogLog.e("isConntect :" + isConnect + "    isWifi:" + isWifi );
             NetUtils.openNetSetting(CardViewActivity.this);
             break;
         case R.id.sina_btn_ScreenUtil:
             int height = ScreenUtil.getScreenHeight(this);
             int  width = ScreenUtil.getScreenWidth(this);
             int heightBar =  ScreenUtil.getStateBarHeight(this);

             LogLog.e("Screen Width :" + width + "  height:" + height + "   barHeight:" + heightBar);
             if(count++ % 2 ==0)
                 onRequestComplete(ScreenUtil.snapShotWithStatusBar(CardViewActivity.this));
             else
                 onRequestComplete(ScreenUtil.snapShotWithoutStatusBar(CardViewActivity.this));
             break;
         case R.id.sina_btn_SDCardUtil:
           boolean  isEnable = SDCardUtil.isSDCardEnable();
           String rootPath = SDCardUtil.getRootPath() ;
           long allSize =  SDCardUtil.getSDCardAllSize();
             String cardPath = SDCardUtil.getSDCardPath();
             LogLog.e("isEnable:" + isEnable + "  rootPth:" + rootPath + "  allSize:" + allSize + "  cardPath:" + cardPath);
             break;
         case R.id.sina_btn_SPUtil:

             break;
     }
    }

    @Override
    public void onRequestComplete(final Bitmap result) {
        if(null != result){
            //方式一 使用handler + message 来处理UI的更新
//            Message msg = Message.obtain();
//            msg.what =  MSG_DOWNLOAD;
//            mHandler.sendMessage(msg);

//            msg.obj = result;
            //方式二 使用view.post的方式来处理UI的更新
            mImgHttp.post(new Runnable() {
                @Override
                public void run() {
                    mImgHttp.setImageBitmap(result);
                }
            });
    }
    }

    @Override
    protected void setContextView() {
       setContentView(R.layout.activity_cardview);
    }

    @Override
    protected void findView() {
        mCardView = $(R.id.sina_cardview);
        mImg = $(R.id.sina_img_cardview);
        mText = $(R.id.sina_text_cardview);
        mSeekBarCorner = $(R.id.sina_seek_corner);
        mSeekBarRadius = $(R.id.sina_seek_radius);

         mBtnApp = $(R.id.sina_btn_appUtil);
         mBtnSensity = $(R.id.sina_btn_densityUtil);
         mBtnHttp = $(R.id.sina_btn_httpUtil);
         mBtnKeyboard = $(R.id.sina_btn_keyboardUtil);
        mBtnKeyboard.requestFocus();
         mBtnLog = $(R.id.sina_btn_LogUtil);
         mBtnNet = $(R.id.sina_btn_NetUtil);
         mBtnScreen = $(R.id.sina_btn_ScreenUtil);
         mBtnSdcard = $(R.id.sina_btn_SDCardUtil);
         mBtnSp = $(R.id.sina_btn_SPUtil);

        mImgHttp = $(R.id.sina_img_http);
        mEdt = $(R.id.sina_edt_keyboard);
     }

    @Override
    protected void initView() {
        mSeekBarCorner.setMax(100);
        mSeekBarCorner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCardView.setCardElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBarRadius.setMax(100);
        mSeekBarRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCardView.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBtnApp.setOnClickListener(this);
        mBtnSensity.setOnClickListener(this);
        mBtnHttp.setOnClickListener(this);
        mBtnKeyboard.setOnClickListener(this);
        mBtnLog.setOnClickListener(this);
        mBtnNet.setOnClickListener(this);
        mBtnScreen.setOnClickListener(this);
        mBtnSdcard.setOnClickListener(this);
        mBtnSp.setOnClickListener(this);
    }
}
