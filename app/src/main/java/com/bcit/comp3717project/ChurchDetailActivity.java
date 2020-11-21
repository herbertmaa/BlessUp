package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import model.Church;

public class ChurchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church_detail);
        displayChurchDetails();
    }


    private void displayChurchDetails(){
        Church church = (Church) getIntent().getExtras().get("church");

        if(church != null){
            TextView church_name = findViewById(R.id.church_name);
            church_name.setText(church.getName());

            TextView church_description = findViewById(R.id.church_description);
            church_description.setText(church.getDescription());

            TextView church_address = findViewById(R.id.church_address);
            church_address.setText(church.getAddress());

            TextView church_number = findViewById(R.id.church_number);
            church_address.setText(church.getPhoneNumber());
//            ImageView church_image = findViewById(R.id.church_image);
//            church_image.setImageDrawable(ContextCompat.getDrawable(this,);
//            church_image.setContentDescription(church.getName());
        }


    }
}