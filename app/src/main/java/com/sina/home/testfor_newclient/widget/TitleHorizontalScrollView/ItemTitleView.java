package com.sina.home.testfor_newclient.widget.TitleHorizontalScrollView;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.bean.ChannelItem;
import com.sina.home.testfor_newclient.listener.OnItemClickListener;

/**
 * Created by Walter on 2016/1/10.
 */
public class ItemTitleView extends LinearLayout {
    private static String TAG = "ItemTitleView";
    //widget
    private RelativeLayout layout;
    private ItemTitleTextView mText;
    private View mViewIndex;
    //data
    private OnItemClickListener<ChannelItem> mOnItemClickListener;
    private ChannelItem mChannelItem;
    private boolean textSelected = false;
    //interface
    public ItemTitleView(Context context) {
        super(context);
        findView(context);
    }

    public ItemTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        findView(context);
    }

    public ItemTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        findView(context);
    }

    private void findView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.item_title,null);
        layout = (RelativeLayout)view.findViewById(R.id.layout_title_item);
        mText = (ItemTitleTextView)view.findViewById(R.id.text_title_text);
        mViewIndex = view.findViewById(R.id.view_title_index);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(v, mChannelItem.getOrderId(), mChannelItem);
                mText.setSelected(true);
                mViewIndex.setSelected(true);
                ViewGroup.LayoutParams params = mViewIndex.getLayoutParams();
                params.width = mText.getWidth();
                mViewIndex.setLayoutParams(params);
                textSelected = true;
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(view,params);
    }

    public void setmOnItemClickListener(OnItemClickListener<ChannelItem> onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public void setTextTitle(String title){
        if(!TextUtils.isEmpty(title))
          mText.setTextTitle(title);
    }

    public void setChannelItem(ChannelItem channelItem){
        mChannelItem = channelItem;
    }

    public void  setTextSelected(boolean selected){
        mText.setSelected(selected);
        mViewIndex.setSelected(selected);
        textSelected = selected;
    }

    public void setChannelItemOrderId(int index){
       if(index< 0) return;
        mChannelItem.setOrderId(index);
    }

    @Override
    public boolean isSelected() {
        return textSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        textSelected = selected;
        mText.setSelected(selected);
        mViewIndex.setSelected(selected);
        ViewGroup.LayoutParams params = mViewIndex.getLayoutParams();
        params.width = mText.getWidth();
        mViewIndex.setLayoutParams(params);
    }
}
