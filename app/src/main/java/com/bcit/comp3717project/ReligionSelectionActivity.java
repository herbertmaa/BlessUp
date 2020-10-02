package com.bcit.comp3717project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ReligionSelectionActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion_selection);
    }

    public void onConfirmClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
