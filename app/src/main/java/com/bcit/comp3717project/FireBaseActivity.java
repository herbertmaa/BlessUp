package com.bcit.comp3717project;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireBaseActivity extends AppCompatActivity {

    public static String TAG = "FireBaseActivity";

    protected FirebaseAuth auth = FirebaseAuth.getInstance();
    protected FirebaseUser currentUser = null;

    //Override this method in your Activity
    public void updateUI(FirebaseUser user){
        Log.e(TAG, user.toString());
    }


    //Override this method in your Activity and call super.onCreate(savedInstanceState)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FirebaseAuth.AuthStateListener mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
            // ...
        };

    }


}
