package com.example.ds.fid_me;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;


// 사진 저장하기 https://www.youtube.com/watch?v=4bU9cZsJRLI 6분 50초쯤?

public class MemoActivity extends AppCompatActivity {

    MemoListAdapter adapter;
    ListView mMemoListView;
    int mMemoCount = 0;


    SQLiteHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        Log.d("sql", "get dbHelper ");

        dbHelper =  new SQLiteHelper(this);


        mMemoListView = (ListView)findViewById(R.id.memoList);
        adapter = new MemoListAdapter(this);


        loadMemoListData();



        mMemoListView.setAdapter(adapter);



        mMemoListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3){
                viewMemo(position);
            }

        });


        ImageView newMemoBtn = (ImageView) findViewById(R.id.newMemoBtn);


        newMemoBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MemoActivity","clicked");
            }
        });

        Log.d("memolist", "go load memo list data");
        loadMemoListData();

    }

    private void loadMemoListData() {

        MemoListItem aItem = new MemoListItem("1", "2013-06-10 10:20","누들아한타이",
                "맛있는 곳!",null,4
                );

        Log.d("memolist", "first memo made, go adapter to add the item ");

        adapter.addItem(aItem);

        adapter.notifyDataSetChanged();

/*

        int recordCount = -1;

        Cursor data = dbHelper.getData("MEMO");
        Cursor data_photo =  dbHelper.getData("PHOTO");

        while (data.moveToNext()) {

            String memoId = data.getString(0);
            String memoDate = data.getString(1);
            String memoName = data.getString(2);
            String memoText = data.getString(3);
            String id_photo = data.getString(4);
            String uri_photo = data_photo.getString(1);
            String memoDate = data.getString(1);


            String location = data.getString(3);
            int memoId = Integer.parseInt(data.getString(4));

            Boolean star = Boolean.parseBoolean(data.getString(5));

            adapter.addItem(new RestaurantItem(restName,location,memoId,star));


        }


        adapter.notifyDataSetChanged();


        return recordCount;*/
    }

    private void viewMemo(int position) {

    }
}
