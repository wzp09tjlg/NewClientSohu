package com.sina.home.testfor_newclient.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.bean.ClientBean.PicBean;

import java.util.List;

/**
 * Created by Walter on 2016/1/28.
 */
public class MorePicAdapter extends RecyclerView.Adapter<MorePicAdapter.ViewHolder> {
    private static final String TAG = "MorePicAdapter";
    //data
    private Context mContext;
    private List<PicBean> mList;

    public MorePicAdapter(Context context,List<PicBean> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_more_pic,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
      if(mList.get(i) == null) return;
        viewHolder.img.setImageDrawable(mContext.getResources().getDrawable(mList.get(i).getUrl()));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       private ImageView img;
       public ViewHolder(View view){
           super(view);
           img = (ImageView)view.findViewById(R.id.sina_img_new_base_more_pic_sub);
       }
   }
}
