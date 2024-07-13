package com.CS360.stocksense.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String password;
    private String role;
    private String phoneNumber; // New attribute
    private boolean isEnrolledInSMS; // New attribute

    public User(String username, String password, String role, String phoneNumber, boolean isEnrolledInSMS) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.isEnrolledInSMS = isEnrolledInSMS;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnrolledInSMS() {
        return isEnrolledInSMS;
    }

    public void setEnrolledInSMS(boolean enrolledInSMS) {
        isEnrolledInSMS = enrolledInSMS;
    }
}
