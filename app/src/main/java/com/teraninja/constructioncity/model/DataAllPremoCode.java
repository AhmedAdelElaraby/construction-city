package com.teraninja.constructioncity.model;

import java.util.ArrayList;

public class DataAllPremoCode {
    public boolean status;
    public ArrayList<AllPremocod> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<AllPremocod> getData() {
        return data;
    }

    public void setData(ArrayList<AllPremocod> data) {
        this.data = data;
    }
}
