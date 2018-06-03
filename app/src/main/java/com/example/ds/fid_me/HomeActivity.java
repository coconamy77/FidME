package com.example.ds.fid_me;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    Intent intent;
    private static final String TAG = "HomeActivity";
    SQLiteHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onRestClicked(View view) {
        // 상세 검색
        //Intent intent = new Intent(getApplicationContext(), )
    }

    public void onBtnGo(View view) {
        //구글 지도 길찾기 이동
    }

    public void onBtnRefresh(View view) {
        intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);

    }

    public void onBtnMap(View view) {
        intent = new Intent(getApplicationContext(),MapActivity.class);
        startActivity(intent);
    }

    public void onBtnBM(View view) {
        intent = new Intent(getApplicationContext(),BookMarkActivity.class);
        startActivity(intent);
    }

    public void onBtnMemo(View view) {
        intent = new Intent(getApplicationContext(),MemoActivity.class);
        startActivity(intent);
    }

    public void onBtnHistory(View view) {
        intent = new Intent(getApplicationContext(),HistoryActivity.class);
        startActivity(intent);
    }

    public void onBtnRecom(View view) {
        intent = new Intent(getApplicationContext(),RecommandActivity.class);
        startActivity(intent);
    }
}
