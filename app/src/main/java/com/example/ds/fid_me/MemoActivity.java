package com.example.ds.fid_me;

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

    MemoListAdapter mMemoListAdapter;
    ListView mMemoListView;
    int mMemoCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        mMemoListView = (ListView)findViewById(R.id.memoList);
        mMemoListAdapter = new MemoListAdapter(this);
        mMemoListView.setAdapter(mMemoListAdapter);
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
        
        
        loadMemoListData();
    }

    private void loadMemoListData() {
        MemoListItem aItem = new MemoListItem("1", "2013-06-10 10:20","누들아한타이",
                "맛있는 곳!",null, null,2
                );

        mMemoListAdapter.addItem(aItem);
        mMemoListAdapter.notifyDataSetChanged();
    }

    private void viewMemo(int position) {

    }
}
