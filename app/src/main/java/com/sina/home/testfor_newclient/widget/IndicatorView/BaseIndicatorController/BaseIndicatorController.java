package com.sina.home.testfor_newclient.widget.IndicatorView.BaseIndicatorController;

import android.animation.Animator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.List;

/**
 * Created by Walter on 2016/1/20.
 * 这里定义抽象类，什么两个抽象方法。用于子类实现
 */
public abstract class BaseIndicatorController {
    private View mTarget;
    private List<Animator> mAnimators;

    public abstract void onDraw(Canvas canvas,Paint paint);

    public abstract List<Animator> addAnimation();

    public int getWidth(){
        return mTarget.getWidth();
    }

    public int getHeight(){
        return mTarget.getHeight();
    }

    public void postInvalidate(){
        mTarget.postInvalidate();
    }

    public void setTarget(View mTarget) {
        this.mTarget = mTarget;
    }

    public View getTarget(){ return mTarget;}

    public void setAnimationStatus(AnimStatus animStatus){
        if (mAnimators==null){
            return;
        }
        int count=mAnimators.size();
        for (int i = 0; i < count; i++) {
            Animator animator=mAnimators.get(i);
            boolean isRunning=animator.isRunning();
            switch (animStatus){
                case START:
                    if (!isRunning){
                        animator.start();
                    }
                    break;
                case END:
                    if (isRunning){
                        animator.end();
                    }
                    break;
                case CANCEL:
                    if (isRunning){
                        animator.cancel();
                    }
                    break;
            }
        }
    }

    public enum AnimStatus{
        START,END,CANCEL
    }
}
