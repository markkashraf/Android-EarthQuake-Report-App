package com.example.quakereport;

import java.util.Date;

public class Earthquake {
    private double mag;
    private String place;
    private String date;
    private long time;



    public Earthquake(double mag, String place, String date) {
        this.mag = mag;
        this.place = place;
        this.date = date;
    }
    public Earthquake(double mag, String place,  long time) {
        this.mag = mag;
        this.place = place;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }


}
