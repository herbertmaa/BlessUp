package com.bcit.comp3717project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.ChatListAdapter;
import model.ChatMessage;
import model.User;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    private EditText editTextMessage;
    private ImageButton buttonSendMessage;

    ListView lvChat;
    List<ChatMessage> messagesList;

    DatabaseReference databaseChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSendMessage = findViewById(R.id.btnSendMessage);
        databaseChat = FirebaseDatabase.getInstance().getReference("chat");

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        lvChat = (ListView) findViewById(R.id.lvChat);
        messagesList = new ArrayList<ChatMessage>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseChat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messagesList.clear();
                for (DataSnapshot toDoItemSnapshot : dataSnapshot.getChildren()) {
                    ChatMessage chatMessage = toDoItemSnapshot.getValue(ChatMessage.class);
                    messagesList.add(chatMessage);
                }

                ChatListAdapter adapter = new ChatListAdapter(ChatActivity.this, messagesList);
                lvChat.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString();

        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "You must enter a message.", Toast.LENGTH_LONG).show();
            return;
        }

        String messageFireBaseID = databaseChat.push().getKey();

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String createdAt = "" + day + "-" + month + "-" + year;
        ChatMessage chatMessage = new ChatMessage(messageFireBaseID, message, createdAt);

        Task setValueTask = databaseChat.child(messageFireBaseID).setValue(chatMessage);

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