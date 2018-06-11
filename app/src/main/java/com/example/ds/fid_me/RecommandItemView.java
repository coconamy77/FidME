package com.example.ds.fid_me;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.jar.Attributes;

public class RecommandItemView extends LinearLayout {

    TextView textname, textaddress;

    public RecommandItemView(Context context) {
        super(context);
        init(context);
    }

    public RecommandItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.restaurants, this, true);

        textname = (TextView) findViewById(R.id.textname);
      //  textaddress = (TextView) findViewById(R.id.textaddress);
    }

    public void setName(String name){
        textname.setText(name);
    }

    public void setAddress(String address){
        textaddress.setText(address);
    }

}
