package com.bcit.comp3717project;

import androidx.annotation.NonNull;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import io.supercharge.shimmerlayout.ShimmerLayout;
import model.Church;
import model.User;

public class ChurchDetailActivity extends FireBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church_detail);
        getSupportActionBar().hide();
        displayChurchDetails();
    }

    private void displayChurchDetails() {
        Church church = (Church) getIntent().getExtras().get("church");


        if (church != null) {
            TextView church_name = findViewById(R.id.church_name);
            church_name.setText(church.getName());

            TextView church_description = findViewById(R.id.church_description);
            church_description.setText(church.getDescription());

            TextView church_address = findViewById(R.id.church_address);
            church_address.setText(church.getAddress());

            TextView church_number = findViewById(R.id.church_number);
            church_number.setText(church.getPhoneNumber());

            ImageView church_image = findViewById(R.id.church_image);
            loadImageView(church.getImageURL(), church.getName(), church_image);
        }

    }

    private void loadImageView(String url, String churchName, ImageView v) {

        try {
            StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child(url);
            final File localFile = File.createTempFile(churchName, "png");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());

                                if (v != null) {
                                    v.setImageBitmap(bitmap);
                                    ShimmerLayout skeleton = (ShimmerLayout) this.findViewById(R.id.shimmer_wrapper);
                                    skeleton.stopShimmerAnimation();
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onJoinChurch(View view) {

        Church church = (Church) getIntent().getExtras().get("church");
        String userID = auth.getCurrentUser().getUid();
        DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference("users");

        usersReference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);
                church.addUser(u);
                FirebaseDatabase.getInstance().getReference("churches").child(church.getChurchID()).setValue(church);
                Toast toast = Toast.makeText(getApplicationContext(), "You've joined this church!", Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        Toast.makeText(ReligionSelectionActivity.this, "You've chosen " + religionChoice, Toast.LENGTH_SHORT).show();
    }
}