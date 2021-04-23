package com.example.rozwoznikv2.DAO;

public class UserData {

    private long Id;
    private String name;
    private String phone;

public UserData(){

}
    public long getID() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    private void setID() {
        Id = System.currentTimeMillis();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
