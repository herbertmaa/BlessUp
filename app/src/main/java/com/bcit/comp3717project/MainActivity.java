package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:<password>@cluster1.8lepj.mongodb.net/<dbname>?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");

    }
}