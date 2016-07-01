package com.sina.home.testfor_newclient.widget.IndicatorView.SubController;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.sina.home.testfor_newclient.widget.IndicatorView.BaseIndicatorController.BaseIndicatorController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/10/20.
 */
public class SemiCircleSpinIndicator extends BaseIndicatorController {


    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        RectF rectF=new RectF(0,0,getWidth(),getHeight());
        canvas.drawArc(rectF,-60,120,false,paint);
    }

    @Override
    public List<Animator> addAnimation() {
        List<Animator> animators=new ArrayList<>();
        ObjectAnimator rotateAnim=ObjectAnimator.ofFloat(getTarget(),"rotation",0,180,360);
        rotateAnim.setDuration(600);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.start();
        animators.add(rotateAnim);
        return animators;
    }


}
