package com.matcher.matcher;

import com.matcher.matcher.entity.order.AggregatedOrder;
import com.matcher.matcher.entity.order.Orders;
import com.matcher.matcher.entity.order.OrderDetails;
import com.matcher.matcher.entity.order.OrdersRepository;
import com.matcher.matcher.entity.trade.Trade;
import com.matcher.matcher.entity.trade.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Matcher {

    @Autowired
    OrdersRepository orderRepository;
    @Autowired
    TradeRepository tradeRepository;

//    private ArrayList<Trade> completedTrades;
//    private ArrayList<AggregatedOrder> orderBook;

    public Matcher() {
//        completedTrades = new ArrayList<Trade>();
//        orderBook = new ArrayList<AggregatedOrder>();
    }

    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }

    public List<Trade> getTrades() {
        return tradeRepository.findAll();
    }

    public void addOrder(Orders order) {
        if (orderRepository == null){
            orderRepository.save(order);
        } else {
        orderRepository.findAll().stream().forEach(repoOrder -> {
            if (!Objects.equals(order.getType(), repoOrder.getType()) &&
                    ((Objects.equals(order.getType(), "buy") && order.getPrice() >= repoOrder.getPrice()) ||
                            (Objects.equals(order.getType(), "sell") && order.getPrice() <= repoOrder.getPrice()))
            ) {
                if (order.getQuantity() > repoOrder.getQuantity() &&
                        order.getQuantity() > 0) {
                    Trade trade = new Trade(order, repoOrder);
                    tradeRepository.save(trade);

                    order.changeQuantity(repoOrder.getQuantity());
                    orderRepository.delete(repoOrder);
//                    repoOrder.changeQuantity(repoOrder.getQuantity());
                } else if (order.getQuantity() <= repoOrder.getQuantity() &&
                        order.getQuantity() > 0) {
                    Trade trade = new Trade(order, repoOrder);
                    tradeRepository.save(trade);
                    if (order.getQuantity() < repoOrder.getQuantity()) {
                        repoOrder.changeQuantity(order.getQuantity());
                    } else {
                        orderRepository.delete(repoOrder);
                    }

//                    for (int j = 0; j < aggregatedOrder.getOrderDetails().size(); j++) {
//                        int quantityTracker = order.getQuantity();
//                        if (quantityTracker >= 0) {
//                            quantityTracker -= aggregatedOrder.getOrderDetails().get(j).getDetailsQuantity();
//                            aggregatedOrder.getOrderDetails().get(j).changeOrderQuantity(
//                                    aggregatedOrder.getOrderDetails().get(j).getDetailsQuantity());
//                        } else {
//                            aggregatedOrder.getOrderDetails().get(j).changeOrderQuantity(quantityTracker);
//                        }
//                    }
                    order.changeQuantity(order.getQuantity());
                }


            }
        });
        }
//        if ( orderBook.size() == 0){
//            orderBook.add(new AggregatedOrder(order));
//        } else{
//            for (int i=0;i<orderBook.size();i++) {
//                if (Objects.equals(orderBook.get(i).getPrice(), order.getPrice()) && Objects.equals(orderBook.get(i).getAction(), order.getAction())) {
//
//                    orderBook.get(i).changeAggregatedQuantity(-order.getQuantity());
//                    orderBook.get(i).getOrderDetails().add(new OrderDetails(order.getUsername(), order.getQuantity()));
//
//                } else {
//                    orderBook.add(new AggregatedOrder(order));
//                }
//            }
//        }

//        orderBook.removeIf(element -> (element.getQuantity() == 0));

    }


}
