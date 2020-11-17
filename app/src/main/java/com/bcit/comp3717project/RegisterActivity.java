package com.bcit.comp3717project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.User;


public class RegisterActivity extends FireBaseActivity {

    //widgets and firebaseauth
    EditText FullName, UserEmail, Password, PasswordConfirm;
    Button Register;
    DatePicker dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FullName = findViewById(R.id.FullName);
        UserEmail = findViewById(R.id.UserEmail);
        Password = findViewById(R.id.emailPasswordTextField);
        PasswordConfirm = findViewById(R.id.rePassword);


        //add back once login button is implemented.
//        if(fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

    }

    public void register(View view) {

        String fullName = findViewById(R.id.FullName).toString().trim();
        String email = UserEmail.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String confirmPassword = PasswordConfirm.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            UserEmail.setError("Email is Required.");
            return;
        }

        if(TextUtils.isEmpty(fullName)){
            FullName.setError("Name is Required.");
            return;
        }

        if(TextUtils.isEmpty(password)){
            Password.setError("Password is Required");
            return;
        }

        if(password.length() < 6){
            Password.setError("Password must be at least 6 characters.");
        }

        if (!password.equals(confirmPassword)) {
            PasswordConfirm.setError("Both passwords must be identical.");
        }

        else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    String userID = user.getUid();
                    User newUser = new User(userID, fullName, fullName, email);
                    DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference("users");
                    usersReference.child(userID).setValue(newUser);
                    Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ReligionSelectionActivity.class));

                } else {
                    Toast.makeText(RegisterActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}