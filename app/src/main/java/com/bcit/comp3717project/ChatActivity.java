package com.bcit.comp3717project;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import adapter.ChatListAdapter;
import de.hdodenhof.circleimageview.CircleImageView;
import model.ChatMessage;
import model.Church;
import model.User;

/**
 * Activity providing current user with a Chat Thread that is linked with
 * a Church that the user has joined.
 */
public class ChatActivity extends FireBaseActivity {

    private static final String TAG = "ChatActivity";

    /**
     * Toolbar containing Church Details linked to this chat
     */
    private Toolbar toolbar;

    /**
     * Text input field to create new messages
     */
    private EditText editTextMessage;

    /**
     * Button used to send completed messages to Firebase
     */
    private Button buttonSendMessage;

    /**
     * Image View hosting church's image
     */
    private CircleImageView toolBarImage;

    /**
     * TextView for toolbar
     */
    private TextView toolBarText;

    /**
     * List View used to render chat messages
     */
    ListView lvChat;

    /**
     * List  containing chat messages
     */
    List<ChatMessage> messagesList = new ArrayList<ChatMessage>();

    /**
     * Adapter to render each message into the list view
     */
    ChatListAdapter adapter;

    /**
     * Firebase database reference to this chat
     */
    DatabaseReference chatReference;

    /**
     * Firebase database reference to churches
     */
    DatabaseReference churchReference;

    /**
     * The church this chat activity is linked to
     */
    Church church;

    /**
     * The Firebase ID used to retreive linked church object
     */
    String churchID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbarChat);
        toolBarImage = findViewById(R.id.chatToolBarImage);
        toolBarText = findViewById(R.id.chatToolBarText);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSendMessage = findViewById(R.id.btnSendMessage);
        lvChat = findViewById(R.id.lvChat);

        Intent intent = getIntent();
        churchID = intent.getStringExtra("churchID");

        renderToolbar();
        renderChatThread();

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    @Override
    protected void onLogin() {}


    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Creates and Sends a message string to Firebase Database. Associates the message with the current firebase user
     */
    private void sendMessage() {
        String message = editTextMessage.getText().toString();

        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "You must enter a message.", Toast.LENGTH_LONG).show();
            return;
        }

        createMessage(message);
    }

    /**
     * Creates a ChatMessage object using @param message, links it to current firebase user and pushes message to firebase.
     * @param message
     */
    private void createMessage(String message) {
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

    /**
     * Renders the Toolbar, sets Toolbar Image & Text
     */
    private void renderToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
                Log.e(TAG, "Error Loading Church From Firebase", databaseError.toException());
            }
        });
    }

    /**
     * Loads the @param churchName's associated image from @url FirebaseStorage and sets loaded image to @param image
     * @param url - url the image stored in FireStore
     * @param churchName - the church this image is associated with
     * @param image - CircleImageView being set
     */
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

    /**
     * Renders the Chat by populating the ListView with ChatMessages obtained from Firebase.
     * Sets a listener to update Chat List View whenever a new ChatMessage is created and sent
     * to Firebase
     */
    private void renderChatThread() {
        adapter = new ChatListAdapter(ChatActivity.this, messagesList);
        lvChat.setAdapter(adapter);

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
    };
}
