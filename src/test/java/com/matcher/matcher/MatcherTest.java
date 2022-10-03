package com.matcher.matcher;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MatcherTest {
    Order testOrder = new Order(1,1,1,"buy");
    Order testOrder2 = new Order(1,1,1,"sell");
    Order testOrder3 = new Order(1,2,1,"buy");
    Matcher matcher = new Matcher();

    @Test
    void addOrderCorrectly(){
        matcher.addOrder(testOrder);
        String orderStructure = "[\n Action: " + "buy" + "\n Price: " + 1.0 + "\n Aggregated Quantity: " + 1 + "\n Number of Orders:" + 1 +"]";
        assertEquals(matcher.getOrderBook().toString(),orderStructure);
    }

    @Test
    void tradeCreatedCorrectly(){
        matcher.addOrder(testOrder);
        matcher.addOrder(testOrder2);


        System.out.println(matcher.getCompletedTrades().toString());
        String tradeStructure = ("[\n Price: " + 1.0 + "\n Quantity: " + 1 + "]") ;

        assertEquals(matcher.getCompletedTrades().toString(), tradeStructure);
    }

    @Test
    void tradeNotCreatedCorrectly(){
        matcher.addOrder(testOrder);
        matcher.addOrder(testOrder3);
        ArrayList<Trade> emptyTrades = new ArrayList<Trade>();
        assertEquals(matcher.getCompletedTrades(), emptyTrades);
    }

    @Test
    void orderBookPopulatedCorrectly(){
        matcher.addOrder(testOrder);
        matcher.addOrder(testOrder3);
        assertEquals(2,matcher.getOrderBook().size());
    }
    @Test
    void orderDetailsPopulatedCorrectly(){
        matcher.addOrder(testOrder);
        matcher.addOrder(testOrder);
        assertEquals(2,matcher.getOrderBook().get(0).getOrderDetails().size());
    }


}