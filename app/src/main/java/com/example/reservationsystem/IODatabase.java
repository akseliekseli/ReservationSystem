package com.example.reservationsystem;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class IODatabase {
    FirebaseDatabase rootNode;
    DatabaseReference referene;
    private User loaded_user;

    private static IODatabase io = new IODatabase();

    public IODatabase(){
        rootNode = FirebaseDatabase.getInstance();
        referene = rootNode.getReference("users");
    }
    public static IODatabase getInstance(){
        return io;
    }
    public void createUser (String user_uid, User user){
        rootNode.getReference("users").child(user_uid).setValue(user);
    }
    public void addReserToDB(Reservation newRes){
        rootNode.getReference("reservations").setValue(newRes);
    }




}
