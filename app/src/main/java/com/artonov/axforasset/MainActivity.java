package com.artonov.axforasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forceLightMode();
        SharedPreferences spLogin = getSharedPreferences("loginStatus", MODE_PRIVATE);
        boolean isLoggedIn = spLogin.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("Username tidak boleh kosong");
                }


                if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Password tidak boleh kosong");
                } else if (etPassword.getText().length() < 8) {
                    etPassword.setError("Password harus lebih dari 8 karakter");
                } else {
                    SharedPreferences spLogin = getSharedPreferences("loginStatus", MODE_PRIVATE);
                    SharedPreferences.Editor editor = spLogin.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    SharedPreferences spUsername = getSharedPreferences("username", Context.MODE_PRIVATE);
                    SharedPreferences.Editor seUsername = spUsername.edit();
                    String username = etUsername.getText().toString();
                    seUsername.putString("username", username);
                    seUsername.apply();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void forceLightMode() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {// Dark mode is enabled
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO || nightModeFlags == Configuration.UI_MODE_NIGHT_UNDEFINED) {// Dark mode is not enabled
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
