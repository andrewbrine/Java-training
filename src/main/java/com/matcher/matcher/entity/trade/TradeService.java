package com.matcher.matcher.entity.trade;

import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    TradeRepository repository;

    public List<Trade> findAll() {
        return (List<Trade>) repository.findAll();
    }

    public Trade getTradeById(long id) {
        return repository.findById(id).get();
    }

    public List<Trade> findTradesByUsername(String username) {
        Optional<List<Trade>> buyerTrades = repository.findByBuyer(username);
        Optional<List<Trade>> sellerTrades = repository.findBySeller(username);
        List<Trade> trades = new ArrayList<>();
        trades.addAll(buyerTrades.get());
        trades.addAll(sellerTrades.get());
        return trades;
    }

    public Trade saveOrUpdate(Trade trade){
        repository.save(trade);
        return trade;
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
