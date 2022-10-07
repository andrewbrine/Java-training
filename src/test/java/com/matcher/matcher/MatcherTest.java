package com.matcher.matcher;

import com.matcher.matcher.entity.order.Order;
import com.matcher.matcher.entity.order.OrdersRepository;
import com.matcher.matcher.entity.trade.Trade;
import com.matcher.matcher.entity.trade.TradeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatcherTest {

    @Mock
    OrdersRepository orderRepository;
    @Mock
    TradeRepository tradeRepository;



    Order testOrder = new Order("aa",1,1,"buy");
    Order testOrder2 = new Order("aa",1,1,"sell");
    Order testOrder3 = new Order("aa",2,1,"buy");
    Matcher matcher = new Matcher();

    @Test
    void addOrderCorrectly(){
//        matcher.addOrder(testOrder);
        orderRepository.save(testOrder);
        String orderStructure = "[\n Action: " + "buy" + "\n Price: " + 1.0 + "\n Aggregated Quantity: " + 1 + "\n Number of Orders:" + 1 +"]";
        assertEquals(orderRepository.findAll().toString(),orderStructure);
    }

    @Test
    void tradeCreatedCorrectly(){
        matcher.addOrder(testOrder);
        matcher.addOrder(testOrder2);


//        System.out.println(matcher.getCompletedTrades().toString());
        String tradeStructure = ("[\n Price: " + 1.0 + "\n Quantity: " + 1 + "]") ;

        assertEquals(tradeRepository.findAll().toString(), tradeStructure);
    }

    @Test
    void tradeNotCreatedCorrectly(){
        matcher.addOrder(testOrder);
        matcher.addOrder(testOrder3);
        List<Trade> emptyTrades = new ArrayList<Trade>();
        assertEquals(tradeRepository.findAll(), emptyTrades);
    }

    @Test
    void orderBookPopulatedCorrectly(){
        matcher.addOrder(testOrder);
        matcher.addOrder(testOrder3);
        assertEquals(2,orderRepository.findAll().size());
    }
//    @Test
//    void orderDetailsPopulatedCorrectly(){
//        matcher.addOrder(testOrder);
//        matcher.addOrder(testOrder);
//        assertEquals(2,matcher.getOrderBook().get(0).getOrderDetails().size());
//    }


}