package com.bcit.comp3717project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This class holds all the firebase listener and other classes extend from here to use firebase methods.
 */
public abstract class FireBaseActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    public static String TAG = "FireBaseActivity";
    protected FirebaseAuth auth = FirebaseAuth.getInstance();

    //Override this method in your Activity and call super.onCreate(savedInstanceState)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected abstract void onLogin();

    protected void onLogout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish(); // Finish the previous activity that called log out.
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Add the listener when this activity starts
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Remove the listener when this activity stops
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }



    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            onLogin();

        } else {
            // User is signed out
            onLogout();
        }

    }
}

