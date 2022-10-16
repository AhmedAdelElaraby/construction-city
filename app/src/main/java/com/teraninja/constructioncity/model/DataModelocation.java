package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class DataModelocation {
    public int status;
    public int code;
    public ArrayList<LocationModel> data;

    public DataModelocation(int status) {
        this.status = status;
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

    public ArrayList<LocationModel> getData() {
        return data;
    }

    public void setData(ArrayList<LocationModel> data) {
        this.data = data;
    }
}
