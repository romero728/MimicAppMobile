package com.mimicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        TextView tvUserName = findViewById(R.id.tvUserName);
        Button btnNewGame = findViewById(R.id.btnNewGame);
        Button btnCategories = findViewById(R.id.btnCategories);

        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("nameKey", null);

        if (userName != null) {
            String[] split = userName.split(" ");
            userName = split[0];

            userName = "Hola " + userName + ", \n Selecciona una opción";
            tvUserName.setText(userName);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewGame();
            }
        });
    }

    public void goToNewGame() {
        Intent iNewGame = new Intent(this, NewGameActivity.class);
        startActivity(iNewGame);
    }
}
