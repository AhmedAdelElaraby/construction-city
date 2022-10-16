package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class DataOrder {
    public Order order;
    public String date_start_order;
    public String delivery_before;
    public String start_order;
    public String prepared_order;
    public String in_way_order;

    public AddressData address;
    public ArrayList<ProductDetilseOrser> products;
    public RateDetils rate;

    public String getDate_start_order() {
        return date_start_order;
    }

    public void setDate_start_order(String date_start_order) {
        this.date_start_order = date_start_order;
    }

    public String getDelivery_before() {
        return delivery_before;
    }

    public void setDelivery_before(String delivery_before) {
        this.delivery_before = delivery_before;
    }

    public String getStart_order() {
        return start_order;
    }

    public void setStart_order(String start_order) {
        this.start_order = start_order;
    }

    public String getPrepared_order() {
        return prepared_order;
    }

    public void setPrepared_order(String prepared_order) {
        this.prepared_order = prepared_order;
    }

    public String getIn_way_order() {
        return in_way_order;
    }

    public void setIn_way_order(String in_way_order) {
        this.in_way_order = in_way_order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public AddressData getAddress() {
        return address;
    }

    public void setAddress(AddressData address) {
        this.address = address;
    }

    public ArrayList<ProductDetilseOrser> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductDetilseOrser> products) {
        this.products = products;
    }

    public RateDetils getRate() {
        return rate;
    }

    public void setRate(RateDetils rate) {
        this.rate = rate;
    }
}
