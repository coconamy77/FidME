package com.example.ds.fid_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindActivity extends AppCompatActivity implements View.OnClickListener {

    private Button foodbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        foodbtn=(Button)findViewById(R.id.btn);
        foodbtn.setOnClickListener(this);

    }

    // 사용자가 최종적으로 선택한 버튼(음식이름)의 값을 받아와서, RecommandActivity로 넘겨준다.
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RecommandActivity.class);
        intent.putExtra("foodbtn",foodbtn.getText().toString());
        startActivity(intent);
        finish();
    }
}
