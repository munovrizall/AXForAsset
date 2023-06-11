package com.artonov.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_black);
        getSupportActionBar().setTitle("Terms and Conditions");
        setupToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            // Tindakan yang ingin Anda jalankan saat tombol "back" diklik
            onBackPressed(); // Contoh: Kembali ke aktivitas sebelumnya
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Terms and Conditions");
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
    }
}