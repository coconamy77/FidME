package com.example.ds.fid_me;

/**
 * Created by DS on 2018-05-25.
 */

public class MemoListItem {

    private Object[] mData;
    private String mId;
    private boolean mSelectable = true;

    public MemoListItem(String itemId, Object[] obj) {
        mId = itemId;
        mData = obj;
    }

    public MemoListItem(String memoId, String memoDate, String memoName, String memoText,
                        String id_photo, String uri_photo, int memoRating)    {
        mId = memoId;
        mData = new Object[6];
        mData[0] = memoDate;
        mData[1] = memoName;
        mData[2] = memoText;
        mData[3] = id_photo;
        mData[4] = uri_photo;
        mData[5] = memoRating;

    }

    public boolean isSelectable() {
        return mSelectable;
    }

    public void setSelectable(boolean selectable) {
        mSelectable = selectable;
    }


    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }


    public Object[] getData() {
        return mData;
    }

    public Object getData(int index) {
        if (mData == null || index >= mData.length) {
            return null;
        }

        return mData[index];
    }

    public void setData(String[] mData) {
        this.mData = mData;
    }

    public int compareTo(MemoListItem other) {
        if (mData != null) {
            Object[] otherData = other.getData();
            if (mData.length == otherData.length) {
                for (int i = 0; i < mData.length; i++) {
                    if (!mData[i].equals(otherData[i])) {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        } else {
            throw new IllegalArgumentException();
        }

        return 0;
    }
}
