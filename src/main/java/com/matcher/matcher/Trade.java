package com.matcher.matcher;

public class Trade {

    private Order order1;
    private AggregatedOrder order2;
    private int tradeQuantity;
    private double price;

    public Trade(Order inputOrder1, AggregatedOrder inputOrder2){
        order1 = inputOrder1;
        order2 = inputOrder2;
        tradeQuantity = Math.min(order1.getQuantity(),order2.getQuantity());
        price = Math.min(order1.getPrice(),order2.getPrice());
    }

    public String getTradeCompleteness(){
        if (order1.getQuantity() > 0){
            return "partial";
        } else if (order1.getQuantity() == 0){
            return "complete";
        } else {
            return "error";
        }
    }

    public String toString() {
        String response = ("\n Price: " + price + "\n Quantity: " + tradeQuantity) ;
        return response;
    }
}
