package com.example.l.TripFlash.model;

public class DestSpot {


    private int id;
    private String name;
    private String location;

    public DestSpot(int id, String name, String location){
        this.id=id;
        this.name=name;
        this.location=location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
