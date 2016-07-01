package com.sina.home.testfor_newclient.widget.DragGrid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Walter on 2016/1/7.
 */
public class OtherGridView extends GridView {
    public OtherGridView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
