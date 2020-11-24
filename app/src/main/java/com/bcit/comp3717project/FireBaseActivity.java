package com.bcit.comp3717project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public abstract class FireBaseActivity extends AppCompatActivity {

    public static String TAG = "FireBaseActivity";

    protected FirebaseAuth auth = FirebaseAuth.getInstance();
    protected FirebaseUser currentUser = null;


    //Override this method in your Activity and call super.onCreate(savedInstanceState)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        auth.addAuthStateListener(firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.e(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                onLogin();
            } else {
                // User is signed out
                Log.e(TAG, "onAuthStateChanged:signed_out");
                onLogout();
            }
        });
    }


    protected abstract void onLogin();
    protected void onLogout(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }
}
