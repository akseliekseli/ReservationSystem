package com.example.reservationsystem;

import com.google.firebase.database.FirebaseDatabase;

public class User {
    private String name, phone, email;

    public User(){

    }

    public User (String name,String phone, String email){
        this.email = email;
        this.name = name;
        this.phone = phone;

    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }

    public String getEmail() {
        return email;
    }
    public void updateName(String name){
        this.name=name;
    }
    public void updatePhone(String phone){
        this.phone=phone;
    }
}
