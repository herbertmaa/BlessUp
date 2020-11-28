package com.bcit.comp3717project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

/**
 * Activity to inform the user to join a church before being able to
 * participate in the a Chat
 */
public class NotAMemberActivity extends FireBaseActivity {

    /**
     * Toolbar used to provide details to the user about the current view.
     */
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_not_a_member);

        toolbar = findViewById(R.id.toolbarNotAMember);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onLogin() {}

    /**
     * Button event handler used to direct user to ViewChurchesActivity
     * @param view - the current view
     */
    public void onViewChurches(View view) {
        Intent i = new Intent(this, ViewChurchesActivity.class);
        startActivity(i);
    }
}
