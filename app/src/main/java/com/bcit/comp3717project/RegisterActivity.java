package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

import io.realm.Realm;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.App.Result;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}