package com.matcher.matcher.entity.trade;

import com.matcher.matcher.entity.trade.Trade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends CrudRepository<Trade,Long > {

    List<Trade> findAll();

    Optional<List<Trade>> findByBuyer(String buyer);

    Optional<List<Trade>> findBySeller(String seller);

    Optional<Trade> findById(long id);
}