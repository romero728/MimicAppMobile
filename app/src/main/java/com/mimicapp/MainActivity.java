package com.mimicapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    View view;

    GoogleApiClient googleApiClient;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        TextView tvUserName = findViewById(R.id.tvUserName);
        Button btnNewGame = findViewById(R.id.btnNewGame);
        Button btnCategories = findViewById(R.id.btnCategories);

        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        String userId = sharedPreferences.getString("userIdKey", null);

        if (userId == null) {
            userId = getIntent().getExtras().getString("userId");

            spEditor  = sharedPreferences.edit();
            spEditor.putString("userIdKey", userId);
            spEditor.apply();
        }

        String userName = sharedPreferences.getString("nameKey", null);

        if (userName != null) {
            String[] split = userName.split(" ");
            userName = split[0];

            userName = "Hola " + userName + ", \n Selecciona una opción";
            tvUserName.setText(userName);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewGame();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.
                silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSingInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSingInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSingInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
        } else {
            Toast.makeText(this, "Error con tu sesión", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToNewGame() {
        Intent iNewGame = new Intent(this, NewGameActivity.class);
        startActivity(iNewGame);
    }

    public void signOut(View view) {
        AlertDialog.Builder builderSignOut = new AlertDialog.Builder(this);
        builderSignOut.setMessage("¿Deseas cerrar sesión?");
        builderSignOut.setCancelable(true);

        builderSignOut.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builderSignOut.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            spEditor = sharedPreferences.edit();
                            spEditor.clear();
                            spEditor.apply();

                            Toast.makeText(MainActivity.this,
                                    "¡Hasta pronto!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "No se pudo cerrar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        AlertDialog alertDialog = builderSignOut.create();
        alertDialog.show();
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                signOut(view);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Deseas salir de la aplicación?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
