package com.bcit.comp3717project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import model.Church;
import model.User;

public class MainActivity extends FireBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
    }

    public void onSelectReligionClick(View view) {
        Intent i = new Intent(this, ReligionSelectionActivity.class);
        startActivity(i);
    }

    public void onSelectChatChannelClick(View view) {
        Intent intentChatChannel = new Intent(this, ChatChannelActivity.class);
        Intent intentNotAMember = new Intent(this, NotAMemberActivity.class);

        DatabaseReference churchesRef = FirebaseDatabase.getInstance().getReference("churches");
        churchesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isMemberToAnyChurch = false;
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Church church = postSnapshot.getValue(Church.class);
                    HashMap<String, User> membersMap = church.getMembers();
                    if (membersMap != null && membersMap.containsKey(auth.getCurrentUser().getUid())) {
                        isMemberToAnyChurch = true;
                    }
                }

                if (isMemberToAnyChurch) {
                    startActivity(intentChatChannel);
                } else {
                    startActivity(intentNotAMember);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onSelectChurchesPageViewClick(View view) {
        Intent i = new Intent(this, ViewChurchesActivity.class);
        startActivity(i);
    }

    public void onAboutUsPageClick(View view) {
        Intent i = new Intent(this, AboutUsActivity.class);
        startActivity(i);
    }

    public void onLogoutClick(View view) {
        auth.signOut();
        Toast t = Toast.makeText(this, "Logged user out", Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    protected void onLogin() {
        
    }
}
