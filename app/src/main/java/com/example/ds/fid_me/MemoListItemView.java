package com.example.ds.fid_me;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by DS on 2018-05-25.
 */

public class MemoListItemView extends LinearLayout{
    private ImageView memoPhoto;

    private TextView memoDate;

    private TextView memoName;

    private TextView memoText;

    private ImageView[] memoRating=new ImageView[5];

    Bitmap bitmap;



    public MemoListItemView(Context context) {
        super(context);
        init(context);
    }

    public MemoListItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.memo_listitem, this,  true);

        memoPhoto = (ImageView)findViewById(R.id.memoPhoto);

        memoDate = (TextView) findViewById(R.id.memoDate);

        memoName = (TextView) findViewById(R.id.memoName);

        memoText = (TextView) findViewById(R.id.memoText);

        memoRating[0] = (ImageView) findViewById(R.id.star1);
        memoRating[1] = (ImageView) findViewById(R.id.star2);
        memoRating[2] = (ImageView) findViewById(R.id.star3);
        memoRating[3] = (ImageView) findViewById(R.id.star4);
        memoRating[4] = (ImageView) findViewById(R.id.star5);
    }

    public void setContents(int index, String data) {
        Log.d("memolist","set contents");
        if (index == 0) {
            memoDate.setText(data);
        } else if (index == 1) {
            memoName.setText(data);
        } else if (index == 2) {
            memoText.setText(data);
        } else if (index == 3) {
            if (data == null || data.equals("-1") || data.equals("")) {
                memoPhoto.setImageResource(R.drawable.image);
            } else {
                if (bitmap != null) {
                    bitmap.recycle();
                }

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                //여기 오류 나요 ㅠㅠ
                //bitmap = BitmapFactory.decodeFile(BasicInfo.FOLDER_PHOTO + data, options);

                memoPhoto.setImageBitmap(bitmap);
            }
        } else if(index==4){
            Log.d("star",data);
            for (int i=0;i<Integer.parseInt(data);i++) {
                Log.d("real star",i+"");
                memoRating[i].setImageResource(R.drawable.star_black);
                Log.d("change star",i+"change");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }


    }
