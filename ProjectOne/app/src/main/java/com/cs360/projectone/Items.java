package com.cs360.projectone;

public class Items {
    private String id;
    private String itemName;
    private int quantity;
    private String location;

    public Items(String id, String itemName, int quantity, String location) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.location = location;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
