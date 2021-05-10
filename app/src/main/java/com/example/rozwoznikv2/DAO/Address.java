package com.example.rozwoznikv2.DAO;

public class Address {

    private String userID;
    private String City;
    private String ZipCode;

    public String getUserID() {
        return userID;
    }

    public String getCity() {
        return City;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }
}
