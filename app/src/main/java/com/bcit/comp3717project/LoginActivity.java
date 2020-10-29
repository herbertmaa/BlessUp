package com.bcit.comp3717project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LoginManager loginManager;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private User user;
    private String appID = BuildConfig.MONGODB_APPID;
    private App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Realm.init(this);
        app = new App(new AppConfiguration.Builder(appID).build());
        callbackManager = CallbackManager.Factory.create();

        Log.v(TAG, "Displaying main activity contents");
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override/**/
    protected void onStart(){
        super.onStart();
        Log.v(TAG, getLineNumber() + "Starting main activity");

        loginButton = findViewById(R.id.login_button);
        if(loginButton != null)
        {
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.v(TAG, "successful login");
                }

                @Override
                public void onCancel() {
                    Log.v(TAG, "user cancelled");
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.v(TAG, "facebook error");
                }
            });
        }
        else{
            Log.v(TAG, "login button is null zzz wtf?");

        }
    }

    public void logOut(View view) {
        if(user != null){
            user.logOutAsync(new App.Callback<User>(){
                public void onResult(Result<User> callback ) {

                    Log.v(TAG, getLineNumber() + user.getState().toString());
                    if (callback.isSuccess()) {
                        Log.v(TAG, "Successfully logged out user");
                        Log.v(TAG, getLineNumber() + user.getState().toString());
                        user = null;

                    } else {
                        Log.v(TAG, callback.getError().toString());
                        Log.v(TAG, getLineNumber() + user.getState().toString());

                    }
                }
            });
        }
    }

    public void logIn(View view) {

        Credentials anonymousCredentials = Credentials.anonymous();

        if (user != null) {
            Log.v(TAG, getLineNumber() + "Not trying to login as user exists: " + user.getState().toString());
            return;
        }

        app.loginAsync(anonymousCredentials, new App.Callback<User>() {
            @Override
            public void onResult(Result<User> callback) {
                if (callback.isSuccess()) {
                    Log.v(TAG, getLineNumber() + "Successfully authenticated anonymously.");
                    user = app.currentUser();
                    Log.v(TAG, "USER LOGGED IN: " + user.getId());
                    Toast toast = Toast.makeText(getApplicationContext(), "User logged in: " + user.getId(), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Log.v(TAG, getLineNumber() + callback.getError().toString());
                }
            }
        });
    }

    public static String getLineNumber(){
        return Thread.currentThread().getStackTrace()[2].getLineNumber() + ": ";
    }


    public void registerUser(String email, String password){

        app.getEmailPassword().registerUserAsync(email, password, it ->{

            if (it.isSuccess()) {
                Log.i(TAG,"Successfully registered user.");
            } else {
                Log.e(TAG,"Failed to register user: ${it.error}");
            }

        });
    }
}