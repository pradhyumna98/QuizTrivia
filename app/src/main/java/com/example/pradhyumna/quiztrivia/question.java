package com.example.pradhyumna.quiztrivia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class question extends AppCompatActivity {
    ProgressBar pbar;
    MyCountdownTimer mycount;
    RadioButton rdb;
    RadioGroup rdg;
    Button btnsub , btnchk;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        rdg = findViewById(R.id.radioGroup);
        //btnchk = findViewById(R.id.btnchk);
        btnsub = findViewById(R.id.btnsub);
        openHelper = new QuestionMaster(this);

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectid = rdg.getCheckedRadioButtonId();
                rdb = findViewById(selectid);
                String text = rdb.getText().toString();
                db = openHelper.getWritableDatabase();
                db = openHelper.getReadableDatabase();
                String qry = "SELECT "+QuestionMaster.COL_ANS_2+ " FROM "+QuestionMaster.TABLE_ANS+"";
                cursor = db.rawQuery(qry , null);
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                    }
                    else{
                        ContentValues cvs = new ContentValues();
                        cvs.put(QuestionMaster.COL_ANS_2 , "Mercury");
                        long i = db.insert(QuestionMaster.TABLE_ANS , null , cvs);
                    }
                }
                insertdata(text);
                cursor = db.rawQuery("SELECT " + QuestionMaster.TABLE_ANS + "." + QuestionMaster.COL_ANS_2 + "," + QuestionMaster.TABLE_SA + "." + QuestionMaster.COL_SA_2 +" FROM " + QuestionMaster.TABLE_ANS + 
                        " INNER JOIN (SELECT " +QuestionMaster.COL_SA_2+ " FROM "
                        +QuestionMaster.TABLE_SA+ " ORDER BY ID DESC LIMIT 1)"
                        +QuestionMaster.TABLE_SA+ " ON " +QuestionMaster.TABLE_SA+ "."
                        +QuestionMaster.COL_SA_2+ "=" +QuestionMaster.TABLE_ANS+ "."
                        +QuestionMaster.COL_ANS_2+"", null);
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        ContentValues cvs = new ContentValues();
                        cvs.put(QuestionMaster.COL_COM_2 , "CORRECT");
                        long dd = db.insert(QuestionMaster.TABLE_COM , null , cvs);
                        Toast.makeText(getApplicationContext() , "Submitted", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        ContentValues cv = new ContentValues();
                        cv.put(QuestionMaster.COL_COM_2 , "INCORRECT");
                        long dd1 = db.insert(QuestionMaster.TABLE_COM , null , cv);
                    }
                }
                Intent iq = new Intent(question.this , question1.class);
                startActivity(iq);
            }
        });

      /* btnchk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getReadableDatabase();
                String check = "SELECT " +QuestionMaster.COL_COM_2+ " FROM " +QuestionMaster.TABLE_COM+ " ORDER BY ID DESC LIMIT 1";
                cursor = db.rawQuery(check , null);
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        String vall = cursor.getString(cursor.getColumnIndex("value"));

                    }
                }
            }
        });*/

        pbar = findViewById(R.id.progressbar);

        pbar.setProgress(100);
        mycount = new MyCountdownTimer(10000 , 100);
        mycount.start();

    }


    class MyCountdownTimer extends CountDownTimer {

        public MyCountdownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            int progress = (int) (l / 100);
            pbar.setProgress(progress);
        }

        @Override
        public void onFinish() {
        pbar.setProgress(0);
            Toast.makeText(getApplicationContext() , "Time Up" , Toast.LENGTH_SHORT).show();
            Intent next = new Intent(question.this , question1.class);
            startActivity(next);
        }
    }
    public  void insertdata(String value){
        ContentValues cv = new ContentValues();
        cv.put(QuestionMaster.COL_SA_2 , value);
        long id = db.insert(QuestionMaster.TABLE_SA , null , cv);
    }
}