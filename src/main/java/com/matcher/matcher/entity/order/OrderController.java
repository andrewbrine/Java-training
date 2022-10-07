package com.matcher.matcher.entity.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

//    @Autowired
//    OrderRepository orderRepository;
    @Autowired
    OrdersService orderService;

    @GetMapping("/orders")
    private List<Orders> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("orders/{username}")
    private List<Orders> getOrdersByUsername(@PathVariable("username") String username){
        return orderService.getOrdersByUsername(username);
    }

    @PostMapping("/add-order")
    private List<Orders> addOrder(@Valid @RequestBody Orders order){
        orderService.saveOrUpdate(order);
        return orderService.findAll();
    }

}
