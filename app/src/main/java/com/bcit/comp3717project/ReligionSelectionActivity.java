package com.bcit.comp3717project;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import java.util.ArrayList;

public class ReligionSelectionActivity extends AppCompatActivity {

    AppCompatImageButton btnBhud;
    AppCompatImageButton btnChrist;
    AppCompatImageButton btnJuda;
    AppCompatImageButton btnIslam;
    AppCompatImageButton btnHindu;
    ArrayList<View> religionButtons = new ArrayList<>();
    private GestureDetector gestureDetector;

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion_selection);
        gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        btnBhud = findViewById(R.id.btnBuddhism);
        btnChrist = findViewById((R.id.btnChristianity));
        btnJuda = findViewById((R.id.btnJudaism));
        btnIslam = findViewById((R.id.btnIslam));
        btnHindu = findViewById((R.id.btnHinduism));

        religionButtons.add(btnBhud);
        religionButtons.add(btnChrist);
        religionButtons.add(btnJuda);
        religionButtons.add(btnIslam);
        religionButtons.add(btnHindu);

        btnBhud.setOnTouchListener(initAnimateClickListener());

        btnChrist.setOnTouchListener(initAnimateClickListener());

        btnJuda.setOnTouchListener(initAnimateClickListener());

        btnIslam.setOnTouchListener(initAnimateClickListener());

        btnHindu.setOnTouchListener(initAnimateClickListener());

    }

    public void onConfirmClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private AppCompatImageButton.OnTouchListener initAnimateClickListener() {
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        View.OnTouchListener animateClick = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e("MM", Boolean.toString(gestureDetector.onTouchEvent(motionEvent)));
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    AlphaAnimation alphaAnim = new AlphaAnimation(0.2f, 1.0f);
                    alphaAnim.setDuration(1000);
                    alphaAnim.setStartOffset(200);
                    alphaAnim.setFillAfter(true);
                    view.startAnimation(alphaAnim);

                    for (View otherV : religionButtons) {
                        if (otherV.getId() == view.getId()) continue;
                        AlphaAnimation reverseAlphaAnim = new AlphaAnimation(1.0f, 0.2f);
                        reverseAlphaAnim.setDuration(1000);
                        reverseAlphaAnim.setStartOffset(500);
                        reverseAlphaAnim.setFillAfter(true);
                        otherV.startAnimation(reverseAlphaAnim);
                    }
                    return true;
                }
                return false;
            }

        };
        return animateClick;
    }


}
