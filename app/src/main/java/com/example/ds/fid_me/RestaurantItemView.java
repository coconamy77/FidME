package com.example.ds.fid_me;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.jar.Attributes;

/**
 * Created by DS on 2018-05-25.
 */

public class RestaurantItemView extends LinearLayout {

    TextView restName;
    ImageView restStar,memoBtn, mapBtn;



    public RestaurantItemView(Context context) {
        super(context);
        init(context);
    }

    public RestaurantItemView(Context context, AttributeSet attrs){
        super(  context, attrs);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.restaurants, this, true);

        restName = (TextView) findViewById(R.id.restName);
        restStar = (ImageView) findViewById(R.id.restStar);
        memoBtn = (ImageView)findViewById(R.id.memoBtn);
        mapBtn = (ImageView)findViewById(R.id.mapBtn);

        memoBtn.setImageResource(R.drawable.memo);
        mapBtn.setImageResource(R.drawable.map);
    }

    public void setName(String name){
        restName.setText(name);
    }

    public void setRestStar(boolean star){
        if (!star) restStar.setImageResource(R.drawable.star_black);
        else restStar.setImageResource(R.drawable.star);
    }


}
