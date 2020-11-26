package com.bcit.comp3717project;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends FireBaseActivity {

    private static int SPLASH_DELAY_TIME = 3000;

    Animation topAnim;
    Animation bottomAnim;
    ImageView topImage;
    TextView companyName;
    TextView companySlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initHooks();
    }

    private void initHooks() {
        // Initialize the animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_down);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_up);

        // Initialize the hooks
        topImage = findViewById(R.id.topLoadingImage);
        topImage.setAnimation(topAnim);

        companyName = findViewById(R.id.companyName);
        companyName.setAnimation(bottomAnim);

        companySlogan = findViewById(R.id.companySlogan);
        companySlogan.setAnimation(bottomAnim);

    }

    @Override
    protected void onLogin() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish(); // Remove this Activity from the Stack, as it should not be called again.
        }, SPLASH_DELAY_TIME);

    }

    @Override
    protected void onLogout() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish(); // Remove this Activity from the Stack, as it should not be called again.
        }, SPLASH_DELAY_TIME);
    }

}
