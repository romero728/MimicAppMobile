package com.mimicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SettingsGameActivity extends AppCompatActivity {
    ConstraintLayout.LayoutParams params;

    String categorySelected, timeSelected, kindCategorySelected;

    Spinner sKindCategory = null;
    Spinner sCategory = null;
    Spinner sTime = null;

    List<String> listCategories;

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

        final TextView tvLabel3 = findViewById(R.id.tvSGLabel3);
        final TextView tvLabel4 = findViewById(R.id.tvSGLabel4);

        sKindCategory = findViewById(R.id.sSGKindCategory);
        sCategory = findViewById(R.id.sSGCategory);
        sTime = findViewById(R.id.sSGTime);

        Button btnNext = findViewById(R.id.btnSGBeginPlay);

        ArrayAdapter<CharSequence> adapterKind = ArrayAdapter.createFromResource(this,
                R.array.kind_categories, android.R.layout.simple_spinner_item);
        adapterKind.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sKindCategory.setAdapter(adapterKind);

        sKindCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        kindCategorySelected = "all";
                        hideCategories(tvLabel3, tvLabel4, sCategory);

                        break;
                    case 1:
                        kindCategorySelected = "general";
                        getGeneralCategories();
                        showCategories(tvLabel3, tvLabel4, sCategory);

                        break;
                    case 2:
                        kindCategorySelected = "custom";
                        getCustomCategories();
                        showCategories(tvLabel3, tvLabel4, sCategory);

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterTime = ArrayAdapter.createFromResource(this,
                R.array.time_options, android.R.layout.simple_spinner_item);
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
                goToGame();
            }
        });
    }

    public void getGeneralCategories() {
        listCategories = new ArrayList<>(Arrays.asList(getCategories(1)));

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listCategories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapterCategory);
    }

    public void getCustomCategories() {
        listCategories = new ArrayList<>(Arrays.asList(getCategories(2)));

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listCategories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapterCategory);
    }

    public String[] getCategories(int k) {
        String splitKind = Pattern.quote("/");
        String splitCategory = Pattern.quote("|");

        String listCategories = getIntent().getExtras().getString("listCategories");

        String[] allCategories = listCategories.split(splitKind);

        String[] generalCategories = allCategories[0].split(splitCategory);
        String[] customCategories = allCategories[1].split(splitCategory);

        generalCategories[0] = "Todas";
        customCategories[0] = "Todas";

        if (k == 1) {
            return generalCategories;
        } else {
            return customCategories;
        }
    }

    public void showCategories(TextView tv3, TextView tv4, Spinner s) {
        tv3.setVisibility(View.VISIBLE);
        s.setVisibility(View.VISIBLE);

        params = (ConstraintLayout.LayoutParams) tv4.getLayoutParams();
        params.topToBottom = R.id.sSGCategory;
    }

    public void hideCategories(TextView tv3, TextView tv4, Spinner s) {
        tv3.setVisibility(View.INVISIBLE);
        s.setVisibility(View.INVISIBLE);

        params = (ConstraintLayout.LayoutParams) tv4.getLayoutParams();
        params.topToBottom = R.id.sSGKindCategory;
    }

    public void goToGame() {
        editorSettingsGame = spSettingsGame.edit();
        editorSettingsGame.putString("kindCategoryKey", kindCategorySelected);
        editorSettingsGame.putString("categoryKey", categorySelected);
        editorSettingsGame.putString("timeKey", timeSelected);
        editorSettingsGame.apply();

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
