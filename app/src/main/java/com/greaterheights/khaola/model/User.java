package com.greaterheights.khaola.model;

public class User {
    private int user_id;
    private String email;
    private String password;
    private String privilege;
    private String creation_date;

    public User() {
    }

    public User(int user_id, String email, String password, String privilege, String creation_date) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.privilege = privilege;
        this.creation_date = creation_date;
    }

    public User(String email, String password, String privilege, String creation_date) {
        this.email = email;
        this.password = password;
        this.privilege = privilege;
        this.creation_date = creation_date;
    }

    public User(int user_id, String email, String password) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
