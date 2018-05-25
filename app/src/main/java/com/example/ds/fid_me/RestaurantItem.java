package com.example.ds.fid_me;

/**
 * Created by DS on 2018-05-25.
 */

public class RestaurantItem {

    String name;
    boolean star;
    boolean memo;
    String address;

    public RestaurantItem(String name, boolean star, boolean memo, String address ){
        this.name = name;
        this.star = star;
        this.memo = memo;
        this.address = address;
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

    public boolean isMemo() {
        return memo;
    }

    public void setMemo(boolean memo) {
        this.memo = memo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
