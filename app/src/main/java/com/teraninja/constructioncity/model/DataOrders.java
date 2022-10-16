package com.teraninja.constructioncity.model;

import com.teraninja.constructioncity.Orders;

import java.util.ArrayList;

public class DataOrders {
    public int status;
    public int code;
    public String message;
    public ArrayList<OrdersModel> data;

    public DataOrders(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<OrdersModel> getData() {
        return data;
    }

    public void setData(ArrayList<OrdersModel> data) {
        this.data = data;
    }
}
