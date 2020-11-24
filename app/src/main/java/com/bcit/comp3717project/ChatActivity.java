package com.bcit.comp3717project;

import androidx.annotation.NonNull;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import adapter.ChatListAdapter;
import model.ChatMessage;
import model.User;

public class ChatActivity extends FireBaseActivity {

    private static final String TAG = "ChatActivity";
    SharedPreferences pref;

    private EditText editTextMessage;
    private ImageButton buttonSendMessage;

    ListView lvChat;
    List<ChatMessage> messagesList;
    ChatListAdapter adapter;

    DatabaseReference chatCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSendMessage = findViewById(R.id.btnSendMessage);
        chatCollection = FirebaseDatabase.getInstance().getReference("chat");

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        lvChat = (ListView) findViewById(R.id.lvChat);
        messagesList = new ArrayList<ChatMessage>();
        adapter = new ChatListAdapter(ChatActivity.this, messagesList);
        lvChat.setAdapter(adapter);

        chatCollection.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messagesList.add(dataSnapshot.getValue(ChatMessage.class));
                adapter.notifyDataSetChanged();
                lvChat.smoothScrollToPosition(adapter.getCount()-1);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString();

        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "You must enter a message.", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userUid = firebaseUser.getUid();
        User newUser = new User(userUid, firebaseUser.getDisplayName(), firebaseUser.getDisplayName(), firebaseUser.getEmail());

        String messageFireBaseID = chatCollection.push().getKey();
        ChatMessage chatMessage = new ChatMessage(messageFireBaseID, message, newUser);

        Task setValueTask = chatCollection.child(messageFireBaseID).setValue(chatMessage);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(ChatActivity.this,"Message Sent.",Toast.LENGTH_LONG).show();
                editTextMessage.setText("");
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChatActivity.this,
                        "Something went wrong.\n" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
