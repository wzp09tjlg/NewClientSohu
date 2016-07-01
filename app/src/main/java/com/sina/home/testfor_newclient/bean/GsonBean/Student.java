package com.sina.home.testfor_newclient.bean.GsonBean;

/**
 * Created by Walter on 2016/1/27.
 */
public class Student {
    private static final String TAG = "Studeng";
    //widget
    //data
    private String academy;
    private String major;
    private Person person;
    private float score;
    //interface
    public Student(){
        this.person = new Person();
    }

    public Student(Person _person){
        this.person = _person;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student[person:" + this.person.toString() + ",academy:" + this.academy +",major:" + this.major +",score:" + this.score +"]";
    }
}
