package com.example.pradhyumna.quiztrivia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class start extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;

    private int highscore;

    Button str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.str);

        str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(check1.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void updateHighscore(int score) {
        highscore = score;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }
    private void startQuiz() {
        Intent intent = new Intent(start.this, check1.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }
}
