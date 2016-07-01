package com.sina.home.testfor_newclient.widget.ProjectToast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sina.home.testfor_newclient.R;

/**
 * Created by Walter on 2016/1/15.
 *  自定义的toast ，可以自己设置显示的时间 和 动态的取消显示
 */
public class ShortToast {
    public static ShortToast makeText(Context context, CharSequence text, int duration)
    {
        ShortToast result = new ShortToast(context);

        LinearLayout mLayout=new LinearLayout(context);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setTextColor(Color.rgb(121,121,121));
        tv.setGravity(Gravity.CENTER);

        ImageView imgCancel = new ImageView(context);
        imgCancel.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_close));
        imgCancel.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgCancel.setClickable(true);
        mLayout.setBackgroundResource(R.drawable.layout_short_toast_shape);

        int cancelWidth = (int)context.getResources().getDimension(R.dimen.dimen_60dp);
        int cancelHeight = (int)context.getResources().getDimension(R.dimen.dimen_60dp);

        int w = context.getResources().getDisplayMetrics().widthPixels - cancelWidth;
        int h = (int)context.getResources().getDimension(R.dimen.dimen_80dp);

        mLayout.addView(tv, w, h);

        mLayout.addView(imgCancel,cancelWidth,cancelHeight);

        result.mNextView = mLayout;
        result.mImgCancel = imgCancel;
        result.mDuration = duration;

        return result;
    }

    public static final int LENGTH_SHORT = 2000;
    public static final int LENGTH_LONG = 3500;

    private final Handler mHandler = new Handler();
    private int mDuration=LENGTH_SHORT;
    private int mGravity = Gravity.CENTER;
    private int mX, mY;
    private float mHorizontalMargin;
    private float mVerticalMargin;
    private View mView;
    private View mNextView;

    /** 添加的变量*/
    private ImageView mImgCancel;
    private boolean isShowing;

    private   View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               cancelShow();
        }
    };

    private WindowManager mWM;
    private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();

    private ShortToast(Context context) {
        init(context);
    }

    /**
     * Set the view to show.
     * @see #getView
     */
    public void setView(View view) {
        mNextView = view;
    }

    /**
     * Return the view.
     * @see #setView
     */
    public View getView() {
        return mNextView;
    }

    /**
     * Set how long to show the view for.
     * @see #LENGTH_SHORT
     * @see #LENGTH_LONG
     */
    public void setDuration(int duration) {
        mDuration = duration;
    }

    /**
     * Return the duration.
     * @see #setDuration
     */
    public int getDuration() {
        return mDuration;
    }

    /**
     * Set the margins of the view.
     *
     * @param horizontalMargin The horizontal margin, in percentage of the
     *		container width, between the container's edges and the
     *		notification
     * @param verticalMargin The vertical margin, in percentage of the
     *		container height, between the container's edges and the
     *		notification
     */
    public void setMargin(float horizontalMargin, float verticalMargin) {
        mHorizontalMargin = horizontalMargin;
        mVerticalMargin = verticalMargin;
    }

    /**
     * Return the horizontal margin.
     */
    public float getHorizontalMargin() {
        return mHorizontalMargin;
    }

    /**
     * Return the vertical margin.
     */
    public float getVerticalMargin() {
        return mVerticalMargin;
    }

    /**
     * Set the location at which the notification should appear on the screen.
     * @see android.view.Gravity
     * @see #getGravity
     */
    public void setGravity(int gravity, int xOffset, int yOffset) {
        mGravity = gravity;
        mX = xOffset;
        mY = yOffset;
    }

    /**
     * Get the location at which the notification should appear on the screen.
     * @see android.view.Gravity
     * @see #getGravity
     */
    public int getGravity() {
        return mGravity;
    }

    /**
     * Return the X offset in pixels to apply to the gravity's location.
     */
    public int getXOffset() {
        return mX;
    }

    /**
     * Return the Y offset in pixels to apply to the gravity's location.
     */
    public int getYOffset() {
        return mY;
    }

    /**
     * schedule handleShow into the right thread
     */
    public void show() {
        mImgCancel.setOnClickListener(listener);

        mHandler.post(mShow);

        if(mDuration>0)
        {
            mHandler.postDelayed(mHide, mDuration);
        }
    }

    /**
     * schedule handleHide into the right thread
     */
    public void hide() {
        mHandler.post(mHide);
    }

    private final Runnable mShow = new Runnable() {
        public void run() {
            handleShow();
        }
    };

    private final Runnable mHide = new Runnable() {
        public void run() {
            handleHide();
        }
    };

    private void init(Context context)
    {
        final WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                //| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE  //这个属性是针对toast 是否可以被触摸
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        params.windowAnimations = android.R.style.Animation_Toast;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");

        mWM = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
    }


    private void handleShow() {

        if (mView != mNextView) {
            // remove the old view if necessary
            handleHide();
            mView = mNextView;
            final int gravity = mGravity;
            mParams.gravity = gravity;
            if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL)
            {
                mParams.horizontalWeight = 1.0f;
            }
            if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL)
            {
                mParams.verticalWeight = 1.0f;
            }
            mParams.x = mX;
            mParams.y = mY;
            mParams.verticalMargin = mVerticalMargin;
            mParams.horizontalMargin = mHorizontalMargin;
            if (mView.getParent() != null)
            {
                mWM.removeView(mView);
            }
            mWM.addView(mView, mParams);
            isShowing = true;
        }
    }

    private void handleHide()
    {
        if (mView != null)
        {
            if (mView.getParent() != null)
            {
                mWM.removeView(mView);
            }
            mView = null;
            isShowing = false;
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void cancelShow(){
        if(isShowing)
            handleHide();
    }
}
