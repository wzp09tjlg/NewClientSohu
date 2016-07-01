package com.sina.home.testfor_newclient.view.testview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.base.BaseActivity;

/**
 * Created by Walter on 2016/2/17.
 */
public class PopupWindowActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "PopupWindowActivity";
    //widget
    private Button mBtnCenter;
    private Button mBtnLeftUp;
    private Button mBtnLeftDown;
    private Button mBtnRightUp;
    private Button mBtnRightDown;
    //data
    private Context mContext = this;
    //interface
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.sina_popupwindow_center:
              View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwind,null);
              view.findViewById(R.id.sina_btn_popupwindow_msg).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Toast.makeText(mContext,"this is a popupwindos totast",Toast.LENGTH_LONG).show();
                  }
              });
              PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
              popupWindow.setTouchable(true);
              popupWindow.setTouchInterceptor(
                      new View.OnTouchListener() {
                  @Override
                  public boolean onTouch(View v, MotionEvent event) {
                      return true;
                  }
              });

              popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_cat1));
              popupWindow.showAtLocation(v, Gravity.BOTTOM,10,10);
              break;
          case R.id.sina_popupwindow_left_up:
              break;
          case R.id.sina_popupwindow_left_down:
              break;
          case R.id.sina_popupwindow_right_up:
              break;
          case R.id.sina_popupwindow_right_down:
              break;
      }
    }

    @Override
    protected void setContextView() {
      setContentView(R.layout.activity_popupwindow);
    }

    @Override
    protected void findView() {
      mBtnCenter = $(R.id.sina_popupwindow_center);
        mBtnLeftUp = $(R.id.sina_popupwindow_left_up);
        mBtnLeftDown = $(R.id.sina_popupwindow_left_down);
        mBtnRightUp = $(R.id.sina_popupwindow_right_up);
        mBtnRightDown = $(R.id.sina_popupwindow_right_down);
    }

    @Override
    protected void initView() {
        mBtnCenter.setOnClickListener(this);
        mBtnLeftUp.setOnClickListener(this);
        mBtnLeftUp.setOnClickListener(this);
        mBtnRightUp.setOnClickListener(this);
        mBtnRightDown.setOnClickListener(this);
    }
}
