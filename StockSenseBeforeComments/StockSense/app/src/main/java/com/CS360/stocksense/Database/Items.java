package com.CS360.stocksense.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Items {

    @PrimaryKey
    private int id;

    private String itemName;
    private int quantity;
    private String location;
    private int alertLevel;
    private long lastAlertTimestamp;
    public Items(int id, String itemName, int quantity, String location, int alertLevel) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.location = location;
        this.alertLevel = alertLevel;
        lastAlertTimestamp = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(int alertLevel) {
        this.alertLevel = alertLevel;
    }

    public long getLastAlertTimestamp() {
        return lastAlertTimestamp;
    }

    public void setLastAlertTimestamp(long lastAlertTimestamp) {
        this.lastAlertTimestamp = lastAlertTimestamp;
    }

    public boolean isLowInventory() {
        return this.quantity < this.alertLevel;
    }
}
