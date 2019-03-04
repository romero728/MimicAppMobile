package com.mimicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

public class NewGameActivity extends AppCompatActivity {

    ConstraintLayout.LayoutParams params;

    SharedPreferences spNewGame;
    SharedPreferences.Editor editorNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        spNewGame = getSharedPreferences("settingsNewGame", Context.MODE_PRIVATE);

        final EditText etNameTeamOne = findViewById(R.id.etNameTeam1);
        final EditText etNameTeamTwo = findViewById(R.id.etNameTeam2);
        final EditText etNameTeamThree = findViewById(R.id.etNameTeam3);
        final EditText etNameTeamFour = findViewById(R.id.etNameTeam4);
        final EditText etNameTeamFive = findViewById(R.id.etNameTeam5);
        final EditText etNameTeamSix = findViewById(R.id.etNameTeam6);
        final EditText etNameTeamSeven = findViewById(R.id.etNameTeam7);

        etNameTeamOne.setVisibility(View.INVISIBLE);
        etNameTeamTwo.setVisibility(View.INVISIBLE);
        etNameTeamThree.setVisibility(View.INVISIBLE);
        etNameTeamFour.setVisibility(View.INVISIBLE);
        etNameTeamFive.setVisibility(View.INVISIBLE);
        etNameTeamSix.setVisibility(View.INVISIBLE);
        etNameTeamSeven.setVisibility(View.INVISIBLE);

        final Button btnNext = findViewById(R.id.btnNGNext);

        Spinner sNumberTeams = findViewById(R.id.sNumberTeams);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_teams, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNumberTeams.setAdapter(adapter);

        sNumberTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        etNameTeamOne.setVisibility(View.VISIBLE);
                        etNameTeamTwo.setVisibility(View.VISIBLE);
                        etNameTeamThree.setVisibility(View.INVISIBLE);
                        etNameTeamFour.setVisibility(View.INVISIBLE);
                        etNameTeamFive.setVisibility(View.INVISIBLE);
                        etNameTeamSix.setVisibility(View.INVISIBLE);
                        etNameTeamSeven.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.VISIBLE);

                        params = (ConstraintLayout.LayoutParams) btnNext.getLayoutParams();
                        params.topToBottom = R.id.etNameTeam2;

                        editorNewGame = spNewGame.edit();
                        editorNewGame.putString("numberTeamsKey", "2");
                        editorNewGame.apply();


                        break;
                    case 1:
                        etNameTeamOne.setVisibility(View.VISIBLE);
                        etNameTeamTwo.setVisibility(View.VISIBLE);
                        etNameTeamThree.setVisibility(View.VISIBLE);
                        etNameTeamFour.setVisibility(View.INVISIBLE);
                        etNameTeamFive.setVisibility(View.INVISIBLE);
                        etNameTeamSix.setVisibility(View.INVISIBLE);
                        etNameTeamSeven.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.VISIBLE);

                        params = (ConstraintLayout.LayoutParams) btnNext.getLayoutParams();
                        params.topToBottom = R.id.etNameTeam3;

                        editorNewGame = spNewGame.edit();
                        editorNewGame.putString("numberTeamsKey", "3");
                        editorNewGame.apply();

                        break;
                    case 2:
                        etNameTeamOne.setVisibility(View.VISIBLE);
                        etNameTeamTwo.setVisibility(View.VISIBLE);
                        etNameTeamThree.setVisibility(View.VISIBLE);
                        etNameTeamFour.setVisibility(View.VISIBLE);
                        etNameTeamFive.setVisibility(View.INVISIBLE);
                        etNameTeamSix.setVisibility(View.INVISIBLE);
                        etNameTeamSeven.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.VISIBLE);

                        params = (ConstraintLayout.LayoutParams) btnNext.getLayoutParams();
                        params.topToBottom = R.id.etNameTeam4;

                        editorNewGame = spNewGame.edit();
                        editorNewGame.putString("numberTeamsKey", "4");
                        editorNewGame.apply();

                        break;
                    case 3:
                        etNameTeamOne.setVisibility(View.VISIBLE);
                        etNameTeamTwo.setVisibility(View.VISIBLE);
                        etNameTeamThree.setVisibility(View.VISIBLE);
                        etNameTeamFour.setVisibility(View.VISIBLE);
                        etNameTeamFive.setVisibility(View.VISIBLE);
                        etNameTeamSix.setVisibility(View.INVISIBLE);
                        etNameTeamSeven.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.VISIBLE);

                        params = (ConstraintLayout.LayoutParams) btnNext.getLayoutParams();
                        params.topToBottom = R.id.etNameTeam5;

                        editorNewGame = spNewGame.edit();
                        editorNewGame.putString("numberTeamsKey", "5");
                        editorNewGame.apply();

                        break;
                    case 4:
                        etNameTeamOne.setVisibility(View.VISIBLE);
                        etNameTeamTwo.setVisibility(View.VISIBLE);
                        etNameTeamThree.setVisibility(View.VISIBLE);
                        etNameTeamFour.setVisibility(View.VISIBLE);
                        etNameTeamFive.setVisibility(View.VISIBLE);
                        etNameTeamSix.setVisibility(View.VISIBLE);
                        etNameTeamSeven.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.VISIBLE);

                        params = (ConstraintLayout.LayoutParams) btnNext.getLayoutParams();
                        params.topToBottom = R.id.etNameTeam6;

                        editorNewGame = spNewGame.edit();
                        editorNewGame.putString("numberTeamsKey", "6");
                        editorNewGame.apply();

                        break;
                    case 5:
                        etNameTeamOne.setVisibility(View.VISIBLE);
                        etNameTeamTwo.setVisibility(View.VISIBLE);
                        etNameTeamThree.setVisibility(View.VISIBLE);
                        etNameTeamFour.setVisibility(View.VISIBLE);
                        etNameTeamFive.setVisibility(View.VISIBLE);
                        etNameTeamSix.setVisibility(View.VISIBLE);
                        etNameTeamSeven.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.VISIBLE);

                        params = (ConstraintLayout.LayoutParams) btnNext.getLayoutParams();
                        params.topToBottom = R.id.etNameTeam7;

                        editorNewGame = spNewGame.edit();
                        editorNewGame.putString("numberTeamsKey", "7");
                        editorNewGame.apply();

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userName = Integer.parseInt(spNewGame.getString("numberTeamsKey", null));

                switch (userName) {
                    case 2:
                        if (!etNameTeamOne.getText().toString().isEmpty() &&
                                !etNameTeamTwo.getText().toString().isEmpty()) {
                            editorNewGame = spNewGame.edit();
                            editorNewGame.putString("nameTeamOne", String.valueOf(
                                    etNameTeamOne.getText()));
                            editorNewGame.putString("nameTeamTwo", String.valueOf(
                                    etNameTeamTwo.getText()));
                            editorNewGame.apply();

                            changeActivity();
                        } else {
                            myToast();
                        }

                        break;

                    case 3:
                        if (!etNameTeamOne.getText().toString().isEmpty() &&
                                !etNameTeamTwo.getText().toString().isEmpty() &&
                                !etNameTeamThree.getText().toString().isEmpty()) {
                            editorNewGame = spNewGame.edit();
                            editorNewGame.putString("nameTeamOne", String.valueOf(
                                    etNameTeamOne.getText()));
                            editorNewGame.putString("nameTeamTwo", String.valueOf(
                                    etNameTeamTwo.getText()));
                            editorNewGame.putString("nameTeamThree", String.valueOf(
                                    etNameTeamThree.getText()));
                            editorNewGame.apply();

                            changeActivity();
                        } else {
                            myToast();
                        }

                        break;

                    case 4:
                        if (!etNameTeamOne.getText().toString().isEmpty()&&
                                !etNameTeamTwo.getText().toString().isEmpty() &&
                                !etNameTeamThree.getText().toString().isEmpty() &&
                                !etNameTeamFour.getText().toString().isEmpty()) {
                            editorNewGame = spNewGame.edit();
                            editorNewGame.putString("nameTeamOne", String.valueOf(
                                    etNameTeamOne.getText()));
                            editorNewGame.putString("nameTeamTwo", String.valueOf(
                                    etNameTeamTwo.getText()));
                            editorNewGame.putString("nameTeamThree", String.valueOf(
                                    etNameTeamThree.getText()));
                            editorNewGame.putString("nameTeamFour", String.valueOf(
                                    etNameTeamFour.getText()));
                            editorNewGame.apply();

                            changeActivity();
                        } else {
                            myToast();
                        }

                        break;

                    case 5:
                        if (!etNameTeamOne.getText().toString().isEmpty() &&
                                !etNameTeamTwo.getText().toString().isEmpty() &&
                                !etNameTeamThree.getText().toString().isEmpty() &&
                                !etNameTeamFour.getText().toString().isEmpty() &&
                                !etNameTeamFive.getText().toString().isEmpty()) {
                            editorNewGame = spNewGame.edit();
                            editorNewGame.putString("nameTeamOne", String.valueOf(
                                    etNameTeamOne.getText()));
                            editorNewGame.putString("nameTeamTwo", String.valueOf(
                                    etNameTeamTwo.getText()));
                            editorNewGame.putString("nameTeamThree", String.valueOf(
                                    etNameTeamThree.getText()));
                            editorNewGame.putString("nameTeamFour", String.valueOf(
                                    etNameTeamFour.getText()));
                            editorNewGame.putString("nameTeamFive", String.valueOf(
                                    etNameTeamFive.getText()));
                            editorNewGame.apply();

                            changeActivity();
                        } else {
                            myToast();
                        }

                        break;

                    case 6:
                        if (!etNameTeamOne.getText().toString().isEmpty() &&
                                !etNameTeamTwo.getText().toString().isEmpty() &&
                                !etNameTeamThree.getText().toString().isEmpty() &&
                                !etNameTeamFour.getText().toString().isEmpty() &&
                                !etNameTeamFive.getText().toString().isEmpty() &&
                                !etNameTeamSix.getText().toString().isEmpty()) {
                            editorNewGame = spNewGame.edit();
                            editorNewGame.putString("nameTeamOne", String.valueOf(
                                    etNameTeamOne.getText()));
                            editorNewGame.putString("nameTeamTwo", String.valueOf(
                                    etNameTeamTwo.getText()));
                            editorNewGame.putString("nameTeamThree", String.valueOf(
                                    etNameTeamThree.getText()));
                            editorNewGame.putString("nameTeamFour", String.valueOf(
                                    etNameTeamFour.getText()));
                            editorNewGame.putString("nameTeamFive", String.valueOf(
                                    etNameTeamFive.getText()));
                            editorNewGame.putString("nameTeamSix", String.valueOf(
                                    etNameTeamSix.getText()));
                            editorNewGame.apply();

                            changeActivity();
                        } else {
                            myToast();
                        }

                        break;

                    case 7:
                        if (!etNameTeamOne.getText().toString().isEmpty() &&
                                !etNameTeamTwo.getText().toString().isEmpty() &&
                                !etNameTeamThree.getText().toString().isEmpty() &&
                                !etNameTeamFour.getText().toString().isEmpty() &&
                                !etNameTeamFive.getText().toString().isEmpty() &&
                                !etNameTeamSix.getText().toString().isEmpty() &&
                                !etNameTeamSeven.getText().toString().isEmpty()) {
                            editorNewGame = spNewGame.edit();
                            editorNewGame.putString("nameTeamOne", String.valueOf(
                                    etNameTeamOne.getText()));
                            editorNewGame.putString("nameTeamTwo", String.valueOf(
                                    etNameTeamTwo.getText()));
                            editorNewGame.putString("nameTeamThree", String.valueOf(
                                    etNameTeamThree.getText()));
                            editorNewGame.putString("nameTeamFour", String.valueOf(
                                    etNameTeamFour.getText()));
                            editorNewGame.putString("nameTeamFive", String.valueOf(
                                    etNameTeamFive.getText()));
                            editorNewGame.putString("nameTeamSix", String.valueOf(
                                    etNameTeamSix.getText()));
                            editorNewGame.putString("nameTeamSeven", String.valueOf(
                                    etNameTeamSeven.getText()));
                            editorNewGame.apply();

                            changeActivity();
                        } else {
                            myToast();
                        }

                        break;

                    default:
                        break;
                }
            }
        });
    }

    public void myToast() {
        String message = "Por favor ingresa el nombre de cada equipo";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void changeActivity() {
        Intent intent = new Intent(this, SettingsGameActivity.class);
        startActivity(intent);
        finish();
    }
}