package com.sina.home.testfor_newclient.bean.GsonBean;

import com.sina.home.testfor_newclient.utils.LogLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Walter on 2016/1/27.
 */
public class Person extends Animal {
    private static final String TAG = "Person";
    //widget
    //data
    private String name;
    private String sex; // M or F  M or W
    private int age;
    private List<String> type;
    //interface
    public Person(){
        type = new ArrayList<>();
    }

    public Person(List<String> _type){
        this.type = _type;
    }

    public Person(Person _person){
        this.name = _person.getName();
        this.sex = _person.getSex();
        this.age = _person.getAge();
        this.type = _person.getType();

    }

    public Person(String _name,String _sex,int _age,String ...param){
        type = new ArrayList<>();
        this.name = _name;
        this.sex = _sex;
        this.age = _age;
        if(param.length > 0)
         type.addAll(Arrays.asList(param));
    }

    @Override
    public void walk() {
        LogLog.i(TAG,"walk");
    }

    @Override
    public void sleep() {
        LogLog.i(TAG,"sleep");
    }

    @Override
    public void eat() {
        LogLog.i(TAG,"eat");
    }

    public void play(){
       LogLog.i(TAG,"play");
    }

    public void draw(){
        LogLog.i(TAG,"draw");
    }

    public void addType(String _type){
        if(null == type){
            type = new ArrayList<>();
        }
        type.add(_type);
    }

    public void removeType(String _type){
        type.remove(_type);
    }

    /** 性别*/
    public enum PERSON_SEX{
        Men("Men"),
        Women("Women"),
        Male("Male"),
        Female("Female");

        private String value;

         PERSON_SEX(String _value){
          this.value = _value;
         }

        public String getValue(){
            return this.value;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Person[name:" + this.name +",sex:" + this.sex + ",age:" + this.age + ",type:" + this.type.toString() + "]";
    }

    /** 角色*/
    public static enum PERSON_TYPE{
        /** 家庭*/
       GrandFather,
       GrandMother,
       Father,
       Mother,
       Son,
       Duaghter,
       GrandSon,
       GrandDuaghter,

        /** 学校*/
        HeadMaster,
        Master,
        Teacher,
        Student,

        /** 公司*/
        BoardChairman,
        Manager,
        Clerk
    }
}
