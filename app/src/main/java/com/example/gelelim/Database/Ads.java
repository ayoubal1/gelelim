package com.example.gelelim.Database;

import com.example.gelelim.FireCloud.IFirebase;

public class Ads implements IFirebase {

    String id;
    String driverid;
    String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String plaka;
    String type;




    @Override
    public String TableName() {
        return "ADS";
    }
}
