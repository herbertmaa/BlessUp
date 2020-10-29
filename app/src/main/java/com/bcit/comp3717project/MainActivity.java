package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

   public void onLoginClick(View view) {
       Intent i = new Intent(this, LoginActivity.class);
       startActivity(i);
   }

    public void onSelectReligionClick(View view) {
        Intent i = new Intent(this, ReligionSelectionActivity.class);
        startActivity(i);
    }

    public void onSelectChatClick(View view) {
        Intent i = new Intent(this, ChatActivity.class);
        startActivity(i);
    }

    public void onSelectChurchClick(View view) {
        Intent i = new Intent(this, ChurchListingsActivity.class);
        startActivity(i);
    }

    public void onSelectRegisterClick(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void onSelectMapClick(View view) {
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
    }
}
