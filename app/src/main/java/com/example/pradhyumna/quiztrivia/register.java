package com.example.pradhyumna.quiztrivia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText useret1 , passet1;
Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper = new DatabaseHelper(this);
        useret1 = findViewById(R.id.useret1);
        passet1 = findViewById(R.id.passet1);
        reg = findViewById(R.id.regbt);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();
                String Username = useret1.getText().toString();
                String Password = passet1.getText().toString();
                insertdata(Username , Password);
                Toast.makeText(getApplicationContext() , "Registered Successfully" , Toast.LENGTH_LONG).show();
                Intent i = new Intent(register.this , userpage.class);
                startActivity(i);
            }
        });
    }
    public void insertdata(String username , String password){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2 , username);
        contentValues.put(DatabaseHelper.COL_3 , password);
        long id = db.insert(DatabaseHelper.TABLE_NAME , null , contentValues);

    }
}
