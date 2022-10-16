package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class Company {
    public int id;
    public int owner_id;
    public String name;
    public String mobile;
    public String address;
    public String whats;
    public String commission;
    public int number_company;
    public String logo;
    public Object slog;
    public ArrayList<ImageProdacted> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWhats() {
        return whats;
    }

    public void setWhats(String whats) {
        this.whats = whats;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public int getNumber_company() {
        return number_company;
    }

    public void setNumber_company(int number_company) {
        this.number_company = number_company;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Object getSlog() {
        return slog;
    }

    public void setSlog(Object slog) {
        this.slog = slog;
    }

    public ArrayList<ImageProdacted> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageProdacted> images) {
        this.images = images;
    }
}
