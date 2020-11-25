package com.bcit.comp3717project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class NotAMemberActivity extends FireBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_a_member);
    }

    @Override
    protected void onLogin() {

    }

    public void onViewChurches(View view) {
        Intent i = new Intent(this, ViewChurchesActivity.class);
        startActivity(i);
    }
}
