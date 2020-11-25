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
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import adapter.ChatChannelAdapter;
import model.Church;

public class ChatChannelActivity extends FireBaseActivity {

    private Toolbar toolbar;
    private TextView toolBarText;

    ArrayList<Church> churchList = new ArrayList<>();
    ChatChannelAdapter adapter;
    ListView lvChatChannels;

    DatabaseReference churchDBRef;

    String chatID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_chat_channel);

        toolbar = (Toolbar) findViewById(R.id.toolbarChatChannels);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
                Intent intent = new Intent(ChatChannelActivity.this, ChatActivity.class);
                Church church = churchList.get(position);
                intent.putExtra("churchID", church.getChurchID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onLogin() {
        //todo?
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
//    private DatabaseReference getParentReference(DataSnapshot snapshot) {
//        DatabaseReference ref = snapshot.getRef();
//        return ref.getParent();
//    }
//        chatReference.setValue(church[0]);
//
//        // Check if a chat exists for this church channel
//        Query churchQuery = chatReference.orderByChild("churchID").equalTo(churchID);
//        churchQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(!dataSnapshot.exists()) {
//                    // Create a new chat for this church
//                    String chatID = chatReference.push().getKey();
//                    Chat newChat = new Chat(chatID, church[0]);
//                    chatReference.child(chatID).setValue(newChat);
//                    channelReference = chatReference.child(chatID);
//                } else {
//                    channelReference = getParentReference(dataSnapshot);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e(TAG, " Reading Chat/Creating Chat", databaseError.toException());
//            }
//        });
//
