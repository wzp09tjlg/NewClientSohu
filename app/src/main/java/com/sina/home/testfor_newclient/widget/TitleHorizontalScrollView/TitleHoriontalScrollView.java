package com.sina.home.testfor_newclient.widget.TitleHorizontalScrollView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.sina.home.testfor_newclient.R;

/**
 * Created by Walter on 2016/1/13.
 */
public class TitleHoriontalScrollView extends HorizontalScrollView {
    private static final String TAG = "TitleHoriontalScrollView";
    public static final int TITLE_CLICKED_TYPE_KEEP_SIDE = 0; //标题点击靠边微调
    public static final int TITLE_CLICKED_TYPE_ADJUST_CENTER = 1; //标题点击靠中间微调
    //widget
    private LinearLayout mLayoutTitles;
    //data
    /** ItemScrollType 每项点击之后停留微调的类型 */
    private  int mClickedKeepType ;
    public TitleHoriontalScrollView(Context context) {
        super(context);
        findView(context);
    }

    public TitleHoriontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);
        findView(context);
    }

    public TitleHoriontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(context, attrs);
        findView(context);
    }

    private void initAttributeSet(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.TitleHScrlView);
        int clickedType = array.getInt(R.styleable.TitleHScrlView_clickedType,0);
        setClickedKeepType(clickedType);
        array.recycle();
    }

    private void findView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_title_horizontalscrollview,null);
        mLayoutTitles = (LinearLayout)view.findViewById(R.id.layout_titles);
        addView(view);
    }

    public void setClickedKeepType(int mClickedKeepType) {
        this.mClickedKeepType = mClickedKeepType;
    }

    public void doAjustPosition(View view,int position,int type){ //type 0表示非居中显示 1表示居中显示
        switch (type) {
            case TITLE_CLICKED_TYPE_KEEP_SIDE://点击标题位置靠边微调
            {
                int widthView = view.getWidth();
                int[] posView = new int[2];
                view.getLocationInWindow(posView);

                int widthViewH = getWidth();
                int[] posViewH = new int[2];
                getLocationInWindow(posViewH);

                //计算要移动的距离 然后进行移动
                int durR = posView[0] + widthView - posViewH[0] - widthViewH;
                if (durR > 0) //就是点击的点超过horizontalScrollview的右边界 那么就会移动
                {
                    int scrollX1 = getScrollX();
                    smoothScrollTo(durR + scrollX1, 0);
                }

                int durL = posView[0] - posViewH[0];
                if (durL < 0) {
                    int scrollX2 = getScrollX();
                    smoothScrollTo(scrollX2 + durL, 0);
                }
            }
            break;
            case TITLE_CLICKED_TYPE_ADJUST_CENTER: //点击标题位置居中微调
            {
                if (position < 3)  //如果设置的是居中微调处理，但是最两端的位置没有显示正常，这里做两边微调处理,
                {
                    doAjustPosition(view, 0, TITLE_CLICKED_TYPE_KEEP_SIDE);
                    return;
                }
                if (position > mLayoutTitles.getChildCount() - 3) {
                    doAjustPosition(view, mLayoutTitles.getChildCount() - 1, TITLE_CLICKED_TYPE_KEEP_SIDE);
                    return;
                }
                int widthView = view.getWidth() / 2;
                int[] posViewMiddle = new int[2];
                view.getLocationInWindow(posViewMiddle);
                int widthHorizontalScrollView = getWidth() / 2;
                int[] posHorizontalScrollView = new int[2];
                getLocationInWindow(posHorizontalScrollView);
                int interval = posViewMiddle[0] + widthView - posHorizontalScrollView[0] - widthHorizontalScrollView;
                int scrollX = getScrollX();
                if (interval > 0) { //horizontalScrollView 左移interval 的距离
                    smoothScrollTo(scrollX + interval, 0);
                } else { ////horizontalScrollView 右移interval 的距离
                    smoothScrollTo(scrollX + interval, 0);
                }
            }
            break;
        }
    }

    public int getClickedKeepType() {
        return mClickedKeepType;
    }

    public LinearLayout getLayoutTitles() {
        return mLayoutTitles;
    }

    public View getCurrentItem(int position){
        if(position < mLayoutTitles.getChildCount())
            return mLayoutTitles.getChildAt(position);
        else
            return null;
    }

    public void setPositionSelected(int index){
        if(index < 0 || index > mLayoutTitles.getChildCount()) return;
        for(int i=0;i<mLayoutTitles.getChildCount();i++){
            mLayoutTitles.getChildAt(i).setSelected(false);
        }
        mLayoutTitles.getChildAt(index).setSelected(true);
    }
}
