package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ChurchListingsActivity extends AppCompatActivity {
    String selectedReligion;
    Button btnBuddhism;
    Button btnChristianity;
    Button btnIslam;
    Button btnJudaism;
    Button btnHinduism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church_listings);

        btnBuddhism = findViewById(R.id.btnBuddhism);
        btnChristianity = findViewById(R.id.btnChristianity);
        btnIslam = findViewById(R.id.btnIslam);
        btnJudaism = findViewById(R.id.btnJudaism);
        btnHinduism = findViewById(R.id.btnHinduism);
    }
}