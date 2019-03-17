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

import com.mimicapp.DBConnection.GetWords;

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

    SharedPreferences spSettingsGame, spUserData;
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
        spUserData = getSharedPreferences("userData", Context.MODE_PRIVATE);

        final TextView tvLabel3 = findViewById(R.id.tvSGLabel3);
        final TextView tvLabel4 = findViewById(R.id.tvSGLabel4);

        sKindCategory = findViewById(R.id.sSGKindCategory);
        sCategory = findViewById(R.id.sSGCategory);
        sTime = findViewById(R.id.sSGTime);

        Button btnNext = findViewById(R.id.btnSGBeginPlay);

        /*--- Obtener categorías */

        String splitKind = Pattern.quote("/");
        String splitCategory = Pattern.quote("|");
        String listCategories = getIntent().getExtras().getString("listCategories");
        String[] allCategories = listCategories.split(splitKind);
        final String[] generalCategories = allCategories[0].split(splitCategory);
        String[] customCategories = new String[1];
        generalCategories[0] = "Todas";

        if (allCategories.length > 1) {
            customCategories = allCategories[1].split(splitCategory);
            customCategories[0] = "Todas";
        } else {
            customCategories[0] = "No has creado categorías";
        }

        /* --- */

        ArrayAdapter<CharSequence> adapterKind = ArrayAdapter.createFromResource(this,
                R.array.kind_categories, android.R.layout.simple_spinner_item);
        adapterKind.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sKindCategory.setAdapter(adapterKind);

        final String[] finalCustomCategories = customCategories;
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
                        getCategories(generalCategories);
                        showCategories(tvLabel3, tvLabel4, sCategory);

                        break;
                    case 2:
                        kindCategorySelected = "custom";
                        getCategories(finalCustomCategories);
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
                if (!kindCategorySelected.equals("all")) {
                    if (categorySelected.equals("No has creado categorías")) {
                        Toast.makeText(SettingsGameActivity.this,
                                "Debes seleccionar una categoría válida", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        goToGame();
                    }
                } else {
                    goToGame();
                }
            }
        });
    }

    public void getCategories(String[] categories) {
        listCategories = new ArrayList<>(Arrays.asList(categories));

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listCategories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapterCategory);
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

        String url = spUserData.getString("urlKey", null);
        String userId = spUserData.getString("userIdKey", null);

        if (!userId.isEmpty()) {
            new GetWords(this).execute(url, userId, kindCategorySelected, categorySelected);
        } else {
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
