package com.sina.home.testfor_newclient.bean.GsonBean;

import com.sina.home.testfor_newclient.utils.LogLog;

/**
 * Created by Walter on 2016/1/27.
 */
public class Men extends Person {
    private static final String TAG = "Men";
    //widget
    //data
    private boolean desc;
    //interface
    public Men(){
        this.setSex("Men");
    };

    @Override
    public void walk() {
        super.walk();
        LogLog.i(TAG,"walk...");;
    }

    @Override
    public void eat() {
        super.eat();
        LogLog.i(TAG,"eat...");
    }

    @Override
    public void sleep() {
        super.sleep();
        LogLog.i(TAG,"sleep...");
    }

    @Override
    public void play() {
        super.play();
        LogLog.i(TAG,"play...");
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Men[name:" + this.getName() +",sex:" + this.getSex()+ ",age:" + this.getAge() +",desc:" +this.desc+ "]";
    }
}
