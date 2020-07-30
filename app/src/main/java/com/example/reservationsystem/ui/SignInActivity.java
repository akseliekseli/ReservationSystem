package com.example.reservationsystem.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservationsystem.HomeActivity;
import com.example.reservationsystem.IODatabase;
import com.example.reservationsystem.LoginActivity;
import com.example.reservationsystem.MainActivity;
import com.example.reservationsystem.R;
import com.example.reservationsystem.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    EditText emailId, password, nameT, phoneT;
    Button btnLogin;
    TextView tvLogin;
    IODatabase io;
    FirebaseAuth mAuth;
    private String email;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        io = IODatabase.getInstance();
        emailId = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        nameT = findViewById(R.id.editTextTextPersonName);
        phoneT = findViewById(R.id.editTextPhone);
        btnLogin = findViewById(R.id.button_login);
        tvLogin = findViewById(R.id.loginText);
        /// Onclicklistener for our button that checks if user has entered email and password and then
        /// creates an account with Firebase's createUserWithEmailAndPassword
        /// After that the app changes to home screen.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailId.getText().toString();
                pwd = password.getText().toString();
                final String pho = phoneT.getText().toString();
                final String nam = nameT.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Enter email, please");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Enter password, please");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Enter email and password, please", Toast.LENGTH_SHORT).show();

                }
                else if(!email.isEmpty() && !pwd.isEmpty()) {
                    if(!pho.isEmpty() && !nam.isEmpty()){
                        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignInActivity.this, "SignIn Unsuccesful, please try again.", Toast.LENGTH_SHORT).show();
                                } else {

                                    /// Creating user node to database using Firebases's user ID as an ID
                                    String new_user = mAuth.getCurrentUser().getUid();
                                    String new_email = mAuth.getCurrentUser().getEmail();
                                    User n_user = new User(nam,pho,new_email);
                                    io.createUser(new_user,n_user);
                                    startActivity(new Intent(SignInActivity.this, HomeActivity.class));

                                }
                            }

                        });
                    }
                    else{
                        Toast.makeText(SignInActivity.this, "Give name and phone number first, please", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignInActivity.this, "An error ocurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /// Changing from Signin screen to Login screen
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

}