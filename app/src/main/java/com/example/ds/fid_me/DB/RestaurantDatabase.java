package com.example.ds.fid_me;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DS on 2018-06-01.
 */

public class RestaurantDatabase {

    public static final String TAG = "RestaurantDatabase";

    /**
     * 싱글톤 인스턴스
     */
    private static RestaurantDatabase database;


    public static String TABLE_MEMO = "MEMO";


    public static String TABLE_PHOTO = "PHOTO";


    public static String TABLE_HISTORY = "HISTORY";


    /**
     * version
     */
    public static int DATABASE_VERSION = 1;


    /**
     * Helper class defined
     */
    private DatabaseHelper dbHelper;

    /**
     * SQLiteDatabase 인스턴스
     */
    private SQLiteDatabase db;

    /**
     * 컨텍스트 객체
     */
    private Context context;

    /**
     * 생성자
     */
    private RestaurantDatabase(Context context) {
        this.context = context;
    }

    /**
     * 인스턴스 가져오기
     */
    public static RestaurantDatabase getInstance(Context context) {
        if (database == null) {
            database = new RestaurantDatabase(context);
        }

        return database;
    }


    /**
     * 데이터베이스 열기
     */
    public boolean open() {

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    /**
     * 데이터베이스 닫기
     */
    public void close() {

        db.close();

        database = null;
    }

    public Cursor rawQuery(String SQL) {

        Cursor c1 = null;
        try {
            c1 = db.rawQuery(SQL, null);

        } catch(Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
        }

        return c1;
    }


    //??
    public boolean execSQL(String SQL) {

        try {
            db.execSQL(SQL);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
            return false;
        }

        return true;
    }








    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, BasicInfo.DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_SQL = "create table if not exists "+TABLE_HISTORY+"("
                    +" _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    +" INPUT_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    +" REST_NAME TEXT, "
                    +" REST_LOCATION TEXT, "
                    +" ID_MEMO INTEGER, "
                    +" STAR TEXT DEFAULT 'FALSE' "+")";
            try {
                db.execSQL(CREATE_SQL);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }


        }

        @Override
        public void onOpen(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
