package com.bliss.csc.dbtest4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DBManager mDBManager; // private SQLiteDatabase mDB;
    private SQLiteDatabase mDB;

    Button btn_insert,btn_delete,btn_update,btn_select;
    EditText grade, semester, average, id;
    ListView listView, listView2, listView3, listView4, listView_id;
    Cursor cursor;
    ArrayAdapter adapter, adapter2, adapter3, adapter4, adapter_id;
    RadioGroup examgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grade = (EditText)findViewById(R.id.grade);
        semester = (EditText)findViewById(R.id.semester);
        average = (EditText)findViewById(R.id.average);
        id = (EditText)findViewById(R.id.id_edt);

        btn_insert = (Button)findViewById(R.id.btn_insert);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_select = (Button)findViewById(R.id.btn_select);

        listView = (ListView)findViewById(R.id.listview);
        listView2 = (ListView)findViewById(R.id.listview2);
        listView3 = (ListView)findViewById(R.id.listview3);
        listView4 = (ListView)findViewById(R.id.listview4);
        listView_id = (ListView)findViewById(R.id.listview_id);


        examgroup = (RadioGroup)findViewById(R.id.examRgroup);

        mDBManager = new DBManager(this);
        mDBManager.getReadableDatabase();
        mDBManager.getWritableDatabase();
        mDBManager.close();


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Integer Grade = Integer.valueOf(grade.getText().toString());
                    Integer Semester = Integer.valueOf(semester.getText().toString());
                    Integer Average = Integer.valueOf(average.getText().toString());
                    String exam;
                    int rbt_id = ((RadioGroup)examgroup.findViewById(R.id.examRgroup)).getCheckedRadioButtonId();
                    if (rbt_id == 2131231195){ // ???????????? ?????? ?????? ???!
                        exam = "????????????";
                    }else {
                        exam = "????????????";
                    }
                    String rbt_result = String.valueOf(rbt_id);
                    Log.i("????????? ??????",rbt_result);
                    try {
                        mDBManager.insert(mDBManager, Grade, Semester, exam, Average);
                        grade.setText("");
                        semester.setText("");
                        average.setText("");
                    }catch (SQLiteConstraintException e){
                        Toast.makeText(getApplicationContext(),"?????? ????????? ??????????????????",Toast.LENGTH_SHORT).show();
                    }

                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"?????? ?????? ????????? ???????????????",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBManager.delete(mDBManager);
                Toast.makeText(getApplicationContext(),"?????? ???????????? ?????????????????????.",Toast.LENGTH_SHORT).show();
            }

        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Integer ID = Integer.valueOf(id.getText().toString());
                    Integer Grade = Integer.valueOf(grade.getText().toString());
                    Integer Semester = Integer.valueOf(semester.getText().toString());
                    Integer Average = Integer.valueOf(average.getText().toString());
                    String exam;
                    int rbt_id = ((RadioGroup)examgroup.findViewById(R.id.examRgroup)).getCheckedRadioButtonId();
                    if (rbt_id == 2131231195){ // ???????????? ?????? ?????? ???!
                        exam = "????????????";
                    }else {
                        exam = "????????????";
                    }
                    mDBManager.update(mDBManager, ID, Grade, Semester, exam , Average);


                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "???????????? ????????? ?????? ?????? ???????????????.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mDBManager.select(mDBManager);
                listUpdate();
            }
        });

    }

    public Cursor avg_average(){
        //String query = "SELECT AVG("+ TABLE_COLUMN_AVERAGE +") FROM "+ TABLE_NAME;

        mDB = mDBManager.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("SELECT AVG( average ) FROM Android;", null);
        startManagingCursor(cursor);
        cursor.close();
        return cursor;
        
    }

    public void listUpdate(){
        mDB = mDBManager.getReadableDatabase();
        cursor = mDB.rawQuery("SELECT * FROM Android",null);
        startManagingCursor(cursor);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter4 = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter_id = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()){
            adapter_id.add(cursor.getInt(0));
            adapter.add(cursor.getInt(1));
            adapter2.add(cursor.getInt(2));
            adapter3.add(cursor.getString(3));
            adapter4.add(cursor.getInt(4));
        }
        listView_id.setAdapter(adapter_id);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
        listView3.setAdapter(adapter3);
        listView4.setAdapter(adapter4);
        cursor.close();
    }

}