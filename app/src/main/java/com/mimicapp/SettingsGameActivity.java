package com.mimicapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SettingsGameActivity extends AppCompatActivity {
    String categorySelected, timeSelected;

    SharedPreferences spSettingsGame;
    SharedPreferences.Editor editorSettingsGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_game);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        spSettingsGame = getSharedPreferences("settingsNewGame", Context.MODE_PRIVATE);

        Spinner sCategory = findViewById(R.id.sSGCategory);
        Spinner sTime = findViewById(R.id.sSGTime);

        Button btnNext = findViewById(R.id.btnSGBeginPlay);

        List<String> myList = new ArrayList<String>(Arrays.asList(getCategories()));

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, myList);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapterCategory);

        sCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        List<String> myTime = new ArrayList<String>(Arrays.asList(getTime()));

        ArrayAdapter<String> adapterTime = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, myTime);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTime.setAdapter(adapterTime);

        sTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeSelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editorSettingsGame = spSettingsGame.edit();
                editorSettingsGame.putString("categoryKey", categorySelected);
                editorSettingsGame.putString("timeKey", timeSelected);
                editorSettingsGame.apply();
            }
        });
    }

    public String[] getCategories() {
        String listCategories = getIntent().getExtras().getString("listCategories");
        String splitter = Pattern.quote("|");
        String[] categoryArray = listCategories.split(splitter);
        categoryArray[0] = "Todas";

        return categoryArray;
    }

    public String[] getTime() {
        String[] timeArray = new String[]{
            "15 segundos",
            "30 segundos",
            "45 segundos",
            "1 minuto",
            "3 minutos",
            "5 minutos"
        };

        return timeArray;
    }
}
