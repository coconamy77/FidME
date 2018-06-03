package com.example.ds.fid_me;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FindItemView extends LinearLayout {

    TextView foodName;

    public FindItemView(Context context) {
        super(context);
        init(context);
    }

    public FindItemView(Context context, AttributeSet attrs){
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.find_listitem, this, true);

        TextView foodName = (TextView)findViewById(R.id.foodName);

    }

    public void setName(String name) {
        foodName.setText(name);
    }

}
