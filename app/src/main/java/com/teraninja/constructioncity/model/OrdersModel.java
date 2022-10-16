package com.teraninja.constructioncity.model;

public class OrdersModel {
    public int id;
    public int user_id;
    public int user_addresses_id;
    public String status;
    public int added_tax_id;
    public int delivery_value_id;
    public int company_id;
    public int promo_code_id;
    public String phone;
    public Object delivery_before;
    public int sub_total;
    public int total;
    public String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_addresses_id() {
        return user_addresses_id;
    }

    public void setUser_addresses_id(int user_addresses_id) {
        this.user_addresses_id = user_addresses_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAdded_tax_id() {
        return added_tax_id;
    }

    public void setAdded_tax_id(int added_tax_id) {
        this.added_tax_id = added_tax_id;
    }

    public int getDelivery_value_id() {
        return delivery_value_id;
    }

    public void setDelivery_value_id(int delivery_value_id) {
        this.delivery_value_id = delivery_value_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getPromo_code_id() {
        return promo_code_id;
    }

    public void setPromo_code_id(int promo_code_id) {
        this.promo_code_id = promo_code_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getDelivery_before() {
        return delivery_before;
    }

    public void setDelivery_before(Object delivery_before) {
        this.delivery_before = delivery_before;
    }

    public int getSub_total() {
        return sub_total;
    }

    public void setSub_total(int sub_total) {
        this.sub_total = sub_total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
