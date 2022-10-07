package com.matcher.matcher;

import com.matcher.matcher.Matcher;
import com.matcher.matcher.entity.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RestController
////@ComponentScan
//public class MatcherController {
//    @Autowired
//    private Matcher matcher;
//
//    @GetMapping("/")
//    private String getFrontPage() {
//        return "Welcome to the Matcher!";
//    }
//
//    @GetMapping("/order-book")
//    private String getOrderBook() {
//        if (matcher.getOrderBook().size() == 0) {
//            return "No orders currently";
//        } else {
//            return matcher.getOrderBook().toString();
//        }
//    }
//
//    @GetMapping("/trades-list")
//    private String getTradesList(){
//        if (matcher.getCompletedTrades().size() == 0) {
//            return "No trades currently";
//        } else {
//            return matcher.getCompletedTrades().toString();
//        }
//    }
//
//    @PostMapping("/add-order")
//    private String addOrder(@Valid @RequestBody Order order){
//
//        matcher.addOrder(order);
//        return "Order added successfully!";
//    }
//}
