package com.example.ds.fid_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //왜 여기 안나와..ㅠㅠㅠ
        goHome();


    }

    private void goHome() {
        try{
            Thread.sleep(2000);
        }catch (Exception e){};

        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);

    }
}
