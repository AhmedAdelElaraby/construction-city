package com.teraninja.constructioncity.model;

public class DataRenendcode {
    public int status;
    public int code;
    public int message;

    public DataRenendcode(int status, int message) {
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

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
