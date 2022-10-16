package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class ProductDetilseOrser {
    public int id;
    public int order_id;
    public String total;
    public ArrayList<ProductDetilsOrderses> product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<ProductDetilsOrderses> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<ProductDetilsOrderses> product) {
        this.product = product;
    }
}
