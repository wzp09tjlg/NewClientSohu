package com.sina.home.testfor_newclient.bean.GsonBean;

import com.sina.home.testfor_newclient.utils.LogLog;

/**
 * Created by Walter on 2016/1/27.
 */
public class Women  extends Person{
    private static final String TAG = "Women";
    //widget
    //data
    private String  desc;
    //interface
    public Women(){
        this.setSex("Women");
    }

    @Override
    public void walk() {
        super.walk();
        LogLog.i(TAG,"walk...");
    }

    @Override
    public void eat() {
        super.eat();
        LogLog.i(TAG,"eat...");
    }

    @Override
    public void sleep() {
        super.sleep();
        LogLog.i(TAG,"eat...");
    }

    @Override
    public void draw() {
        super.draw();
        LogLog.i(TAG," Women draw...");
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Women[name:" +this.getName() +",sex:" + this.getSex() +",age:" + this.getAge() +",desc:" + this.desc + "]";
    }
}
