package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import model.MapListing;

public class MapActivity extends AppCompatActivity {

    private ListView listOfChurches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ArrayAdapter<MapListing> arrayAdapter = new ArrayAdapter<MapListing>(this,android.R.layout.simple_list_item_1, MapListing.testListings);
        listOfChurches = findViewById(R.id.listOfChurches);
        listOfChurches.setAdapter(arrayAdapter);
    }
}