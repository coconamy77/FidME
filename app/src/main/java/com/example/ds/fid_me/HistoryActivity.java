package com.example.ds.fid_me;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView listView;
    RestaurantAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView)findViewById(R.id.historyListView);

        String title = "";
        String address = "";

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            title = "error";
        }
        else {

            title = extras.getString("title");
            address = extras.getString("address");
        }

        adapter = new RestaurantAdapter();

      //  adapter.addItem(new RestaurantItem("누들아한타이",true,true,"대한민국 서울특별시 쌍문동 139"));
     //   adapter.addItem(new RestaurantItem("일락",false,false, "대한민국 서울특별시 쌍문동 138"));

        adapter.addItem(new RestaurantItem(title, true, true, address));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RestaurantItem item = (RestaurantItem) adapter.getItem(i);
                //구글 상세 정보 조회 이동
            }
        });


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
