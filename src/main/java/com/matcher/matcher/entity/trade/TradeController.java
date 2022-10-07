package com.matcher.matcher.entity.trade;

import com.matcher.matcher.entity.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    TradeService tradeService;

    @GetMapping("/trades")
    private List<Trade> getAllTrades(){
        return tradeService.findAll();
    }

//    @GetMapping("orders/{username}")
//    private List<Order> getOrdersByUsername(@PathVariable("username") String username){
//        return orderService.getOrdersByUsername(username);
//    }

    @PostMapping("/add-trade")
    private List<Trade> addTrade() {
        Trade trade = new Trade(new Orders("steve",1,1,"buy"), new Orders("andrew",1,1,"sell"));
        tradeService.saveOrUpdate(trade);
        return tradeService.findAll();
    }

}
