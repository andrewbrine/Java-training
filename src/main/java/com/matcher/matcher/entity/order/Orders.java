package com.matcher.matcher.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Digits(integer = 100, fraction = 2)
    private double price;
    @NotNull
    private int quantity;
    @NotNull
    @Pattern(regexp = "buy|sell")
    private String type;
    @NotNull
    @Length(min=4,max=20)
    private String username;

    public Orders() {};

    public Orders(String username, double price, int quantity, String type) {
        this.username = username;
        this.price= price;
        this.quantity = quantity;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getType(){
        return type;
    }

    public void setAccount(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        String response = ("\n Account: " + username + "\n Price: " + price + "\n Quantity: " + quantity + "\n Action: " + type) ;
        return response;
    }
}
