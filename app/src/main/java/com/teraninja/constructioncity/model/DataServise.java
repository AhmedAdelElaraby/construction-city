package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class DataServise {
    public int status;
    public int code;
    public String message;
    public ArrayList<ServiseData> data;

    public DataServise(int status, String message) {
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

    public ArrayList<ServiseData> getData() {
        return data;
    }

    public void setData(ArrayList<ServiseData> data) {
        this.data = data;
    }
}
