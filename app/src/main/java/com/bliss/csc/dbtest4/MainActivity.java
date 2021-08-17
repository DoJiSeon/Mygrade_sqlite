package com.bliss.csc.dbtest4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DBManager mDBManager; // private SQLiteDatabase mDB;
    private SQLiteDatabase mDB;

    Button btn_insert,btn_delete,btn_update,btn_select;
    EditText grade, semester, average;
    RadioGroup examgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grade = (EditText)findViewById(R.id.grade);
        semester = (EditText)findViewById(R.id.semester);
        average = (EditText)findViewById(R.id.average);

        btn_insert = (Button)findViewById(R.id.btn_insert);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_select = (Button)findViewById(R.id.btn_select);

        examgroup = (RadioGroup)findViewById(R.id.examRgroup);
        mDBManager = new DBManager(this);
        mDBManager.getReadableDatabase();
        mDBManager.getWritableDatabase();
        mDBManager.close();

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer Grade = Integer.valueOf(grade.getText().toString());
                Integer Semester = Integer.valueOf(semester.getText().toString());
                Integer Average = Integer.valueOf(average.getText().toString());
                String exam;
                int rbt_id = ((RadioGroup)examgroup.findViewById(R.id.examRgroup)).getCheckedRadioButtonId();
                if (rbt_id == 2131231195){ // 중간고사 버튼 클릭 시!
                    exam = "중간고사";
                }else {
                    exam = "기말고사";
                }
                String rbt_result = String.valueOf(rbt_id);
                Log.i("라디오 버튼",rbt_result);
                mDBManager.insert(mDBManager, Grade, Semester, exam, Average);

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBManager.delete(mDBManager);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBManager.update(mDBManager);
            }
        });

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBManager.select(mDBManager);
            }
        });

    }
}