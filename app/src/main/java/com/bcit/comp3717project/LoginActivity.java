package com.bcit.comp3717project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends FireBaseActivity{

    private static final String TAG = "MainActivity";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        makeLinks();
    }

    public void logIn(View view) {
        EditText email = findViewById(R.id.emailTextField);
        EditText password = findViewById(R.id.emailPasswordTextField);
        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString());
        }
    }

    private void makeLinks() {

        SpannableString string = new SpannableString(getResources().getString(R.string.registerRedirectText));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        };

        string.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView t = findViewById(R.id.registerRedirectText);
        t.setText(string);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        t.setHighlightColor(Color.TRANSPARENT);
    }


    @Override
    protected void onLogin(){
        Log.e("FUCK", "CALLED ALOT");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class); //TODO change this to an appropriate activity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    protected void onLogout(){

    }


}