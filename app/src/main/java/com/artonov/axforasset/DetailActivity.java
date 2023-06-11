package com.artonov.axforasset;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class DetailActivity extends AppCompatActivity {
    private TextView tvGame;
    private TextView tvDesc;
    private ImageView ivGame;
    private EditText etEmail;
    private Spinner spinnerPayment;
    private Button btnBuy;
    private String selectedCardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupToolbar();
        setupView();
        setupSpinner();
        setupButton();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_black);
        getSupportActionBar().setTitle("Detail Asset");
    }

    void setupView() {
        tvGame = findViewById(R.id.tvGame);
        tvDesc = findViewById(R.id.tvDesc);
        ivGame = findViewById(R.id.ivGame);

        // Mendapatkan data game asset dari intent
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        int image = getIntent().getIntExtra("image", 0);


        // Menampilkan data game asset di TextView
        tvGame.setText(title);
        tvDesc.setText(desc);
        ivGame.setImageResource(image);
    }

    void setupSpinner() {
        spinnerPayment = findViewById(R.id.spinnerPayment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.card_types,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayment.setAdapter(adapter);
        spinnerPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCardType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tidak ada tindakan khusus saat tidak ada item yang dipilih
            }
        });
    }

    void setupButton() {
        btnBuy = findViewById(R.id.btnBuy);
        etEmail = findViewById(R.id.etEmail);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                if (email.matches(emailPattern) &&
                        email.length() > 0) {
                    setupAlert(email);
                } else {
                    etEmail.setError("Invalid email");
                }
            }
        });
    }

    void setupAlert(String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Email has been sent!");
        builder.setMessage("Confirmation email has been sent to " + email + "\n\nYou choose payment via: " + selectedCardType + "\n\nThank You!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
