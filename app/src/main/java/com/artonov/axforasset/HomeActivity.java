package com.artonov.axforasset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.artonov.axforasset.adapter.GameAssetAdapter;
import com.artonov.axforasset.model.GameAsset;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GameAssetAdapter gameAssetAdapter;
    private List<GameAsset> gameAssetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupCarousel();
        setupUsername();
        setupRecyclerView();
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
        toolbar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.barTerms) {
                    Intent intent = new Intent(HomeActivity.this, TermsActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

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
        SharedPreferences spUsername = getSharedPreferences("username", MODE_PRIVATE);
        String username = spUsername.getString("username", "");

        TextView tvGreeting = findViewById(R.id.tvGreeting);
        tvGreeting.setText("Welcome, " + username);
    }

    void setupRecyclerView() {
        recyclerView = findViewById(R.id.rvGame);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gameAssetList = generateGameAssets(); // Metode untuk menghasilkan daftar game asset

        gameAssetAdapter = new GameAssetAdapter(gameAssetList, this);
        recyclerView.setAdapter(gameAssetAdapter);
    }

    private List<GameAsset> generateGameAssets() {
        List<GameAsset> gameAssets = new ArrayList<>();
        gameAssets.add(new GameAsset(
                getString(R.string.game_1),
                getString(R.string.desc_1),
                R.drawable.game_1));
        gameAssets.add(new GameAsset(
                getString(R.string.game_2),
                getString(R.string.desc_2),
                R.drawable.game_2));
        gameAssets.add(new GameAsset(
                getString(R.string.game_3),
                getString(R.string.desc_3),
                R.drawable.game_3));
        gameAssets.add(new GameAsset(
                getString(R.string.game_4),
                getString(R.string.desc_4),
                R.drawable.game_4));
        gameAssets.add(new GameAsset(
                getString(R.string.game_5),
                getString(R.string.desc_5),
                R.drawable.game_5));

        return gameAssets;
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
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.navLogout) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Konfirmasi Logout");
                builder.setMessage("Apakah Anda yakin ingin logout?");

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", false);
                        editor.apply();

                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        finish();
                    }
                });

                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
            return false;
        });

    }

}