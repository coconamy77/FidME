package com.example.ds.fid_me;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 2018-05-25.
 */

public class MemoListAdapter extends BaseAdapter {
    private Context mContext;

    private List<MemoListItem> mItems = new ArrayList<MemoListItem>();

    public MemoListAdapter(Context context) {
        mContext = context;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(MemoListItem it) {
        mItems.add(it);
    }

    public void setListItems(List<MemoListItem> lit) {
        mItems = lit;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MemoListItemView itemView;
       // if (convertView == null) {
            itemView = new MemoListItemView(mContext);
       // } else {
       //     itemView = (MemoListItemView)convertView;
      //  }     ㅗ퍼ㅓㅏㅣㅜ;ㅣㅓㅣ
        MemoListItem item = mItems.get(position);
        // set current item data
        itemView.setContents(0, (String)item.getData(0));
        itemView.setContents(1, (String)item.getData(1));
        itemView.setContents(2, (String)item.getData(2));
        itemView.setContents(3, (String)item.getData(3));
        itemView.setContents(4,item.getData(4)+"");

        Log.d("date",(String)item.getData(0)+"");


        return itemView;
    }
}
