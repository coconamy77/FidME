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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity{

    private ListView listView;
    RestaurantAdapter adapter;
    int historyCount=0;
    public static final String TAG = "HistoryActivity";
    String restName, location ;
    private String tableName = "HISTORY";
    private static final int REQUEST_CODE_MEMO=101;



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



    }

    public void onBtnDel(View view) {
        dbHelper.delData(tableName,"");
        adapter.delAllItem();
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "삭제완료되었습니다.",Toast.LENGTH_LONG).show();
    }




    /**
     * 리스트 데이터 로딩
     */

    public int loadHistoryListData() {

        int recordCount = -1;

        Cursor data = dbHelper.getData("HISTORY");


        while (data.moveToNext()) {

            int restId = Integer.parseInt(data.getString(0));

            String restName = data.getString(2);

            String location = data.getString(3);
            int memoId = Integer.parseInt(data.getString(4));

            Boolean star = Boolean.parseBoolean(data.getString(5));

            adapter.addItem(new RestaurantItem(restId,restName,location,memoId,star));


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

        public void delAllItem(){
            items.clear();
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
            final RestaurantItem item = items.get(i);
            view.setName(item.getName());
            view.setRestStar(item.isStar());

            view.memoBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if (item.getMemoId()==-1){
                        Intent intent = new Intent(getApplicationContext(), MemoNewActivity.class);
                        intent.putExtra("id",item.getRestId());
                        intent.putExtra("from","history");
                        startActivityForResult(intent,REQUEST_CODE_MEMO);

                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
                        intent.putExtra("memoId", item.getMemoId());
                        startActivity(intent);


                    }
                }
            });

            view.mapBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), RecommandMapActivity.class);
                    intent.putExtra("name",restName);
                    intent.putExtra("address", location);

                    startActivity(intent);
                }
            });

            view.restStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setStar(!item.isStar());
                    dbHelper.updateStar(item.getRestId(),item.isStar()+"");
                    adapter.notifyDataSetChanged();
                }
            });


            return view;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_MEMO && requestCode==RESULT_OK){
           adapter.notifyDataSetChanged();

        }




    }
}
