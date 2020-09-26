package com.bcit.comp3717project;

import android.app.Application;
import android.util.Log;

import com.bcit.comp3717project.BuildConfig;


import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application{

    String appID = BuildConfig.APPLICATION_ID; // replace this with your App ID
    private static final String TAG = "Application";


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this); // context, usually an Activity or Application
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Log.e(TAG, "TEsting");
    }

}
