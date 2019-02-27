package com.mimicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvUserName = (TextView) findViewById(R.id.tvUserName);

        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("nameKey", null);

        if (userName != null) {
            userName = "Hola " + userName;
            tvUserName.setText(userName);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
