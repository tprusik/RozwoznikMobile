package com.example.rozwoznikv2.DAO;

import java.util.UUID;

public class UserData {

    private String userID;
    private String Id;
    private String name;
    private String phone;

public UserData(){
    setID();
}

    public void setUserID(String userID) { this.userID = userID; }

    public String getUserID() { return userID; }

    public String getID() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setID() { Id = UUID.randomUUID().toString();}

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) { this.phone = phone; }


}
