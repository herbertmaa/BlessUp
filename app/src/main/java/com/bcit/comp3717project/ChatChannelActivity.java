package com.bcit.comp3717project;


import com.google.android.material.tabs.TabLayout;
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

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import adapter.ChatChannelAdapter;
import model.Church;

public class ChatChannelActivity extends FireBaseActivity {

    ArrayList<Church> churchList = new ArrayList<>();
    ChatChannelAdapter adapter;
    ListView lvChatChannels;

    DatabaseReference churchDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_channel_list_layout);

        churchDBRef = FirebaseDatabase.getInstance().getReference("churches");
        lvChatChannels = (ListView) findViewById(R.id.lv_chat_channel);

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
                Intent appInfo = new Intent(ChatChannelActivity.this, ChatActivity.class);
                startActivity(appInfo);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}

