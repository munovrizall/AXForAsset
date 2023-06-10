package com.artonov.axforasset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupCarousel();
        setupUsername();
        setupToolbar();
        setupBottomNav();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("AXForAsset");
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
    }

    void setupCarousel() {
        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

        imageList.add(new SlideModel(R.drawable.carousel1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.carousel2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.carousel3, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.carousel4, ScaleTypes.CENTER_CROP));
        ImageSlider imageSlider = findViewById(R.id.carousel);
        imageSlider.setImageList(imageList);
    }

    void setupUsername() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        TextView tvGreeting = findViewById(R.id.tvGreeting);
        tvGreeting.setText("Welcome, " + username);
    }
    void setupBottomNav() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.navStore);
        bottomNavigationView.setItemActiveIndicatorColor(getResources().getColorStateList(R.color.white));
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navStore) {
                return true;
            } else if (itemId == R.id.navProfile) {

                return true;
            } else if (itemId == R.id.navLogout) {
                SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
                return true;
            }
            return false;
        });

    }

}