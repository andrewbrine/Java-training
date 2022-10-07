package com.matcher.matcher.entity.trade;

import com.matcher.matcher.entity.order.AggregatedOrder;
import com.matcher.matcher.entity.order.Orders;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int tradeQuantity;
    private double price;
    private String buyer;
    private String seller;

    public Trade(Orders order1, Orders order2){
        tradeQuantity = Math.min(order1.getQuantity(),order2.getQuantity());
        price = Math.min(order1.getPrice(),order2.getPrice());
        if (order1.getType() == "buy"){
            buyer = order1.getUsername();
            seller = order2.getUsername();
        } else {
            seller = order1.getUsername();
            buyer = order2.getUsername();
        }
    }

    public String getTradeCompleteness(Orders order){
        if (order.getQuantity() > 0){
            return "partial";
        } else if (order.getQuantity() == 0){
            return "complete";
        } else {
            return "error";
        }
    }

    public String toString() {
        String response = ("\n Price: " + price + "\n Quantity: " + tradeQuantity) ;
        return response;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(int tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
