package com.example.ds.fid_me;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/**
 * Created by DS on 2018-06-01.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHelper",HISTORY_TABLE="history_Table";
    private static final String DATABASENAME = "Restaurant_db";
    private static final String COL1  = "ID";
    private static final String COL2 = "INPUT_DATE";
    private static final String COL3 = "NAME";
    private static final String COL4= "LOCATION";
    private static final String COL5 = "ID_MEMO";
    private static final String COL6 = "STAR";


    public SQLiteHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }


    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void addData(String tableName, String col3, String col4, String col5, String col6 ){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+tableName+" VALUES (NULL, NULL, ?,?,?,? )";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(3, col3 );
        statement.bindString(4, col4);
        statement.bindString(5,col5);
        statement.bindString(6,col6);

        statement.executeInsert();
    }


    public Cursor getData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ tableName;
        Cursor data = db.rawQuery(query, null);
        return  data;
    }


    public Cursor getItemId(String tableName, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL1+" FROM "+ tableName+ "WHERE "+ COL3+"= '"+ name+"' ";
        Cursor data = db.rawQuery(query, null);
        return  data;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String createTable = "create table if not exists "+HISTORY_TABLE+"("
                    +" ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    +COL2+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    +COL3+" TEXT, "
                    +COL4+" TEXT, "
                    +COL5+" INTEGER, "
                    +COL6+" TEXT DEFAULT 'FALSE')";
            db.execSQL(createTable);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP  TABLE IF EXISTS "+HISTORY_TABLE);
        onCreate(db);

    }


}
