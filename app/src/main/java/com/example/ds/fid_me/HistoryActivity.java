package com.example.ds.fid_me;

import android.app.Activity;
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
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity{

    ListView listView;
    RestaurantAdapter adapter;
    int historyCount=0;
    public static final String TAG = "HistoryActivity";

    /**
     * 데이터베이스 인스턴스
     */
    public static RestaurantDatabase rDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView)findViewById(R.id.historyListView);


        adapter = new RestaurantAdapter();

      //  adapter.addItem(new RestaurantItem(,true,true,"대한민국 서울특별시 쌍문동 139"));
     //   adapter.addItem(new RestaurantItem("일락",false,false, "대한민국 서울특별시 쌍문동 138"));


        adapter.addItem(new RestaurantItem("누들아한타이", "대한민국 서울특별시 쌍문동 139", -1, false));


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RestaurantItem item = (RestaurantItem) adapter.getItem(i);
                //구글 상세 정보 조회 이동
            }
        });

    }

    protected void onStart() {

        // 데이터베이스 열기
        openDatabase();

        // 메모 데이터 로딩
        loadHistoryListData();


        super.onStart();
    }





    /**
     * 데이터베이스 열기 (데이터베이스가 없을 때는 만들기)
     */
    public void openDatabase() {
        // open database
        if (rDatabase != null) {
            rDatabase.close();
            rDatabase = null;
        }

        rDatabase = RestaurantDatabase.getInstance(this);
        boolean isOpen = rDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Memo database is open.");
        } else {
            Log.d(TAG, "Memo database is not open.");
        }
    }


    /**
     * 리스트 데이터 로딩
     */

    public int loadHistoryListData() {
        String SQL = "select _id, INPUT_DATE, REST_NAME, LOCATION, ID_MEMO, STAR from HISTORY order by INPUT_DATE desc";

        int recordCount = -1;

        if (HistoryActivity.rDatabase != null) {
            Cursor outCursor = HistoryActivity.rDatabase.rawQuery(SQL);

            recordCount = outCursor.getCount();
            Log.d(TAG, "cursor count : " + recordCount + "\n");

            adapter.clear();
            Resources res = getResources();

            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();

                String restName = outCursor.getString(2);

                String location = outCursor.getString(3);
                int memoId = Integer.parseInt(outCursor.getString(4));

                Boolean star = Boolean.parseBoolean(outCursor.getString(5));

                adapter.addItem(new RestaurantItem(restName,location,memoId,star));


            }

            outCursor.close();

            adapter.notifyDataSetChanged();

        }
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
