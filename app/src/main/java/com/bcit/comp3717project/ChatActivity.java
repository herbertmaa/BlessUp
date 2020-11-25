package com.bcit.comp3717project;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.wear.widget.CircledImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import adapter.ChatListAdapter;
import de.hdodenhof.circleimageview.CircleImageView;
import model.Chat;
import model.ChatMessage;
import model.Church;
import model.User;

public class ChatActivity extends FireBaseActivity {

    private static final String TAG = "ChatActivity";

    private Toolbar toolbar;
    private EditText editTextMessage;
    private Button buttonSendMessage;
    private CircleImageView toolBarImage;
    private TextView toolBarText;

    ListView lvChat;
    List<ChatMessage> messagesList = new ArrayList<ChatMessage>();
    ChatListAdapter adapter;

    DatabaseReference chatReference;
    DatabaseReference channelReference;
    DatabaseReference churchReference;

    Church church;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO remove action bar (DEBUG)
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_chat);

        toolbar = (Toolbar) findViewById(R.id.toolbarChat);
        toolBarImage = findViewById(R.id.chatToolBarImage);
        toolBarText = findViewById(R.id.chatToolBarText);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSendMessage = findViewById(R.id.btnSendMessage);
        lvChat = (ListView) findViewById(R.id.lvChat);

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        adapter = new ChatListAdapter(ChatActivity.this, messagesList);
        lvChat.setAdapter(adapter);

        Intent intent = getIntent();
        String churchID = intent.getStringExtra("churchID");

        churchReference = FirebaseDatabase.getInstance().getReference("churches/"+ churchID);
        churchReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                church = snapshot.getValue(Church.class);
                loadToolbarImage(church.getImageURL(), church.getName(), toolBarImage);
                toolBarText.setText(church.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Hydrating Church (CHAT ACTIVITY)", databaseError.toException());
            }
        });

        chatReference = FirebaseDatabase.getInstance().getReference("chat-" + churchID);

        chatReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messagesList.add(dataSnapshot.getValue(ChatMessage.class));
                adapter.notifyDataSetChanged();
                lvChat.smoothScrollToPosition(adapter.getCount() - 1);
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
    protected void onLogin() {

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

        String messageFireBaseID = chatReference.push().getKey();
        ChatMessage chatMessage = new ChatMessage(messageFireBaseID, message, newUser);

        Task setValueTask = chatReference.child(messageFireBaseID).setValue(chatMessage);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(ChatActivity.this, "Message Sent.", Toast.LENGTH_LONG).show();
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

    @SuppressLint("RestrictedApi")
    private void loadToolbarImage(String url, String churchName, CircleImageView image) {
        try {
            StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child(url);
            final File localFile = File.createTempFile(churchName, "png");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                Resources res = getResources();
                                if (image != null) {
                                    image.setImageDrawable(RoundedBitmapDrawableFactory.create(res, bitmap));
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
