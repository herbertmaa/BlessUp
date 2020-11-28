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

/**
 * Activity providing current user with a list of Church Chat Channels each
 * chat channel linked to a church that the user has joined.
 */
public class ChatChannelActivity extends FireBaseActivity {

    /**
     * Toolbar used to provide user with details about the current activity
     */
    private Toolbar toolbar;

    /**
     * List of churches stored in Firebase
     */
    ArrayList<Church> churchList = new ArrayList<>();

    /**
     * List View to populate populate ChatChannelActivity
     * with Chats for Churches the current firebase user has joined
     */
    ListView lvChatChannels;

    /**
     * Renders each ListView item
     */
    ChatChannelAdapter adapter;

    /**
     * Church Firebase Reference used to populate ChatChannelActivity
     * with Chats for Churches the current firebase user has joined
     */
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
                finish();
            }
        });

        renderChatChannels();
    }

    /**
     * Renders the ChatChannel ListView onto Layout. Populates the ListView with
     * Churches that the current firebase user has joined. Sets a click handler on each
     * ListView Item to push to the correct Chat Channel.
     */
    private void renderChatChannels() {
        churchDBRef = FirebaseDatabase.getInstance().getReference("churches");
        lvChatChannels = findViewById(R.id.lv_chat_channel);

        adapter = new ChatChannelAdapter(ChatChannelActivity.this, churchList);
        lvChatChannels.setAdapter(adapter);

        churchDBRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Church my_church = (dataSnapshot.getValue(Church.class));
                if(my_church.members == null || !my_church.members.containsKey(auth.getUid())) return;
                churchList.add(my_church);
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