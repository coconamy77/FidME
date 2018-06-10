package com.example.ds.fid_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MemoNewActivity extends AppCompatActivity {

    EditText newDate, newName, newMemo;
    Button btnCancel;
    ImageView[] memoRating=new ImageView[5];
    SQLiteHelper dbHelper;
    int rating=-1;

    String date, name, memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_new);


        Log.d("newMemo"," on new memo");

        dbHelper = new SQLiteHelper(this);
        newDate = (EditText)findViewById(R.id.memoDate);
        newName = (EditText)findViewById(R.id.memoName);
        newMemo = (EditText)findViewById(R.id.memoText);
        btnCancel = (Button)findViewById(R.id.btnDelete);
        btnCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });



        memoRating[0] = (ImageView) findViewById(R.id.star1);
        memoRating[1] = (ImageView) findViewById(R.id.star2);
        memoRating[2] = (ImageView) findViewById(R.id.star3);
        memoRating[3] = (ImageView) findViewById(R.id.star4);
        memoRating[4] = (ImageView) findViewById(R.id.star5);

        memoRating[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeStar(0);
            }
        });

        memoRating[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeStar(1);
            }
        });

        memoRating[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStar(2);
            }
        });

        memoRating[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStar(3);
            }
        });

        memoRating[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStar(4);
            }
        });



    }

    private void changeStar(int rate) {
        rating = rate;

        for ( ImageView img : memoRating){
            img.setImageResource(R.drawable.star);
        }

        for (int i=0; i<=rate;i++){
            memoRating[i].setImageResource(R.drawable.star_black);
        }

    }




    public void onBtnSave(View view) {

        date = newDate.getText().toString();
        name = newName.getText().toString();
        memo = newMemo.getText().toString();

        if (name.isEmpty()|date.isEmpty()|memo.isEmpty()|rating==-1){
            Toast.makeText(getApplicationContext(),"항목을 모두 입력해주세요.", Toast.LENGTH_LONG).show();
        }


        else {
            dbHelper.addData("MEMO", name, memo, "", String.valueOf(rating));
        Log.d("sql","go add");
            dbHelper.addData("MEMO", "누들아한타이", "맛있는 곳!:)", "", "3");

            Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MemoActivity.class);


            startActivity(intent);
            finish();
       }

    }


}
