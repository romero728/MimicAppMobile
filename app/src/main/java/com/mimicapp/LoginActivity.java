package com.mimicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mimicapp.DBConnection.UserRegister;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    private static final int RC_SIGN_IN = 888;

    String url = "https://mimicapp.000webhostapp.com/mimicApp/";
    String userEmail = "";
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            if (account != null) {
                userEmail = account.getEmail();
                userName = account.getDisplayName();

                spEditor  = sharedPreferences.edit();
                spEditor.putString("urlKey", url);
                spEditor.putString("emailKey", userEmail);
                spEditor.putString("nameKey", userName);
                spEditor.apply();

                new UserRegister(this).execute(url, userEmail, userName);

                Toast.makeText(this, "Welcome " + userName, Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Ups! Error xD", Toast.LENGTH_LONG).show();
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}