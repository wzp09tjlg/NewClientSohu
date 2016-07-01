package com.sina.home.testfor_newclient.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.bean.ClientBean.NewBean;
import com.sina.home.testfor_newclient.widget.gridview.HorizontalGridView;

import java.util.List;

/**
 * Created by Walter on 2016/1/28.
 */
public class NewAdapter extends BaseAdapter {
    private static final String TAG = "NewAdapter";
    private static final int NEW_NO_PIC = 0;
    private static final int NEW_ONE_PIC = 1;
    private static final int NEW_MORE_PIC = 2;
    //data
    private List<NewBean> mList;
    private Context mContext;

    public NewAdapter(Context context,List<NewBean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return  ((NewBean)mList.get(position)).getType();
    }

    @Override
    public int getViewTypeCount() {
        return NEW_MORE_PIC + 1;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder_No_Pic mViewHolder_No_Pic = null;
        ViewHolder_One_Pic mViewHolder_One_Pic = null;
        ViewHolder_More_Pic mViewHolder_More_Pic = null;
        int type = getItemViewType(position);
        if(convertView == null){
            switch (type){
                case NEW_NO_PIC:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_new_base_no_pic,null);
                    mViewHolder_No_Pic = new ViewHolder_No_Pic(convertView);
                    convertView.setTag(mViewHolder_No_Pic);
                    break;
                case NEW_ONE_PIC:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_new_base_one_pic,null);
                    mViewHolder_One_Pic = new ViewHolder_One_Pic(convertView);
                    convertView.setTag(mViewHolder_One_Pic);
                    break;
                case NEW_MORE_PIC:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_new_base_more_pic,null);
                    mViewHolder_More_Pic = new ViewHolder_More_Pic(convertView);
                    convertView.setTag(mViewHolder_More_Pic);
                    break;
            }
        }else{
            switch (type){
                case NEW_NO_PIC:
                    mViewHolder_No_Pic = (ViewHolder_No_Pic)convertView.getTag();
                    break;
                case NEW_ONE_PIC:
                    mViewHolder_One_Pic = (ViewHolder_One_Pic)convertView.getTag();
                    break;
                case NEW_MORE_PIC:
                    mViewHolder_More_Pic = (ViewHolder_More_Pic)convertView.getTag();
                    break;
            }
        }
        switch (type){
            case NEW_NO_PIC:
                mViewHolder_No_Pic.title.setText(mList.get(position).getTitle());
                mViewHolder_No_Pic.comments.setText(mList.get(position).getComments());
                mViewHolder_No_Pic.origin.setText(mList.get(position).getOrigin());
                mViewHolder_No_Pic.imgOther.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case NEW_ONE_PIC:
                mViewHolder_One_Pic.title.setText(mList.get(position).getTitle());
                mViewHolder_One_Pic.img.setImageDrawable(mContext.getResources().getDrawable(mList.get(position).getmListPic().get(0).getUrl()));
                mViewHolder_One_Pic.comments.setText(mList.get(position).getComments());
                mViewHolder_One_Pic.origin.setText(mList.get(position).getOrigin());
                mViewHolder_One_Pic.imgOther.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case NEW_MORE_PIC:
                mViewHolder_More_Pic.title.setText(mList.get(position).getTitle());
                mViewHolder_More_Pic.grid.setAdapter(new MorePicAdapter(mContext,mList.get(position).getmListPic()));
                mViewHolder_More_Pic.comments.setText(mList.get(position).getComments());
                mViewHolder_More_Pic.picCount.setText(mList.get(position).getPicCounts());
                mViewHolder_More_Pic.origin.setText(mList.get(position).getOrigin());
                mViewHolder_More_Pic.imgOther.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
        }
        return convertView;
    }

    /** 新闻无图ViewHolder*/
    static class ViewHolder_No_Pic {
        private TextView title;
        private TextView comments;
        private TextView origin;
        private ImageView imgOther;

        public ViewHolder_No_Pic(View view){
            title = (TextView)view.findViewById(R.id.sina_text_new_base_no_pic_title);
            comments = (TextView)view.findViewById(R.id.sina_text_new_base_no_pic_comments);
            origin = (TextView)view.findViewById(R.id.sina_text_new_base_no_pic_origin);
            imgOther = (ImageView)view.findViewById(R.id.sina_img_new_base_no_pic_other);
        }
    }

    /** 新闻单图ViewHolder  or  广告单图*/
    static class ViewHolder_One_Pic{
        private TextView title;
        private ImageView img;
        private TextView comments;
        private TextView origin;
        private ImageView imgOther;

        public ViewHolder_One_Pic(View view){
            title = (TextView)view.findViewById(R.id.sina_text_new_base_one_pic_title);
            img = (ImageView)view.findViewById(R.id.sina_img_new_base_one_pic);
            comments = (TextView)view.findViewById(R.id.sina_text_new_base_one_pic_comments);
            origin = (TextView)view.findViewById(R.id.sina_text_new_base_one_pic_origin);
            imgOther = (ImageView)view.findViewById(R.id.sina_img_new_base_one_pic_other);
        }
    }

    /** 新闻多图*/
    static class ViewHolder_More_Pic{
        private TextView title;
        private HorizontalGridView grid;
        private TextView comments;
        private TextView picCount;
        private TextView origin;
        private ImageView imgOther;

        public ViewHolder_More_Pic(View view){
            title = (TextView)view.findViewById(R.id.sina_text_new_base_more_pic_title);
            grid = (HorizontalGridView)view.findViewById(R.id.sina_grid_new_base_more_pic);
            comments = (TextView)view.findViewById(R.id.sina_text_new_base_more_pic_comments);
            picCount = (TextView)view.findViewById(R.id.sina_text_new_base_more_pic_pic_count);
            origin = (TextView)view.findViewById(R.id.sina_text_new_base_more_pic_origin);
            imgOther = (ImageView)view.findViewById(R.id.sina_img_new_base_more_pic_other);
        }
    }
}
