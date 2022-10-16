package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class DataSearchToProdacted {
    public int status;
    public int code;
    public String message;
    public ArrayList<SearchProdacted> data;

    public DataSearchToProdacted(int status, String message) {
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

    public ArrayList<SearchProdacted> getData() {
        return data;
    }

    public void setData(ArrayList<SearchProdacted> data) {
        this.data = data;
    }
}
