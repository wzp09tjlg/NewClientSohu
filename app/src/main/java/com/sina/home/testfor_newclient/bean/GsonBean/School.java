package com.sina.home.testfor_newclient.bean.GsonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walter on 2016/1/27.
 */
public class School  {
    private static final String TAG = "School";
    //widget
    //data
    private String name;
    private int age;
    private String addres;
    private List<Student>  mListStudent;
    //interface
    public School(){
        mListStudent = new ArrayList<>();
    }

    public School(School _school){
        this.name = _school.getName();
        this.age = _school.getAge();
        this.addres = _school.getAddres();
        this.mListStudent = _school.getmListStudent();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public List<Student> getmListStudent() {
        return mListStudent;
    }

    public void setmListStudent(List<Student> mListStudent) {
        this.mListStudent = mListStudent;
    }

    public void addStudent(Student _student){
        if(null == mListStudent)
            mListStudent = new ArrayList<>();
        mListStudent.add(_student);
    }

    public boolean removeStudent(Student _student){
        if(null == _student) return false;
        mListStudent.remove(_student);
        return true;
    }

    @Override
    public String toString() {
        return "School[name:" + this.name +",age:" + this.getAge() +",address:" + this.addres + ",mListStudent:" + this.mListStudent + "]";
    }
}
