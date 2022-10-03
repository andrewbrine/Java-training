package com.matcher.matcher.controller;

import com.matcher.matcher.Matcher;
import com.matcher.matcher.Order;
import com.matcher.matcher.entity.Account;
import com.matcher.matcher.entity.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
//@ComponentScan
public class MatcherController {
    @Autowired
    private Matcher matcher;
//    Matcher matcher = new Matcher();

    @GetMapping("/")
    private String getFrontPage() {
        return "Welcome to the Matcher!";
    }

    @GetMapping("/order-book")
    private String getOrderBook() {
        if (matcher.getOrderBook().size() == 0) {
            return "No orders currently";
        } else {
            return matcher.getOrderBook().toString();
        }
    }

    @GetMapping("/trades-list")
    private String getTradesList(){
        if (matcher.getCompletedTrades().size() == 0) {
            return "No trades currently";
        } else {
            return matcher.getCompletedTrades().toString();
        }
    }

    @PostMapping("/add-order")
    private String addOrder(@Valid @RequestBody Order order){
        matcher.addOrder(order);
        return "Order added successfully!";
    }
}
