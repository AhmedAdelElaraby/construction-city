package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class ModelAllCard {
    public int id;
    public String owner_name;
    public String name;
    public int number_company;
    public String mobile;
    public AddressModel address;
    public ArrayList<ImageProdacted> images;

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

    public int getNumber_company() {
        return number_company;
    }

    public void setNumber_company(int number_company) {
        this.number_company = number_company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public ArrayList<ImageProdacted> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageProdacted> images) {
        this.images = images;
    }
}
