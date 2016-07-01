package com.sina.home.testfor_newclient.bean.ClientBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walter on 2016/1/28.
 * 这里是针对仿新闻的bean
 */
public class NewBean {
    private static final String TAG = "NewBean";
    //data
    private String title;
    private int type;
    private List<PicBean>  mListPic;
    private String comments;
    private String picCounts;
    private String origin;

    public NewBean(){
        mListPic = new ArrayList<>();
    }

    public NewBean(NewBean bean){
        this.title = bean.getTitle();
        this.type = bean.getType();
        this.comments = bean.getComments();
        this.picCounts = bean.getPicCounts();
        this.origin = bean.getOrigin();
        this.mListPic = bean.getmListPic();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<PicBean> getmListPic() {
        return mListPic;
    }

    public void setmListPic(List<PicBean> mListPic) {
        this.mListPic = mListPic;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPicCounts() {
        return picCounts;
    }

    public void setPicCounts(String picCounts) {
        this.picCounts = picCounts;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "NewBean[title:" + this.title + ",type:" + this.type + ",commits:" + this.comments +",picCounts:" + this.picCounts+ ",origin:" + this.origin + ",mListPics:"  + this.mListPic.toString() + "]";
    }
}
