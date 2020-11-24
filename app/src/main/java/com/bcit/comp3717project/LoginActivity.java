package com.bcit.comp3717project;

import androidx.annotation.NonNull;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends FireBaseActivity {

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
            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();
                        onLogin();

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("email", email.getText().toString());
                        editor.putString("displayName", user.getDisplayName());
                        editor.commit();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class); //TODO change this to an appropriate activity
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    protected void onLogout() {

    }
}