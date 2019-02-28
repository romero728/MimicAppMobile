package com.mimicapp;

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

public class NewGameActivity extends AppCompatActivity {

    ConstraintLayout.LayoutParams params;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

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
                        etNameTeamOne.setVisibility(View.INVISIBLE);
                        etNameTeamTwo.setVisibility(View.INVISIBLE);
                        etNameTeamThree.setVisibility(View.INVISIBLE);
                        etNameTeamFour.setVisibility(View.INVISIBLE);
                        etNameTeamFive.setVisibility(View.INVISIBLE);
                        etNameTeamSix.setVisibility(View.INVISIBLE);
                        etNameTeamSeven.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.INVISIBLE);

                        break;
                    case 1:
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

                        break;
                    case 2:
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

                        break;
                    case 3:
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

                        break;
                    case 4:
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

                        break;
                    case 5:
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

                        break;
                    case 6:
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

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
