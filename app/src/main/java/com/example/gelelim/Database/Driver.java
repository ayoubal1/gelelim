package com.example.gelelim.Database;

import com.example.gelelim.FireCloud.IFirebase;

public class Driver implements IFirebase {
    String id;
    String name;
    String tel;
    String adress;
    String Tip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String getTip() { return Tip; }
    public void setTip(String tip) { Tip = tip; }

    @Override
    public String TableName() {
        return "Driver";
    }
}
