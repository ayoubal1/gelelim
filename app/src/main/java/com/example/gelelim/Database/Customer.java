package com.example.gelelim.Database;

import com.example.gelelim.FireCloud.IFirebase;

public class Customer implements IFirebase {
    String id;
    String tel;
    String adress;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String TableName() {
        return "Customer";
    }
}
