package com.example.awmrapp;

public class User {
    String  customer_id,lat,longit,name,prev_data;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongit() {
        return longit;
    }

    public void setLongit(String longit) {
        this.longit = longit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrev_data() {
        return prev_data;
    }

    public void setPrev_data(String prev_data) {
        this.prev_data = prev_data;
    }
}
