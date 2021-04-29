package com.example.rozwoznikv2.DAO;

import java.util.UUID;

public class Announcement {

    private String ID;
    private String userID;
    private String title;
    private String category;
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
