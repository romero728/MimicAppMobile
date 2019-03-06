package com.mimicapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SettingsGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_game);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        Spinner sCategory = findViewById(R.id.sSGCategory);
        Spinner sTime = findViewById(R.id.sSGTime);

        List<String> myList = new ArrayList<String>(Arrays.asList(getCategories()));
        myList.remove(0);

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, myList);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapterCategory);

        List<String> myTime = new ArrayList<String>(Arrays.asList(getTime()));

        ArrayAdapter<String> adapterTime = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, myTime);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTime.setAdapter(adapterTime);
    }

    public String[] getCategories() {
        String listCategories = getIntent().getExtras().getString("listCategories");
        String splitter = Pattern.quote("|");
        String[] categoryArray = listCategories.split(splitter);

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
