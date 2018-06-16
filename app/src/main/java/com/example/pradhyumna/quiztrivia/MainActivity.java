package com.example.pradhyumna.quiztrivia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView img;
Handler mhandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.logo);
        Animation ani = AnimationUtils.loadAnimation(MainActivity.this , R.anim.logo);
        img.startAnimation(ani);

        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(MainActivity.this , userpage.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}
