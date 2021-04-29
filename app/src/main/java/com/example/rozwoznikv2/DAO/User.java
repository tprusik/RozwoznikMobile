package com.example.rozwoznikv2.DAO;

import java.util.UUID;

public class User {
    private String email;

    public User(){
        setId();
    };

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private String Id;

    public String getId() { return Id; }

    public void setId() { this.Id =   UUID.randomUUID().toString(); }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
