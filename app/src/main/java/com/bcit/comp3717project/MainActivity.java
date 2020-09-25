package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String appID = BuildConfig.APPLICATION_ID;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());
        this.realm = Realm.getDefaultInstance();

    }
}