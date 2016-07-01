package com.sina.home.testfor_newclient.widget.ProjectToast;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sina.home.testfor_newclient.R;

/**
 * Created by Walter on 2016/1/15.
 */
public class LongToast extends BaseToast {
    private ImageView iconCancel;
    public static LongToast makeText(Context context,CharSequence info,int duration){
        LongToast toast = new LongToast(context);
        LinearLayout mLayout=new LinearLayout(context);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv = new TextView(context);
        tv.setText(info);
        tv.setTextColor(Color.rgb(121, 121, 121));
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

        toast.setView(mLayout);
        toast.setDuration(duration);
        return toast;
    }

    public static LongToast makeText(Context context,CharSequence info,int duration,boolean touchable){
        LongToast toast = new LongToast(context,touchable);
        LinearLayout mLayout=new LinearLayout(context);
        mLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv = new TextView(context);
        tv.setText(info);
        tv.setTextColor(Color.rgb(121, 121, 121));
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

        toast.setView(mLayout);
        toast.setDuration(duration);
        toast.iconCancel = imgCancel;
        return toast;
    }

    public LongToast(Context context){
        super(context);
    }

    public LongToast(Context context,boolean touchable){
        super(context,touchable);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cancelShow();
        }
    };

    @Override
    public void show() {
        iconCancel.setOnClickListener(listener);
        super.show();
    }
}
