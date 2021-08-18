package com.bliss.csc.dbtest4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBManager extends SQLiteOpenHelper {

    private static final String TABLE_COLUMN_AVERAGE = "average";
    private static final String TABLE_NAME = "Android";
    public static final String DB_NAME = "dbtest.db";
    public static final int DB_VERSION = 1;
    private SQLiteDatabase mDB;
    private String str;
    private int count_ID = 1;
    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE Android( _id INTEGER ," + "grade INTEGER, semester INTEGER, exam TEXT, average INTEGER);"); //name = 중간, 기말, price = 평균
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("INSERT INTO Android VALUES (null, 1, 2, '기말고사' , 98 );");
        db.execSQL("DROP TABLE IF EXISTS Android");
        onCreate(db);
    }

    @Override public void onOpen(SQLiteDatabase db) { super.onOpen(db); mDB = db; }

    public void insert(DBManager mDBManager,Integer grade, Integer semester, String exam, Integer average)
    {
        mDBManager.getWritableDatabase();
    mDB.execSQL("INSERT INTO Android VALUES (" + count_ID + ", "+ grade +", " +semester+", '"+ exam +"', "+ average +" );");
    mDBManager.close();
    String insert = grade + " ,"+ semester+" ," + exam + ", " + average;
    count_ID ++;
    Log.i("Insert DB : ", insert);
    }

    public void delete(DBManager mDBManager)
    {
        mDBManager.getWritableDatabase();
        mDB.execSQL("DELETE FROM Android");
        mDBManager.close();
        Log.i("Delete DB", "모든 테이블이 삭제 되었습니다.");
        count_ID = 1;
    }

    public void update(DBManager mDBManager, Integer id, Integer grade, Integer semester, String exam, Integer average)
    {
        mDBManager.getWritableDatabase();
        // mDB.execSQL("UPDATE Android SET price = "+ (count_ID+10) +" WHERE price = "+ (count_ID-1) +";");
        mDB.execSQL("INSERT OR REPLACE INTO Android(_id, grade, semester, exam, average) VALUES (" + id+ ", "+ grade +", " +semester+", '"+ exam +"', "+ average +" );");
        mDBManager.close();
        Log.v(null,"Update DB : "+count_ID);
    }

    public void all_average(DBManager mDBManager) {
        String query = "SELECT AVG("+ TABLE_COLUMN_AVERAGE +") FROM "+ TABLE_NAME;
        mDB.rawQuery(query, null);
    }

}
