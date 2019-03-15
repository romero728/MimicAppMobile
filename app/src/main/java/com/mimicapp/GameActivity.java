package com.mimicapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {
    SharedPreferences spGame;
    SharedPreferences.Editor editorGame;

    TextView nameTeam, currentWord, time;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        spGame = getSharedPreferences("settingsNewGame", Context.MODE_PRIVATE);

        nameTeam = findViewById(R.id.tvGNameTeam);
        currentWord = findViewById(R.id.tvGWord);
        time = findViewById(R.id.tvGTime);

        countDownTimer = new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long l) {
                if (l <= 6000)  {
                    time.setTextColor(Color.rgb(255, 68, 68));
                } else {
                    time.setTextColor(Color.rgb(24, 134, 255));
                }

                time.setText(getCorrectTimer(true, l) + ":" + getCorrectTimer(false, l));
            }

            @Override
            public void onFinish() {
                Toast.makeText(GameActivity.this, "C'est fini", Toast.LENGTH_SHORT).show();
            }
        };

        countDownTimer.start();
    }

    private String getCorrectTimer(boolean isMin, long l) {
        String aux;
        int constCalendar = isMin ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(l);
        aux = c.get(constCalendar) < 10 ? "0" + c.get(constCalendar) : "" + c.get(constCalendar);

        return aux;
    }

    @Override
    public void onBackPressed() {

    }
}
