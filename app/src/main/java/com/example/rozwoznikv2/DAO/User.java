package com.example.rozwoznikv2.DAO;

public class User {
    private String email;

    public User(){};

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
