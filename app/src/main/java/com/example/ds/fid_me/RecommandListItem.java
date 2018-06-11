package com.example.ds.fid_me;

import java.util.ArrayList;

public class RecommandListItem {

    String name;
    String address;

    public RecommandListItem(String name,String address ){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
