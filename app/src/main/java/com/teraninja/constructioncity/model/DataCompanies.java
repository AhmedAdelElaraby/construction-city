package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class DataCompanies {
    public int id;
    public String owner_name;
    public String name;
    public int mobile;
    public String address;
    public ArrayList<ImageCompanies> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<ImageCompanies> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageCompanies> images) {
        this.images = images;
    }
}
