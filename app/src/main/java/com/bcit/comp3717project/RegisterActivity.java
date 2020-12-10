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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import model.User;

/**
 * Register activity registers a user to our app using firebase auth.
 */
public class RegisterActivity extends FireBaseActivity {

    SharedPreferences pref;

    //widgets and firebaseauth
    EditText FullName, UserEmail, Password, PasswordConfirm;
    final int MIN_PASSWORD_LENGTH = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);

        FullName = findViewById(R.id.FullName);
        UserEmail = findViewById(R.id.UserEmail);
        Password = findViewById(R.id.emailPasswordTextField);
        PasswordConfirm = findViewById(R.id.rePassword);

        makeLinks();
    }

    @Override
    protected void onLogin() {}

    @Override
    protected void onLogout() {}

    /**
     * Registers a user to firebase and the app
     * @param view - The view this onclick listener is attached to
     */
    public void register(View view) {

        String fullName = FullName.getText().toString().trim();
        String email = UserEmail.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String confirmPassword = PasswordConfirm.getText().toString().trim();

        /**
         * Following if's to check for text fields and password authentication.
         */
        if (!requiredFieldsAreFilled()) {
            return;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            Password.setError(getResources().getString(R.string.minPasswordLength));
        }

        if (!password.equals(confirmPassword)) {
            PasswordConfirm.setError(getResources().getString(R.string.identicalPasswords));
        }

        else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    updateUserDisplayName(fullName, firebaseUser);

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("email", email);
                    editor.putString("displayName", fullName);
                    editor.commit();

                    String userID = firebaseUser.getUid();
                    User newUser = new User(userID, fullName, fullName, email);
                    DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference("users");
                    usersReference.child(userID).setValue(newUser);
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.userCreated), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, ReligionSelectionActivity.class);
                    startActivity(i);
                    finish(); // Finish the Register Activity, the user should not be able to go back to this screen once logged in.
                } else {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.errorCreateUser) + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * To check if text fields for registration activity are filled.
     * @return boolean if text fields are filled
     */
    private boolean requiredFieldsAreFilled() {
        boolean nameIsFilled = true;
        boolean emailIsFilled = true;
        boolean passwordIsFilled = true;
        boolean confirmIsFilled = true;

        if(TextUtils.isEmpty(FullName.getText().toString().trim())) {
            FullName.setError(getResources().getString(R.string.emailRequired));
            nameIsFilled = false;
        }

        if(TextUtils.isEmpty(UserEmail.getText().toString().trim())) {
            UserEmail.setError(getResources().getString(R.string.emailRequired));
            emailIsFilled = false;
        }

        if(TextUtils.isEmpty(Password.getText().toString().trim())) {
            Password.setError(getResources().getString(R.string.passwordRequired));
            passwordIsFilled = false;
        }

        if(TextUtils.isEmpty(PasswordConfirm.getText().toString().trim())) {
            PasswordConfirm.setError(getResources().getString(R.string.passwordRequired));
            confirmIsFilled = false;
        }

        return nameIsFilled && emailIsFilled && passwordIsFilled && confirmIsFilled;
    }

    private void updateUserDisplayName(String displayName, FirebaseUser user) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build();
        user.updateProfile(profileUpdates);
    }

    /**
     * Makes link for login activity if user already has an existing account
     */
    private void makeLinks() {

        SpannableString string = new SpannableString(getResources().getString(R.string.loginRedirectText));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        };

        string.setSpan(clickableSpan, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView t = findViewById(R.id.loginRedirectText);
        t.setText(string);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        t.setHighlightColor(Color.TRANSPARENT);
    }

}