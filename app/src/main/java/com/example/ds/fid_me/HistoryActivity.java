package com.example.ds.fid_me;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity{

    private ListView listView;
    RestaurantAdapter adapter;
    int historyCount=0;
    public static final String TAG = "HistoryActivity";
    String restName, location ;

     SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView)findViewById(R.id.historyListView);

        dbHelper = new SQLiteHelper(this);
        Log.d("sql", "after helper");



        adapter = new RestaurantAdapter();


        loadHistoryListData();



        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RestaurantItem item = (RestaurantItem) adapter.getItem(i);
                //구글 상세 정보 조회 이동
            }
        });

    }






    /**
     * 리스트 데이터 로딩
     */

    public int loadHistoryListData() {

        int recordCount = -1;

        Cursor data = dbHelper.getData("HISTORY");


        while (data.moveToNext()) {

            String restName = data.getString(2);

            String location = data.getString(3);
            int memoId = Integer.parseInt(data.getString(4));

            Boolean star = Boolean.parseBoolean(data.getString(5));

            adapter.addItem(new RestaurantItem(restName,location,memoId,star));


        }


        adapter.notifyDataSetChanged();


        return recordCount;
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

        public void clear() {
            items.clear();
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
