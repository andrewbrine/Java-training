package com.matcher.matcher.entity.order;

public class OrderDetails {
    private String username;
    private int quantity;

    public OrderDetails(String username, int quantity){
        this.username = username;
        this.quantity = quantity;
    }

    public String getDetailsUsername(){
        return username;
    }

    public void setDetailsUsername(String username) {
        this.username = username;
    }

    public int getDetailsQuantity(){
        return quantity;
    }

    public void setDetailsQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeOrderQuantity(int amount){
        if (quantity<amount){
            throw new IllegalArgumentException("Cannot set negative quantity");
        } else {
            quantity -= amount;
        }
    }
}
