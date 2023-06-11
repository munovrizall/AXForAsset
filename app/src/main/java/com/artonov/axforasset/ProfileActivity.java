package com.artonov.axforasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        CardView cardView = findViewById(R.id.cardView);
        cardView.setBackgroundResource(R.drawable.card_bottom_radius);
        setupView();
        setupBottomNav();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    void setupView() {
        SharedPreferences spUsername = getSharedPreferences("username", MODE_PRIVATE);
        String username = spUsername.getString("username", "");
        TextView tvUsername = findViewById(R.id.tvUsername);
        tvUsername.setText(username);
    }
    void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
    }

    void setupBottomNav() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.navProfile);
        bottomNavigationView.setItemActiveIndicatorColor(getResources().getColorStateList(R.color.white));
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navStore) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.navProfile) {
                return true;
            } else if (itemId == R.id.navLogout) {
                SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}