package com.example.quakereport;

import java.util.Date;

public class Earthquake {
    private double mag;
    private String place;
    private long time;
    private String url;




    public Earthquake(double mag, String place,  long time , String url) {
        this.mag = mag;
        this.place = place;
        this.time = time;
        this.url = url;
    }

    public String getUrl() {
        return url;
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




}
