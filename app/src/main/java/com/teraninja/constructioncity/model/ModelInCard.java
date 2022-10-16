package com.teraninja.constructioncity.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelInCard {
    public ArrayList<ProductModelInCard> products;
    public int subtotal;
    public int tax;
    @SerializedName("DeliveryValue")
    public int deliveryValue;
    public int total;
    public String name_user;
    public String phone_user;
    public AddressModel address;

    public ArrayList<ProductModelInCard> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductModelInCard> products) {
        this.products = products;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getDeliveryValue() {
        return deliveryValue;
    }

    public void setDeliveryValue(int deliveryValue) {
        this.deliveryValue = deliveryValue;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(String phone_user) {
        this.phone_user = phone_user;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
