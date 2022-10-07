package com.matcher.matcher.entity.order;

import com.matcher.matcher.entity.order.Orders;
import com.matcher.matcher.entity.order.OrderDetails;

import java.util.ArrayList;

public class AggregatedOrder {
    private String type;
    private double price;
    private int quantity;
    private ArrayList<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

    public AggregatedOrder(Orders order){
        type = order.getType();
        price = order.getPrice();
        quantity = order.getQuantity();
        orderDetails.add(new OrderDetails(order.getUsername(),order.getQuantity()));
    }

    public String getType(){
        return type;
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
        String response = ( "\n Action: " + type + "\n Price: " + price + "\n Aggregated Quantity: " + quantity + "\n Number of Orders:" + orderDetails.size()) ;
        return response;
    }
}
