package com.matcher.matcher.entity.order;


import com.matcher.matcher.entity.order.Orders;
import com.matcher.matcher.entity.order.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository repository;

    public List<Orders> findAll() {
        return (List<Orders>) repository.findAll();
    }

    public Orders getOrderById(long id) {
        return repository.findById(id).get();
    }

    public Orders saveOrUpdate(Orders order){
        repository.save(order);
        return order;
    }

    public List<Orders> getOrdersByUsername(String username) {
        return repository.findByUsername(username).get();
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
