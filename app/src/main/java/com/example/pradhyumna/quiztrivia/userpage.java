package com.example.pradhyumna.quiztrivia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userpage extends AppCompatActivity {
Button sign , login;
EditText useret , passet;
Cursor cursor;
SQLiteDatabase db;
SQLiteOpenHelper openhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        openhelper = new DatabaseHelper(this);
        db = openhelper.getReadableDatabase();
        login = findViewById(R.id.logbt);
        sign = findViewById(R.id.signbt);
        useret = findViewById(R.id.useret);
        passet = findViewById(R.id.passet);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ip= new Intent(userpage.this , register.class);
                startActivity(ip);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = useret.getText().toString();
                String pass = passet.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME +" WHERE "+ DatabaseHelper.COL_2 + " =? AND " + DatabaseHelper.COL_3 +" =? " ,new String[]{user ,pass});
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext() , "Login Success" , Toast.LENGTH_SHORT).show();

                        Intent il = new Intent(userpage.this , start.class);
                        startActivity(il);}


                    else{
                        Toast.makeText(getApplicationContext() , "Error" , Toast.LENGTH_SHORT).show();
                        Intent il1 = new Intent(userpage.this , userpage.class);
                        startActivity(il1);
                    }
                }

            }

        });
    }


}
