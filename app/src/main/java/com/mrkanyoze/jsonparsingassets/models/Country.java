package com.mrkanyoze.jsonparsingassets.models;

/**
 * Created by Victor Kanyoze on 9/26/2018.
 */

public class Country {
    String country_id = "";
    String country = "";
    String telephone_code = "";

    public Country() {
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone_code() {
        return telephone_code;
    }

    public void setTelephone_code(String telephone_code) {
        this.telephone_code = telephone_code;
    }
}