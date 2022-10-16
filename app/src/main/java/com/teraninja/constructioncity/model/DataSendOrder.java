package com.teraninja.constructioncity.model;

public class DataSendOrder {
    String company_id;
    String phone;
    String promo_code;
    String user_addresses_id;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPromo_code() {
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    public String getUser_addresses_id() {
        return user_addresses_id;
    }

    public void setUser_addresses_id(String user_addresses_id) {
        this.user_addresses_id = user_addresses_id;
    }
}
