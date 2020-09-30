package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onLoginClick(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void onSelectReligionClick(View view) {
        Intent i = new Intent(this, LoginActivity.class); //TODO
        startActivity(i);
    }
}