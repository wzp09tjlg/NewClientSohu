package com.sina.home.testfor_newclient.bean.ClientBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walter on 2016/1/28.
 */
public class VideoBean {
    private static final String TAG = "VideoBean";
    //data
    private String title;
    private String comments;
    private String origin;
    private List<PicBean> mListPics;

    public VideoBean(){
        mListPics = new ArrayList<>();
    }

    public VideoBean(VideoBean bean){
        this.title = bean.getTitle();
        this.comments = bean.getComments();
        this.origin = bean.getOrigin();
        this.mListPics = bean.getmListPics();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<PicBean> getmListPics() {
        return mListPics;
    }

    public void setmListPics(List<PicBean> mListPics) {
        this.mListPics = mListPics;
    }

    @Override
    public String toString() {
        return "VideoBean[title:" + this.title +",comments:" + this.comments + ",mListPics:" + this.mListPics.toString() +"]";  //  TODO CONTINUE HERE.....
    }
}
