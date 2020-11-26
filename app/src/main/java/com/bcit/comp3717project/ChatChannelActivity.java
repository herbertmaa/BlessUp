package com.bcit.comp3717project;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import adapter.ChatChannelAdapter;
import model.Church;

public class ChatChannelActivity extends FireBaseActivity {

    private Toolbar toolbar;
    ArrayList<Church> churchList = new ArrayList<>();
    ChatChannelAdapter adapter;
    ListView lvChatChannels;
    DatabaseReference churchDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_chat_channel);

        toolbar = findViewById(R.id.toolbarChatChannels);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        churchDBRef = FirebaseDatabase.getInstance().getReference("churches");
        lvChatChannels = findViewById(R.id.lv_chat_channel);

        adapter = new ChatChannelAdapter(ChatChannelActivity.this, churchList);
        lvChatChannels.setAdapter(adapter);

        churchDBRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                churchList.add(dataSnapshot.getValue(Church.class));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        lvChatChannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(ChatChannelActivity.this, ChatActivity.class);
                Church church = churchList.get(position);
                intent.putExtra("churchID", church.getChurchID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onLogin(){}

}
