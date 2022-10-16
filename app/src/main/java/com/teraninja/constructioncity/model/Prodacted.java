package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class Prodacted {
    public int id;
    public String name;
    public int price;
    public int company_id;
    public String company_name;
    public String status;
    public String description;
    public String max_qty;
    public ArrayList<ImageProdacted> image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMax_qty() {
        return max_qty;
    }

    public void setMax_qty(String max_qty) {
        this.max_qty = max_qty;
    }

    public ArrayList<ImageProdacted> getImage() {
        return image;
    }

    public void setImage(ArrayList<ImageProdacted> image) {
        this.image = image;
    }
}
