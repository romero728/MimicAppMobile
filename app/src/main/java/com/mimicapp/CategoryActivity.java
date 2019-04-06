package com.mimicapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mimicapp.DBConnection.CategoriesEdit;
import com.mimicapp.DBConnection.ListCategories;

import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {

    View view;

    GoogleApiClient googleApiClient;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        Button btnCreateCategories = findViewById(R.id.btnCreateCategories);
        Button btnSeeCategories = findViewById(R.id.btnSeeCategories);

        btnSeeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListCategory();
            }
        });
    }

    public void goToListCategory(){

        SharedPreferences spUser = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String url = spUser.getString("urlKey", null);
        String userId = spUser.getString("userIdKey", null);

        new CategoriesEdit(this).execute(url, userId);
    }

}
