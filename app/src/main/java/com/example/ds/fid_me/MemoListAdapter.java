package com.example.ds.fid_me;

import android.content.Context;
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
        if (convertView == null) {
            itemView = new MemoListItemView(mContext);
        } else {
            itemView = (MemoListItemView)convertView;
        }

        // set current item data
        itemView.setContents(0, ((String) mItems.get(position).getData(0)));
        itemView.setContents(1, ((String) mItems.get(position).getData(1)));
        itemView.setContents(2, ((String) mItems.get(position).getData(2)));
        itemView.setContents(3, ((String) mItems.get(position).getData(4)));
        itemView.setContents(5, ((String) mItems.get(position).getData(5)));


        return itemView;
    }
}
