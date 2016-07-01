package com.sina.home.testfor_newclient.jpush;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.bean.GsonBean.Men;
import com.sina.home.testfor_newclient.bean.GsonBean.Person;
import com.sina.home.testfor_newclient.bean.GsonBean.School;
import com.sina.home.testfor_newclient.bean.GsonBean.Student;
import com.sina.home.testfor_newclient.bean.GsonBean.Women;
import com.sina.home.testfor_newclient.utils.LogLog;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends Activity implements View.OnClickListener
{
    private static final String TAG = "MainActivity";
    //widget
    private Button mInit;
    private Button mSetting;
    private Button mStopPush;
    private Button mResumePush;
    private EditText msgText;

    private Button mBtnGsonTo;
    private Button mBtnGsonFrom;
    private Button mBtnJsonTo;
    private Button mBtnJsonFrom;

    //data
    public static boolean isForeground = false;
    private String GsonTo = "";
    private String GsonFrom = "";
    private String JsonTo = "";
    private String JsonFrom = "";
    private String fromString;
    private String ToString;
    private Gson gson = new Gson();
    private JSON json = new JSONObject();
    private Person mPerson;
    private Women mWomen;
    private Men mMen;
    private Student mStudent;
    private School mSchool;
    //interface
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.init:
                init();
                break;
            case R.id.setting:
                Intent intent = new Intent(MainActivity.this, PushSetActivity.class);
                startActivity(intent);
                break;
            case R.id.stopPush:
                JPushInterface.stopPush(getApplicationContext());
                break;
            case R.id.resumePush:
                JPushInterface.resumePush(getApplicationContext());
                break;
            case R.id.sina_btn_gson_from:
            //Person
                fromString = "{\"name\":\"zhangsan\",\"sex\":\"Men\",age:20,\"desc\":\"a men,who is a student.come from BeiJing\"}";
            //Men
                //fromString = "Person[\'name\':\'zhangsan\',\'sex\':\'Men\',age:20,\'desc\':\'a men,who is a student.come from BeiJing\']";
            //Women
                //fromString = "Person[\'name\':\'zhangsan\',\'sex\':\'Men\',age:20,\'desc\':\'a men,who is a student.come from BeiJing\']";
            //Student
                //fromString = "Person[\'name\':\'zhangsan\',\'sex\':\'Men\',age:20,\'desc\':\'a men,who is a student.come from BeiJing\']";
            //School
                //fromString = "Person[\'name\':\'zhangsan\',\'sex\':\'Men\',age:20,\'desc\':\'a men,who is a student.come from BeiJing\']";
                GsonFrom = fromString;
                mPerson =  gson.fromJson(GsonFrom, Person.class);
                LogLog.i(TAG,"PERSON: from Gson " + mPerson);
                break;
            case R.id.sina_btn_gson_to:
            //Person
                mPerson = null;
                mPerson = new Person();
                mPerson.setName("zhangsan");
                mPerson.setAge(20);
                mPerson.setSex("Men");
                mPerson.addType("Son");
                mPerson.addType("Father");
                mPerson.addType("GrandFather");
                GsonTo = gson.toJson(mPerson);
            //Men
            //Women
            //Student
            //School
                LogLog.i(TAG,"PERSON: to Gson:" + GsonTo);
                break;
            case R.id.sina_btn_Json_from:
                //Person
                //if(null == mPerson ) return;
                //JsonTo = JSON.toJSON(mPerson).toString();  //这是获得的JSONObject 所以这里使用toString方式获取string数据
                //LogLog.i(TAG,"JsonTo:" + JsonTo);
                //Men
                //Women
                //Student
                //School
                mSchool = new School();
                mSchool.setName("beijingdaxue");
                mSchool.setAge(120);
                mSchool.setAddres("beijingshi haidianqu wudaokou momoqu momojie momodao momohao");

                mPerson = new Person("zhangsan","men",20,new String[]{"son","father","gradfather","chairman","manager","clerk"});
                mStudent = new Student(mPerson);
                mStudent.setAcademy("computer");
                mStudent.setMajor("computer");
                mStudent.setScore(100.0f);
                mSchool.addStudent(mStudent);

                mPerson = new Person("lisi","women",21,new String[]{"duaghter","chairman","manager","clerk"});
                mStudent = new Student(mPerson);
                mStudent.setAcademy("english");
                mStudent.setMajor("english");
                mStudent.setScore(110.0f);
                mSchool.addStudent(mStudent);

                mPerson = new Person("wangwu","men",22,new String[]{"son","father","gradfather","chairman","manager","clerk"});
                mStudent = new Student(mPerson);
                mStudent.setAcademy("math");
                mStudent.setMajor("math");
                mStudent.setScore(120.0f);
                mSchool.addStudent(mStudent);

                mPerson = new Person("zhaoliu","women",23,new String[]{"duaghter","mother","clerk"});
                mStudent = new Student(mPerson);
                mStudent.setAcademy("music");
                mStudent.setMajor("muisc");
                mStudent.setScore(130.0f);
                mSchool.addStudent(mStudent);

                mPerson = new Person("wangba","men",24,new String[]{"son","father","gradfather","chairman","manager","clerk"});
                mStudent = new Student(mPerson);
                mStudent.setAcademy("computer");
                mStudent.setMajor("computer");
                mStudent.setScore(140.0f);
                mSchool.addStudent(mStudent);

                mPerson = new Person("wugui","women",25,new String[]{"duaghter","monher","gradmother","chairman","manager","clerk"});
                mStudent = new Student(mPerson);
                mStudent.setAcademy("math");
                mStudent.setMajor("math");
                mStudent.setScore(150.0f);
                mSchool.addStudent(mStudent);

                mPerson = new Person("bie","men",26,new String[]{"son","father","gradfather","chairman","manager","clerk"});
                mStudent = new Student(mPerson);
                mStudent.setAcademy("language");
                mStudent.setMajor("english");
                mStudent.setScore(120.0f);
                mSchool.addStudent(mStudent);

                JsonTo = gson.toJson(mSchool);
                LogLog.i(TAG,"JsonTo:mSchool -- >" + JsonTo);
                break;
            case R.id.sina_btn_Json_to:
                //Person
                //if(TextUtils.isEmpty(JsonTo)) return;
                //mPerson = null;
                //mPerson = JSON.parseObject(JsonTo,Person.class);
                //Men
                //Women
                //Student
                //School
                if(TextUtils.isEmpty(JsonTo)) return;
                mSchool = null ;
                mSchool = JSON.parseObject(JsonTo,School.class);
                LogLog.i(TAG,"mSchool: FROM JSON:   " + mSchool);
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http:www.baidu.com");
                try{
                    HttpResponse response = httpClient.execute(httpGet);
                    response.getEntity();
                }catch (Exception e){}

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //没有标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerMessageReceiver();  // used for receive msg

    }

    private void initView(){
        TextView mImei = (TextView) findViewById(R.id.tv_imei);
        String udid =  ExampleUtil.getImei(getApplicationContext(), "");
        if (null != udid) mImei.setText("IMEI: " + udid);

        TextView mAppKey = (TextView) findViewById(R.id.tv_appkey);
        String appKey = ExampleUtil.getAppKey(getApplicationContext());
        if (null == appKey) appKey = "AppKey异常";
        mAppKey.setText("AppKey: " + appKey);

        String packageName =  getPackageName();
        TextView mPackage = (TextView) findViewById(R.id.tv_package);
        mPackage.setText("PackageName: " + packageName);

        String deviceId = ExampleUtil.getDeviceId(getApplicationContext());
        TextView mDeviceId = (TextView) findViewById(R.id.tv_device_id);
        mDeviceId.setText("deviceId:" + deviceId);

        String versionName =  ExampleUtil.GetVersion(getApplicationContext());
        TextView mVersion = (TextView) findViewById(R.id.tv_version);
        mVersion.setText("Version: " + versionName);

        mInit = (Button)findViewById(R.id.init);
        mInit.setOnClickListener(this);

        mStopPush = (Button)findViewById(R.id.stopPush);
        mStopPush.setOnClickListener(this);

        mResumePush = (Button)findViewById(R.id.resumePush);
        mResumePush.setOnClickListener(this);

        mSetting = (Button)findViewById(R.id.setting);
        mSetting.setOnClickListener(this);

        msgText = (EditText)findViewById(R.id.msg_rec);

        mBtnGsonTo = (Button)findViewById(R.id.sina_btn_gson_to);
        mBtnGsonFrom = (Button)findViewById(R.id.sina_btn_gson_from);
        mBtnJsonFrom = (Button)findViewById(R.id.sina_btn_Json_from);
        mBtnJsonTo = (Button)findViewById(R.id.sina_btn_Json_to);
        mBtnGsonFrom.setOnClickListener(this);
        mBtnGsonTo.setOnClickListener(this);
        mBtnJsonFrom.setOnClickListener(this);
        mBtnJsonTo.setOnClickListener(this);
    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(getApplicationContext());
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        JPushInterface.stopPush(this);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

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
                setCostomMsg(showMsg.toString());
            }
        }
    }

    private void setCostomMsg(String msg){
        if (null != msgText) {
            msgText.setText(msg);
            msgText.setVisibility(android.view.View.VISIBLE);
        }
    }
}
