package com.bcit.comp3717project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends FireBaseActivity{

    private static final String TAG = "MainActivity";

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailTextField);
        password = findViewById(R.id.emailPasswordTextField);
        makeLinks();
    }

    public void logIn(View view) {
        EditText email = findViewById(R.id.emailTextField);
        EditText password = findViewById(R.id.emailPasswordTextField);
        if (requiredFieldsAreFilled()) {
            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString());
            checkUserExists();
        }
    }

    private boolean requiredFieldsAreFilled() {
        boolean emailIsFilled = true;
        boolean passwordIsFilled = true;

        if(TextUtils.isEmpty(email.getText().toString())) {
            email.setError(getResources().getString(R.string.emailRequired));
            emailIsFilled = false;
        }

        if(TextUtils.isEmpty(password.getText().toString())) {
            password.setError(getResources().getString(R.string.passwordRequired));
            passwordIsFilled = false;
        }

        return emailIsFilled && passwordIsFilled;
    }

    private void checkUserExists() {
        if (auth.getCurrentUser() == null) {
            String incorrectCredentials = getResources().getString(R.string.incorrectCredentials);
            Toast.makeText(LoginActivity.this, incorrectCredentials, Toast.LENGTH_SHORT).show();
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
    protected void onLogin() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        String loggingIn = getResources().getString(R.string.loggingIn);
        Toast.makeText(LoginActivity.this, loggingIn, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    protected void onLogout() {}


}