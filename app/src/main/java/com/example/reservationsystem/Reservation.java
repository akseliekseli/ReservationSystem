package com.example.reservationsystem;

import java.util.Date;

public class Reservation{
    DateAndTime time;
    String sport;
    String room;


    public Reservation(String sport_name, String room, DateAndTime time){

        this.sport=sport_name;
        this.time = time;
        this.room=room;
    }

    public DateAndTime getTime() {
        return time;
    }

    public void setTime(DateAndTime time) {
        this.time = time;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
