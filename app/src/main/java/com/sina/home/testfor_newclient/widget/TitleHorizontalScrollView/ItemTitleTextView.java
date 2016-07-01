package com.sina.home.testfor_newclient.widget.TitleHorizontalScrollView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sina.home.testfor_newclient.R;

/**
 * Created by Walter on 2016/1/8.
 */
public class ItemTitleTextView extends RelativeLayout {
    private static final String TAG = "ItemTitleView";
    //widget
    private TextView mTextTitle;
    //data
    private int mMarginLeft;
    private int mMarginRight;
    private int mMarginTop;
    private int mMarginBottom;
    //interface
    public ItemTitleTextView(Context context) {
        super(context);
        findView(context);
    }

    public ItemTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        findView(context);
        initAttribute(context,attrs);
    }

    public ItemTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        findView(context);
        initAttribute(context,attrs);
    }

    private void initAttribute(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.ItemTitleText);
        int mMarginLeft = (int)array.getDimension(R.styleable.ItemTitleText_marginLeft,10);
        int mMarginRight = (int)array.getDimension(R.styleable.ItemTitleText_marginRight,10);
        int mMarginTop = (int)array.getDimension(R.styleable.ItemTitleText_marginTop, 10);
        int mMarginBottom = (int)array.getDimension(R.styleable.ItemTitleText_marginBottom, 10);
        setmMarginLeft(mMarginLeft);
        setmMarginRight(mMarginRight);
        setmMarginTop(mMarginTop);
        setmMarginBottom(mMarginBottom);
        array.recycle();  //属性资源使用使用完之后记得回收掉,不然十分耗费资源
    }

    private void findView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.item_title_text,null);
        mTextTitle = (TextView)view.findViewById(R.id.text_title);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
        addView(view,params);
    }

    public void setTextTitle(String title){
       if(!TextUtils.isEmpty(title))
           mTextTitle.setText(title);
    }

    public void setmMarginLeft(int mMarginLeft) {
        this.mMarginLeft = mMarginLeft;
    }

    public void setmMarginRight(int mMarginRight) {
        this.mMarginRight = mMarginRight;
    }

    public void setmMarginTop(int mMarginTop) {
        this.mMarginTop = mMarginTop;
    }

    public void setmMarginBottom(int mMarginBottom) {
        this.mMarginBottom = mMarginBottom;
    }

    @Override
    public void setSelected(boolean selected) {
        mTextTitle.setSelected(selected);
    }
}
