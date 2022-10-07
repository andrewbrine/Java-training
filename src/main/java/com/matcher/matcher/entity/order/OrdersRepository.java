package com.matcher.matcher.entity.order;

import com.matcher.matcher.entity.order.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends CrudRepository<Orders,Long > {

    List<Orders> findAll();

    Optional<List<Orders>> findByUsername(String username);

    Optional<Orders> findById(long id);
}
