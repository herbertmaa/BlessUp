package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends FireBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
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

    public void onSelectChatChannelClick(View view) {
        Intent i = new Intent(this, ChatChannelActivity.class);
        startActivity(i);
    }


    public void onSelectRegisterClick(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void onSelectChurchesPageViewClick(View view) {
        Intent i = new Intent(this, ViewChurchesActivity.class);
        startActivity(i);
    }

    public void onImageClick(View view) {
        Intent i = new Intent(this, ImageActivity.class);
        startActivity(i);
    }

    public void onLogoutClick(View view) {
        auth.signOut();
        Toast t = Toast.makeText(this, "Logged user out", Toast.LENGTH_LONG);
        t.show();
    }
}
