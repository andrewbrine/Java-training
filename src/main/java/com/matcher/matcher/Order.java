package com.matcher.matcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class Order {
    @NotNull
    @Min(1)
    @Max(9999)
    private int account;
    @NotNull
    @Digits(integer = 100, fraction = 2)
    private double price;
    @NotNull
    private int quantity;
    @NotNull
    @Pattern(regexp = "buy|sell")
    private String action;


    public Order(@JsonProperty("account") int account, @JsonProperty("price") double price, @JsonProperty("quantity") int quantity, @JsonProperty("action") String action) {
        this.account = account;
        this.price= price;
        this.quantity = quantity;
        this.action = action;
    }

    @Override
    public String toString() {
        String response = ("\n Account: " + account + "\n Price: " + price + "\n Quantity: " + quantity + "\n Action: " + action) ;
        return response;
    }

    public int getAccount() {
        return account;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getAction(){
        return action;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeQuantity(int amount){
        if (quantity<amount){
            throw new IllegalArgumentException("Cannot set negative quantity");
        } else {
            quantity -= amount;
        }
    }
}
