package com.example.ds.fid_me;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;


// 사진 저장하기 https://www.youtube.com/watch?v=4bU9cZsJRLI 6분 50초쯤?

public class MemoActivity extends AppCompatActivity {

    MemoListAdapter adapter;
    ListView mMemoListView;
    int mMemoCount = 0;
    ImageView btnDel;
    boolean isDel = false;


    SQLiteHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        Log.d("sql", "get dbHelper ");

        dbHelper =  new SQLiteHelper(this);


        mMemoListView = (ListView)findViewById(R.id.memoList);
        adapter = new MemoListAdapter(this);
        btnDel = (ImageView) findViewById(R.id.delMemoBtn) ;
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isDel = true;
                btnDel.setImageResource(R.drawable.check);

            }
        });


        loadMemoListData();



        mMemoListView.setAdapter(adapter);



        mMemoListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3){

                if (isDel){
                    MemoListItem item = (MemoListItem)adapter.getItem(position);
                    dbHelper.delData("MEMO",item.getId()+"");
                    adapter.notifyDataSetChanged();
                    isDel = false;
                    btnDel.setImageResource(R.drawable.trash);

                }

            }

        });


        ImageView newMemoBtn = (ImageView) findViewById(R.id.newMemoBtn);


        newMemoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MemoActivity","clicked");

                Log.d("newMemo"," go new memo");
                Intent intent = new Intent(getApplicationContext(),MemoNewActivity.class);

                startActivity(intent);

            }
        });



    }

    private int loadMemoListData() {

       /* MemoListItem aItem = new MemoListItem("1", "2013-06-10 10:20","누들아한타이",
                "맛있는 곳!",null,4
                );

        Log.d("memolist", "first memo made, go adapter to add the item ");

        adapter.addItem(aItem);

        adapter.notifyDataSetChanged();

*/

        int recordCount = -1;

        Cursor data = dbHelper.getData("MEMO");

        while (data.moveToNext()) {

            String memoId = data.getString(0);
            String memoDate = data.getString(1);
            String memoName = data.getString(2);
            String memoText = data.getString(3);
            String uri_photo = data.getString(4);
            String memoRating = data.getString(5);


            adapter.addItem(new MemoListItem(memoId,memoDate,memoName, memoText,uri_photo, Integer.parseInt( memoRating)));


        }


        adapter.notifyDataSetChanged();


        return recordCount;
    }



}
