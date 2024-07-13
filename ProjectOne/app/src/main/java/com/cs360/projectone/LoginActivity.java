package com.cs360.projectone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;
    private Button loginButton;
    private Button registerButton;
    private static final String PREFERENCES_FILE = "com.example.app.PREFERENCES_FILE";
    private static final String KEY_USERNAME = "KEY_USERNAME";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";
    private static final String KEY_REMEMBER_ME = "KEY_REMEMBER_ME";
    private UserDatabaseHelper userDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDatabaseHelper = new UserDatabaseHelper(this);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        rememberMeCheckBox = findViewById(R.id.remember_me_checkbox);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        checkSavedLogin();

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            boolean rememberMe = rememberMeCheckBox.isChecked();

            // Validate login credentials directly
            if (userDatabaseHelper.validateUser(username, password)) {
                if (rememberMe) {
                    saveLoginDetails(username, password);
                }
                Log.d("LoginActivity", "Moving to main activity!");
                navigateToMainActivity();
            } else {
                Log.d("LoginActivity", "Invalid login credentials");
                // Show error message to the user
            }
        });

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (userDatabaseHelper.registerUser(username, password)) {
                Log.d("LoginActivity", "User registered successfully");
                // Optionally, navigate to the main activity or show a success message
            } else {
                Log.d("LoginActivity", "User already exists");
                // Show error message to the user
            }
        });
    }

    private void checkSavedLogin() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        boolean rememberMe = preferences.getBoolean(KEY_REMEMBER_ME, false);

        if (rememberMe) {
            String savedUsername = preferences.getString(KEY_USERNAME, null);
            String savedPassword = preferences.getString(KEY_PASSWORD, null);

            if (savedUsername != null && savedPassword != null) {
                usernameEditText.setText(savedUsername);
                passwordEditText.setText(savedPassword);
                loginButton.performClick(); // attempt to log in automatically
            }
        }
    }

    private void saveLoginDetails(String username, String password) {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_REMEMBER_ME, true);
        editor.apply();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, InventoryGridViewActivity.class);
        startActivity(intent);
        finish();
    }
}

