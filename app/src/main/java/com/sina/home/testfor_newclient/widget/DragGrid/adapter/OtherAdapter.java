package com.sina.home.testfor_newclient.widget.DragGrid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sina.home.testfor_newclient.R;
import com.sina.home.testfor_newclient.bean.ChannelItem;

import java.util.List;

/**
 * Created by Walter on 2016/1/7.
 */
public class OtherAdapter extends BaseAdapter {
    private Context context;
    public List<ChannelItem> channelList;
    private TextView item_text;
    /** 是否可见 */
    boolean isVisible = true;
    /** 要删除的position */
    public int remove_position = -1;

    public OtherAdapter(Context context, List<ChannelItem> channelList) {
        this.context = context;
        this.channelList = channelList;
    }

    @Override
    public int getCount() {
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public ChannelItem getItem(int position) {
        if (channelList != null && channelList.size() != 0) {
            return channelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.subscribe_category_item, null);
        item_text = (TextView) view.findViewById(R.id.text_item);
        ChannelItem channel = getItem(position);
        item_text.setText(channel.getName());
        if (!isVisible && (position == -1 + channelList.size())){
            item_text.setText("");
        }
        if(remove_position == position){
            item_text.setText("");
        }
        return view;
    }

    /** 获取频道列表 */
    public List<ChannelItem> getChannnelLst() {
        return channelList;
    }

    /** 添加频道列表 */
    public void addItem(ChannelItem channel) {
        channelList.add(channel);
        notifyDataSetChanged();
    }

    /** 设置删除的position */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
    }

    /** 删除频道列表 */
    public void remove() {
        if(remove_position < 0 || remove_position >= getCount()) return;
        channelList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }
    /** 设置频道列表 */
    public void setListDate(List<ChannelItem> list) {
        channelList = list;
    }

    /** 获取是否可见 */
    public boolean isVisible() {
        return isVisible;
    }

    /** 设置是否可见 */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
