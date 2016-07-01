package com.sina.home.testfor_newclient.bean.ClientBean;

/**
 * Created by Walter on 2016/1/28.
 */
public class PicBean {
    private static final String Tag = "PicBean";
    //data
    private int index;
    private int url;

    public PicBean(){}

    public PicBean(int _index,int _url){
        this.index = _index;
        this.url = _url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PicBean[index:" + this.index +",url:" + this.url +"]";
    }
}
