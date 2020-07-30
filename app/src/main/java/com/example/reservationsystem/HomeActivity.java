package com.example.reservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class HomeActivity extends AppCompatActivity {
    ReseveFragment resfrag = new ReseveFragment();
    Button logout_btn;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    TextView welText;
    IODatabase io_home = IODatabase.getInstance();
    Hall hall = Hall.getInstance();
    User loaded_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        /// Create hall using singleton.

        /// Using this listener to retrieve data from the database realtime.
        final String user_uid = mAuth.getCurrentUser().getUid();
        io_home.referene.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loaded_user = dataSnapshot.child(user_uid).getValue(User.class);
                welText.setText("Welcome "+loaded_user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Test");
            }
        });
        welText = findViewById(R.id.welcomeView);
        logout_btn = findViewById(R.id.logoutBtn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent toMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });

    }
    public void addReservation(View v){
        Fragment fragment;
        fragment = resfrag;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.reserveWindow,fragment);
        transaction.commit();
    }

    public void sendInfoToReserveFragment(){
        Bundle bundle = new Bundle();
        ReseveFragment resfrag = new ReseveFragment();
        resfrag.setArguments(bundle);
    }

}