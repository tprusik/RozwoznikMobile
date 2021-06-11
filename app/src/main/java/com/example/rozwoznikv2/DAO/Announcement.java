package com.example.rozwoznikv2.DAO;

import java.util.UUID;

/**
 * Model ogłoszenia
 */
public class Announcement {

    /**
     * Pole ID
     */
    private String ID;
    /**
     * Pole id do danych konta usera.
     */
    private String userID;
    /**
     * Pole tytuł
     */
    private String title;

    /**
     * Pole kategoria
     */
    private String category;
    /**
     * Pole opis ogłoszenia
     */
    private String description;

    
    public Announcement(){
        setID();
    }

    public String getID() {
        return ID;
    }

    public String  getUserID() {
        return userID;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    private void setID() {
        this.ID  = UUID.randomUUID().toString();
    }

    public void setUserID(String  userID) {
        this.userID = userID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
