package com.bliss.csc.dbtest4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbtest.db";
    public static final int DB_VERSION = 1;
    private  SQLiteDatabase mDB;
    private String str;
    private int temp;
    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE Android( _id INTEGER PRIMARY KEY AUTOINCREMENT," + "grade INTEGER, semester INTEGER, name TEXT, price INTEGER);"); //name = 중간, 기말, price = 평균
        db.execSQL("INSERT INTO Android VALUES (null, 1, 2, '중간고사' , 90 );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("INSERT INTO Android VALUES (null, 1, 2, '기말고사' , 98 );");

    }

    @Override public void onOpen(SQLiteDatabase db) { super.onOpen(db); mDB = db; }

    public void insert(DBManager mDBManager,Integer grade, Integer semester, String name, Integer average)
    {
        mDBManager.getWritableDatabase();
    mDB.execSQL("INSERT INTO Android VALUES (null, "+ grade +", " +semester+", '"+ name +"', "+ average +" );");
    mDBManager.close();
    String insert = grade + " ,"+ semester+" ," + name + ", " + average;
    Log.i("Insert DB : ", insert);
    }

    public void delete(DBManager mDBManager)
    {
        mDBManager.getWritableDatabase();
        mDB.execSQL("DELETE FROM Android");
        mDBManager.close();
        Log.i("Delete DB", "모든 테이블이 삭제 되었습니다.");
    }

    public void update(DBManager mDBManager)
    { mDBManager.getWritableDatabase();
    mDB.execSQL("UPDATE Android SET price = "+ (temp+10) +" WHERE price = "+ (temp-1) +";");
    mDBManager.close();
    Log.v(null,"Update DB : "+temp);
    }

    public void select(DBManager mDBManager) {

    }

}
