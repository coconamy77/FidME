package com.example.ds.fid_me;

/**
 * Created by DS on 2018-05-25.
 */

public class RestaurantItem {

    String name;
    boolean star;

    public RestaurantItem(String name, boolean star ){
        this.name = name;
        this.star = star;
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
}
