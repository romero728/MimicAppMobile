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

public class ListCategoryActivity extends AppCompatActivity {

    ConstraintLayout.LayoutParams params;
    String categorySelected, kindCategory;

    Spinner sKindCategory = null;
    Spinner sCategorySelected = null;

    List<String> listCategories;

    SharedPreferences spSettingsGame, spUserData;
    SharedPreferences.Editor editorSettingsGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        spUserData = getSharedPreferences("userData", Context.MODE_PRIVATE);

        final TextView tvLabel2 = findViewById(R.id.tvLCLabel2);
        final TextView tvLabel3 = findViewById(R.id.tvLCLabel3);

        sKindCategory = findViewById(R.id.sLCKindCategory);
        sCategorySelected = findViewById(R.id.sLCCategory);

        Button btnNext = findViewById(R.id.btnLCGoCategory);



        /*--- Obtener categorías */

        String splitKind = Pattern.quote("/");
        String splitCategory = Pattern.quote("|");
        String listCategories = getIntent().getExtras().getString("listCategories");
        String[] allCategories = listCategories.split(splitKind);
        final String[] generalCategories = allCategories[0].split(splitCategory);
        String[] customCategories = new String[1];
        generalCategories[0]= "-------";

        if (allCategories.length > 1) {
            customCategories = allCategories[1].split(splitCategory);
            customCategories[0] = "-------";
        } else {
            customCategories[0] = "No has creado categorías";
        }

        /* --- */

        ArrayAdapter<CharSequence> adapterKind = ArrayAdapter.createFromResource(this,
                R.array.kind_categoriesLC, android.R.layout.simple_spinner_item);
        adapterKind.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sKindCategory.setAdapter(adapterKind);

        final String[] finalCustomCategories = customCategories;
        sKindCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        kindCategory = "general";
                        getCategories(generalCategories);
                        showCategories(tvLabel3, sCategorySelected);
                        break;
                    case 1:
                        kindCategory = "custom";
                        getCategories(finalCustomCategories);
                        showCategories(tvLabel3, sCategorySelected);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sCategorySelected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!kindCategory.equals(("general"))){
                    if(categorySelected.equals("No has creado categorías")){
                        Toast.makeText(ListCategoryActivity.this,
                                "Debes seleccionar una categoría válida", Toast.LENGTH_SHORT)
                                .show();
                    }
                    else{
                        goToCategory();
                    }
                }
                else{
                    goToCategory();
                }
            }
        });
    }




    private void showCategories(TextView tvLabel3, Spinner sCategorySelected) {
        tvLabel3.setVisibility(View.VISIBLE);
        sCategorySelected.setVisibility(View.VISIBLE);
    }


    public void hideCategories(TextView tv3, Spinner s) {
        tv3.setVisibility(View.INVISIBLE);
        s.setVisibility(View.INVISIBLE);
    }

    private void getCategories(String[] generalCategories) {
        listCategories = new ArrayList<>(Arrays.asList(generalCategories));

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listCategories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategorySelected.setAdapter(adapterCategory);
    }

    private void goToCategory(){
        /*editorSettingsGame = spSettingsGame.edit();
        editorSettingsGame.putString("kindCategoryKey", kindCategory);
        editorSettingsGame.putString("categoryKey", categorySelected);
        editorSettingsGame.apply();

        Intent intent = new Intent(ListCategoryActivity.this,
                SettingsGameActivity.class);
        startActivity(intent);
        finish();*/
    }




}


