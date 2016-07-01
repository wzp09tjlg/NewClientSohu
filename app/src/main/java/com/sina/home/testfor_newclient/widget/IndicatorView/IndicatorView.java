package com.sina.home.testfor_newclient.widget.IndicatorView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.widget.IndicatorView.BaseIndicatorController.BaseIndicatorController;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallBeatIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallClipRotateIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallClipRotateMultipleIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallClipRotatePulseIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallGridBeatIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallGridPulseIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallPulseIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallPulseRiseIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallPulseSyncIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallRotateIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallScaleIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallScaleMultipleIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallScaleRippleIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallScaleRippleMultipleIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallSpinFadeLoaderIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallTrianglePathIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallZigZagDeflectIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.BallZigZagIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.CubeTransitionIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.LineScaleIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.LineScalePartyIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.LineScalePulseOutIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.LineScalePulseOutRapidIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.LineSpinFadeLoaderIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.PacmanIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.SemiCircleSpinIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.SquareSpinIndicator;
import com.sina.home.testfor_newclient.widget.IndicatorView.SubController.TriangleSkewSpinIndicator;

/**
 * Created by Walter on 2016/1/20.
 *  这里创建自定义控件动画的基类，以后要是需要自己创建动画的法，只要重写两个方法就可以完成自定义动画。
 */
public class IndicatorView extends View {
    private static final String TAG = "IndiactorView";
    //indicators
    public static final int BallPulse=0;
    public static final int BallGridPulse=1;
    public static final int BallClipRotate=2;
    public static final int BallClipRotatePulse=3;
    public static final int SquareSpin=4;
    public static final int BallClipRotateMultiple=5;
    public static final int BallPulseRise=6;
    public static final int BallRotate=7;
    public static final int CubeTransition=8;
    public static final int BallZigZag=9;
    public static final int BallZigZagDeflect=10;
    public static final int BallTrianglePath=11;
    public static final int BallScale=12;
    public static final int LineScale=13;
    public static final int LineScaleParty=14;
    public static final int BallScaleMultiple=15;
    public static final int BallPulseSync=16;
    public static final int BallBeat=17;
    public static final int LineScalePulseOut=18;
    public static final int LineScalePulseOutRapid=19;
    public static final int BallScaleRipple=20;
    public static final int BallScaleRippleMultiple=21;
    public static final int BallSpinFadeLoader=22;
    public static final int LineSpinFadeLoader=23;
    public static final int TriangleSkewSpin=24;
    public static final int Pacman=25;
    public static final int BallGridBeat=26;
    public static final int SemiCircleSpin=27;
    //data
    private static final int DEFAULT_SIZE=45;
    private BaseIndicatorController mIndicatorController;
    private int IndicatorId;
    private int IndicatorColor;
    private Paint mPaint;
    private boolean mHasAnimation;

    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        IndicatorId = array.getInt(R.styleable.IndicatorView_indicator,0);
        IndicatorColor = array.getColor(R.styleable.IndicatorView_indicator_color,Color.RED);
        array.recycle();
        mPaint=new Paint();
        mPaint.setColor(IndicatorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        initIndicator(IndicatorId);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width  = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec);
        int height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            mIndicatorController.onDraw(canvas,mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(!mHasAnimation){
            mHasAnimation = true;
            mIndicatorController.addAnimation();
        }
    }

    @Override
    public void setVisibility(int v) {
    if (getVisibility() != v) {
            super.setVisibility(v);
            if (v == GONE || v == INVISIBLE) {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.END);
            } else {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.START);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.CANCEL);
    }

    private int measureDimension(int defaultSize,int measureSpec){
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }

    private void initIndicator(int IndicatorId){
        switch (IndicatorId){
            case BallPulse:
                mIndicatorController=new BallPulseIndicator();
                break;
            case BallGridPulse:
                mIndicatorController=new BallGridPulseIndicator();
                break;
            case BallClipRotate:
                mIndicatorController=new BallClipRotateIndicator();
                break;
            case BallClipRotatePulse:
                mIndicatorController=new BallClipRotatePulseIndicator();
                break;
            case SquareSpin:
                mIndicatorController=new SquareSpinIndicator();
                break;
            case BallClipRotateMultiple:
                mIndicatorController=new BallClipRotateMultipleIndicator();
                break;
            case BallPulseRise:
                mIndicatorController=new BallPulseRiseIndicator();
                break;
            case BallRotate:
                mIndicatorController=new BallRotateIndicator();
                break;
            case CubeTransition:
                mIndicatorController=new CubeTransitionIndicator();
                break;
            case BallZigZag:
                mIndicatorController=new BallZigZagIndicator();
                break;
            case BallZigZagDeflect:
                mIndicatorController=new BallZigZagDeflectIndicator();
                break;
            case BallTrianglePath:
                mIndicatorController=new BallTrianglePathIndicator();
                break;
            case BallScale:
                mIndicatorController=new BallScaleIndicator();
                break;
            case LineScale:
                mIndicatorController=new LineScaleIndicator();
                break;
            case LineScaleParty:
                mIndicatorController=new LineScalePartyIndicator();
                break;
            case BallScaleMultiple:
                mIndicatorController=new BallScaleMultipleIndicator();
                break;
            case BallPulseSync:
                mIndicatorController=new BallPulseSyncIndicator();
                break;
            case BallBeat:
                mIndicatorController=new BallBeatIndicator();
                break;
            case LineScalePulseOut:
                mIndicatorController=new LineScalePulseOutIndicator();
                break;
            case LineScalePulseOutRapid:
                mIndicatorController=new LineScalePulseOutRapidIndicator();
                break;
            case BallScaleRipple:
                mIndicatorController=new BallScaleRippleIndicator();
                break;
            case BallScaleRippleMultiple:
                mIndicatorController=new BallScaleRippleMultipleIndicator();
                break;
            case BallSpinFadeLoader:
                mIndicatorController=new BallSpinFadeLoaderIndicator();
                break;
            case LineSpinFadeLoader:
                mIndicatorController=new LineSpinFadeLoaderIndicator();
                break;
            case TriangleSkewSpin:
                mIndicatorController=new TriangleSkewSpinIndicator();
                break;
            case Pacman:
                mIndicatorController=new PacmanIndicator();
                break;
            case BallGridBeat:
                mIndicatorController=new BallGridBeatIndicator();
                break;
            case SemiCircleSpin:
                mIndicatorController=new SemiCircleSpinIndicator();
                break;
        }
        mIndicatorController.setTarget(this);
    }

}
