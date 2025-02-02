package com.example.ds.fid_me;

/**
 * Created by DS on 2018-05-25.
 */

public class RestaurantItem {

    String name;
    boolean star;
    int memoId, restId;
    String address;


    public RestaurantItem(int restId,String name, String address, int memoId, boolean star ){
        this.restId = restId;
        this.name = name;
        this.star = star;
        this.memoId = memoId;
        this.address = address;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public int getMemoId() {
        return memoId;
    }

    public void setMemoId(int memoId) {
        this.memoId = memoId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
