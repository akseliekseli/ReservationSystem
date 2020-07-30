package com.example.reservationsystem;

/// The hall can be reserved with 1-hour blocks
public class DateAndTime {
    int day;
    int month;
    int year;
    int start_time;

    public DateAndTime(int day, int month, int year, int start_time){
        this.day=day;
        this.month=month;
        this.year=year;
        this.start_time=start_time;

    }

}
