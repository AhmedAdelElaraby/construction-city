package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class ModelDataAllCity {
    public int status;
    public int code;
    public ArrayList<ModelCity> data;

    public ModelDataAllCity(int status) {
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

    public ArrayList<ModelCity> getData() {
        return data;
    }

    public void setData(ArrayList<ModelCity> data) {
        this.data = data;
    }
}
