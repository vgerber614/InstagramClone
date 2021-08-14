package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // if the user is already logged in, redirect to main activity
        if (ParseUser.getCurrentUser() != null) { // user is logged in
            gotoMainActivity();
        }

        // get view items
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                // user provided information to login the user
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sign up as a new user with the provided fields
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                // Create the ParseUser
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // sign up successful
                            gotoMainActivity();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Toast.makeText(LoginActivity.this,
                                    "Sign up failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Parse Exception: " + e);
                        }
                    }
                });
            }
        });

    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user: " + username);
        // verify user, navigate to login activity if successful
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) { // login is unsuccessful
                    Log.e(TAG, "Login unsuccessful");
                    Toast.makeText(LoginActivity.this,
                            "Incorrect username and/or password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                // else on success
                gotoMainActivity();
            }
        });
    }

    private void gotoMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}