package com.example.ds.fid_me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    class RestaurantAdapter extends BaseAdapter{
        ArrayList<RestaurantItem> items = new ArrayList<RestaurantItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(RestaurantItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            RestaurantItemView view = new RestaurantItemView(getApplicationContext());
            RestaurantItem item = items.get(i);
            view.setName(item.getName());
            view.setRestStar(item.isStar());


            return view;
        }
    }
}
