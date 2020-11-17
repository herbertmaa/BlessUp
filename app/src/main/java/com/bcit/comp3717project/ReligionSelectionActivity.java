package com.bcit.comp3717project;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReligionSelectionActivity extends AppCompatActivity {

    Button btnBhud, btnChrist, btnJuda, btnIslam, btnHindu;
    Animation scaleUp, scaleDown;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion_selection);

        btnBhud = findViewById(R.id.btnBuddhism);
        btnChrist = findViewById((R.id.btnChristianity));
        btnJuda = findViewById((R.id.btnJudaism));
        btnIslam = findViewById((R.id.btnIslam));
        btnHindu = findViewById((R.id.btnHinduism));

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        btnBhud.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    btnBhud.startAnimation(scaleUp);
                }
                else if (motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    btnBhud.startAnimation(scaleDown);
                }
                return true;
            }
        });

        btnChrist.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    btnChrist.startAnimation(scaleUp);
                }
                else if (motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    btnChrist.startAnimation(scaleDown);
                }
                return true;
            }
        });

        btnJuda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    btnJuda.startAnimation(scaleUp);
                }
                else if (motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    btnJuda.startAnimation(scaleDown);
                }
                return true;
            }
        });

        btnIslam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    btnIslam.startAnimation(scaleUp);
                }
                else if (motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    btnIslam.startAnimation(scaleDown);
                }
                return true;
            }
        });

        btnHindu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    btnHindu.startAnimation(scaleUp);
                }
                else if (motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    btnHindu.startAnimation(scaleDown);
                }
                return true;
            }
        });
    }

    public void onConfirmClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
