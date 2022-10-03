package com.matcher.matcher;

public class OrderDetails {
    private int account;
    private int quantity;

    public OrderDetails(int inputAccount, int inputQuantity){
        account = inputAccount;
        quantity = inputQuantity;
    }

    public int getDetailsAccount(){
        return account;
    }

    public int getDetailsQuantity(){
        return quantity;
    }

    public void changeOrderQuantity(int amount){
        if (quantity<amount){
            throw new IllegalArgumentException("Cannot set negative quantity");
        } else {
            quantity -= amount;
        }
    }
}
