package com.matcher.matcher;

import com.matcher.matcher.entity.order.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void createOrderCorrectly(){
        Order testOrder = new Order("aa",1,1,"buy");
        assertEquals(testOrder.toString(),"\n Account: " + 1 + "\n Price: " + 1.0 + "\n Quantity: " + 1 + "\n Action: " + "buy");
    }

    @Test
    void changeQuantityCorrectly(){
        Order testOrder = new Order("aa",1,1,"buy");
        testOrder.changeQuantity(1);
        assertEquals(testOrder.toString(),"\n Account: " + 1 + "\n Price: " + 1.0 + "\n Quantity: " + 0 + "\n Action: " + "buy");
    }

    @Test
    void rejectNegativeQuantity(){
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Order testOrder = new Order("aa",1,1,"buy");
                testOrder.changeQuantity(2);
            }
        });
    }
}