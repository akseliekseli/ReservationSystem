package com.example.reservationsystem;

import java.util.ArrayList;

public class Hall {
    IODatabase io_hall = IODatabase.getInstance();
    /// Lets use singleton principle to make only one instance of Hall
    private static Hall hall = new Hall();
    ArrayList<Reservation> all_res = new ArrayList<Reservation>();
    private Hall(){
    }
    public static Hall getInstance(){
        return hall;
    }

    public void addReservation(String sport, String room, int day, int month, int year, int start_time){
        DateAndTime time = new DateAndTime(day,month,year,start_time);
        Reservation newRes = new Reservation(sport,room,time);
        io_hall.addReserToDB(newRes);

    }
}
