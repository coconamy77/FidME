package com.example.ds.fid_me;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class MemoNewActivity extends AppCompatActivity {

    EditText newDate, newName, newMemo;
    Button btnCancel;
    ImageView[] memoRating=new ImageView[5];
    ImageView photo;
    SQLiteHelper dbHelper;
    int rating=-1;
    Intent intent;

    final int REQUEST_CODE_GALLERY = 999;

    String date, name, memo,mPhoto="";



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
        photo = (ImageView)findViewById(R.id.memoPhoto);
        intent=getIntent();

        photo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        MemoNewActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY
                );
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }

            else {
                Toast.makeText(getApplicationContext(), "permission이 없습니다.",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK &&data !=null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                photo.setImageBitmap(bitmap);
            } catch(FileNotFoundException e){
                    e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void changeStar(int rate) {
        rating = rate+1;

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
       // mPhoto = imageViewToString(photo);




        if (name.isEmpty()|date.isEmpty()|memo.isEmpty()|rating==-1){
            Toast.makeText(getApplicationContext(),"항목을 모두 입력해주세요.", Toast.LENGTH_LONG).show();
        }


        else {
            dbHelper.addData("MEMO", date, name, memo, mPhoto , String.valueOf(rating));
            Log.d("sql","go add");

            Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();

            Intent in = new Intent(getApplicationContext(), MemoActivity.class);
            startActivity(in);

           if (intent.getStringExtra("from").equals("history")){
               int restId = intent.getIntExtra("id",-1);
                Cursor data = dbHelper.getItemId("MEMO",name,date);
                int itemId = -1;
                while (data.moveToNext()){
                    itemId = data.getInt(0);
                }

                dbHelper.updateMemo(restId,itemId);
                finish();

           }

       }
    }

    private String imageViewToString(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();

        String stringPhoto = new String(byteArray,0,byteArray.length);
        return  stringPhoto;
    }


}
