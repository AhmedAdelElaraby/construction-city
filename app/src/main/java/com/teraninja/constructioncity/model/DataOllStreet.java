package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class DataOllStreet {

    public int status;
    public int code;
    public String message;
    public ArrayList<OllStreet> data;

    public DataOllStreet(int status, String message) {
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

    public ArrayList<OllStreet> getData() {
        return data;
    }

    public void setData(ArrayList<OllStreet> data) {
        this.data = data;
    }
}
