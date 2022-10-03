package com.matcher.matcher;

import java.util.ArrayList;

public class AggregatedOrder {
    private String action;
    private double price;
    private int quantity;
    private ArrayList<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

    public AggregatedOrder(Order order){
        action = order.getAction();
        price = order.getPrice();
        quantity = order.getQuantity();
        orderDetails.add(new OrderDetails(order.getAccount(),order.getQuantity()));
    }

    public String getAction(){
        return action;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void changeAggregatedQuantity(int amount){
        if (quantity<amount){
            throw new IllegalArgumentException("Cannot set negative quantity");
        } else {
            quantity -= amount;
        }
    }

    public String toString() {
        String response = ( "\n Action: " + action + "\n Price: " + price + "\n Aggregated Quantity: " + quantity + "\n Number of Orders:" + orderDetails.size()) ;
        return response;
    }
}
