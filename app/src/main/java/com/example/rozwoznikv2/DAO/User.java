package com.example.rozwoznikv2.DAO;

import java.util.UUID;

/**
 * Model użytkownika
 */
public class User {

    /**
     * Pole email
     */
    private String email;

    /**
     * Pole hasło
     */
    private String password;

    /**
     * Pole id
     */
    private String id;

    public User(){
        setId();
    };

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() { return id; }

    public void setId() { id = UUID.randomUUID().toString(); }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
