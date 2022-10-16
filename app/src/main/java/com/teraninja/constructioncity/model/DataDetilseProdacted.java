package com.teraninja.constructioncity.model;

public class DataDetilseProdacted {
    public int status;
    public int code;
    public String message;
    public DataDetilse data;

    public DataDetilseProdacted(int status, String message) {
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

    public DataDetilse getData() {
        return data;
    }

    public void setData(DataDetilse data) {
        this.data = data;
    }
}
