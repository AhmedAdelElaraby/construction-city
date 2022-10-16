package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class AllPremocod {
    public int id;
    public String code;
    public int discount;
    public ArrayList<Company> company;
    public City city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ArrayList<Company> getCompany() {
        return company;
    }

    public void setCompany(ArrayList<Company> company) {
        this.company = company;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
