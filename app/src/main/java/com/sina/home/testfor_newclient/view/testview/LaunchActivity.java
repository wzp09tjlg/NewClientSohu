package com.sina.home.testfor_newclient.view.testview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseActivity;
import com.sina.home.testfor_newclient.utils.GlobalConstant;

/**
 * Created by Walter on 2016/2/25.
 */
public class LaunchActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "LaunchActivity";
    //widget
    private Button mBtnCenter;
    private Button mBtnDown;
    //data
    private String stringValue;
    private byte byteValue;
    private short shortValue;
    private int intValue;
    private long longValue;
    private float floatValue;
    private double doubleValue;
    private char charValue;
    private boolean booleanValue;
    //interface
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.sina_btn_lunch:
              break;
          case R.id.sina_btn_lunch2:
              break;
      }
    }

    public static void launch(Context context){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(GlobalConstant.STRING,"string value string value");
        bundle.putChar(GlobalConstant.CHAR, 'a');
        bundle.putByte(GlobalConstant.BYTE, (byte) 12);
        bundle.putShort(GlobalConstant.SHORT, (short) 13123);
        bundle.putInt(GlobalConstant.INTGER, 10000);
        bundle.putLong(GlobalConstant.LONG, 12345l);
        bundle.putFloat(GlobalConstant.FLOAT, 123.34f);
        bundle.putDouble(GlobalConstant.DOUBLE, 12343.221);
        bundle.putBoolean(GlobalConstant.BOOLEAN, true);
        intent.putExtras(bundle);
        intent.setClass(context, LaunchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setContextView() {
     setContentView(R.layout.activity_luanch);
    }

    @Override
    protected void findView() {
        mBtnCenter = $(R.id.sina_btn_lunch);
        mBtnDown = $(R.id.sina_btn_lunch2);
    }

    @Override
    protected void initView() {
        getIntentData();
        mBtnCenter.setOnClickListener(this);
        mBtnDown.setOnClickListener(this);
    }

    private void getIntentData(){
        Bundle bundle = getIntent().getExtras();
        stringValue  = bundle.getString(GlobalConstant.STRING,"defualt");
        byteValue    = bundle.getByte(GlobalConstant.BYTE,(byte)0);
        shortValue   = bundle.getShort(GlobalConstant.SHORT,(short)0);
        intValue     = bundle.getInt(GlobalConstant.INTGER,0);
        longValue    = bundle.getLong(GlobalConstant.LONG,0l);
        floatValue   = bundle.getFloat(GlobalConstant.FLOAT,0.0f);
        doubleValue  = bundle.getDouble(GlobalConstant.DOUBLE,0.0);
        charValue    = bundle.getChar(GlobalConstant.CHAR,'0');
        booleanValue = bundle.getBoolean(GlobalConstant.BOOLEAN,false);
    }

    private void getData(){
        //前边是默认的在json串中获取相关的json块
        String json1 = "{\"total\":5,\"books\":[{\"recommend_intro\":\"推荐语\",\"author\":\"竹内一正\",\"sid\":\"\",\"title\":\"特斯拉之父\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2015\\/0312\\/70\\/8\\/21518621\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5346456\",\"recommend_name\":\"埃隆·马斯克:特斯拉之父\"},{\"recommend_intro\":\"推荐语\",\"author\":\"周浩晖\",\"sid\":\"\",\"title\":\"死亡通知单\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2015\\/0310\\/a6\\/2\\/86510730\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5346368\",\"recommend_name\":\"离奇的爆炸案:死亡通知单\"},{\"recommend_intro\":\"推荐语\",\"author\":\"爱喝水\",\"sid\":\"\",\"title\":\"一见你就笑\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2015\\/0311\\/1a\\/8\\/34518910\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5346397\",\"recommend_name\":\"遭遇男神反追求:一见你就笑\"}],\"title\":\"出版\",\"status\":{\"msg\":\"成功\",\"code\":0},\"index_type\":\"pub\",\"name\":\"出版\"}";
        String json2 = "{\"total\":4,\"books\":[{\"recommend_intro\":\"：顾一念说：“先生，结婚我请，敢不敢？”\",\"author\":\"顾小妖\",\"sid\":\"\",\"title\":\"闪婚蜜爱：纯禽老公悠着点\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2016\\/0106\\/31\\/1\\/92562576\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5350190\",\"recommend_name\":\"[宠婚]闪婚蜜爱:纯禽老公悠着点\"},{\"recommend_intro\":\"堂堂王妃全城宣告休夫，掀翻他的王府！\",\"author\":\"千苒君笑\",\"sid\":\"\",\"title\":\"凤还朝，妖孽王爷请让道\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2015\\/1223\\/f7\\/2\\/72559058\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5350880\",\"recommend_name\":\"[古言]凤还朝:妖孽王爷，请让道\"},{\"recommend_intro\":\"某天，穷小子开了辆限量版奔驰接老婆！\",\"author\":\"肥妈向善\",\"sid\":\"\",\"title\":\"大婚晚辰\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2013\\/0607\\/ce\\/e\\/65428104\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"236458\",\"recommend_name\":\"[婚恋]先婚后爱落魄男：大婚晚辰\"}],\"title\":\"女生爱看\",\"status\":{\"msg\":\"成功\",\"code\":0},\"index_type\":\"girl\",\"name\":\"女生\"}";
        String json3 = "{\"total\":5,\"books\":[{\"recommend_intro\":\"杀手之王化身小保镖，守护极品美女总裁\",\"author\":\"大红凶罩\",\"sid\":\"\",\"title\":\"我的美女总裁老婆\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2015\\/0616\\/10\\/5\\/60533269\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5348128\",\"recommend_name\":\"[职场]最风骚:我的美女总裁老婆\"},{\"recommend_intro\":\"亿万位面，千百种修炼法门，孰强孰弱？\",\"author\":\"书狂人\",\"sid\":\"\",\"title\":\"逆天武神\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2015\\/0702\\/5a\\/f\\/41543608\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5348333\",\"recommend_name\":\"[玄幻]少年微末中崛起:逆天武神\"},{\"recommend_intro\":\" 巅峰兵王归隐都市，为何会沦为小保镖？\",\"author\":\"歪歪王\",\"sid\":\"\",\"title\":\"我的刁蛮女神\",\"img\":\"http:\\/\\/r3.sinaimg.cn\\/22\\/2015\\/0203\\/2b\\/8\\/01516164\\/100x150x75x0.jpg\",\"src\":\"websina\",\"bid\":\"5345876\",\"recommend_name\":\"[兵王]兵王隐都市:我的刁蛮女神\"}],\"title\":\"男生爱看\",\"status\":{\"msg\":\"成功\",\"code\":0},\"index_type\":\"boy\",\"name\":\"男生\"}";

    }

}
