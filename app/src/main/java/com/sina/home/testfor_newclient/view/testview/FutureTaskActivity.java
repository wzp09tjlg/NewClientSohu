package com.sina.home.testfor_newclient.view.testview;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Wuzp on 2016/2/24.
 */
public class FutureTaskActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "FutureTaskActivity";
    //widget
    private Button mBtnStart;
    private Button mBtnGet;
    //data
    private ExecutorService executorService;
    private FutureTask<String> task;
    private boolean flag = false;
    //interface
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.sina_btn_futuretask_start:
             executorService.execute(task);
             flag = true;
             break;
         case R.id.sina_btn_futuretask_get:
             if(!flag) return;
             long now = System.currentTimeMillis();
             Log.e(TAG,"click start:" + now);
             try {
                 String temp = task.get();
             }catch (Exception e){
                 e.printStackTrace();
             }
             Log.e(TAG,"click end:" + (System.currentTimeMillis() - now));
             break;
     }
    }

    @Override
    protected void setContextView() {
     setContentView(R.layout.activity_futuretask);
    }

    @Override
    protected void findView() {
       mBtnStart = (Button)findViewById(R.id.sina_btn_futuretask_start);
        mBtnGet = (Button)findViewById(R.id.sina_btn_futuretask_get);
    }

    @Override
    protected void initView() {
        mBtnStart.setOnClickListener(this);
        mBtnGet.setOnClickListener(this);
        setData();
    }

    private void setData(){
        //可以实例化四种线程池
        executorService = Executors.newSingleThreadExecutor();    //.newScheduledThreadPool(2)   //.newCachedThreadPool()   //.newSingleThreadExecutor()    //  newFixedThreadPool(2);
        task = new FutureTask<String>(new Callable<String>() {  //实例化Task
            @Override
            public String call() throws Exception {
                long now = System.currentTimeMillis();
                Log.e(TAG,"TASK call is now:" + now);
                StringBuilder builder = new StringBuilder();
                for(int i=0;i<1000000;i++){
                    builder.append(i).append(",");
                }
                Log.e(TAG,"TASK call is now:" + (System.currentTimeMillis() - now));
                return builder.toString();
            }
        });
    }
}
