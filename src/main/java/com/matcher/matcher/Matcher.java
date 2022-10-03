package com.matcher.matcher;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Component
public class Matcher {
    private ArrayList<Trade> completedTrades;
    private ArrayList<AggregatedOrder> orderBook;

    public Matcher() {
        completedTrades = new ArrayList<Trade>();
        orderBook = new ArrayList<AggregatedOrder>();
    }

    public ArrayList<AggregatedOrder> getOrderBook() {
        return orderBook;
    }

    public ArrayList<Trade> getCompletedTrades() {
        return completedTrades;
    }

    public void addOrder(Order order) {
        for (AggregatedOrder aggregatedOrder : orderBook) {
            if (!Objects.equals(order.getAction(), aggregatedOrder.getAction()) &&
                    ((Objects.equals(order.getAction(), "buy") && order.getPrice() >= aggregatedOrder.getPrice()) ||
                            (Objects.equals(order.getAction(), "sell") && order.getPrice() <= aggregatedOrder.getPrice()))
            ) {
                if (order.getQuantity() > aggregatedOrder.getQuantity() &&
                        order.getQuantity() > 0) {
                    Trade trade = new Trade(order, aggregatedOrder);
                    completedTrades.add(trade);

                    order.changeQuantity(aggregatedOrder.getQuantity());
                    aggregatedOrder.changeAggregatedQuantity(aggregatedOrder.getQuantity());
                } else if (order.getQuantity() <= aggregatedOrder.getQuantity() &&
                        order.getQuantity() > 0) {
                    Trade trade = new Trade(order, aggregatedOrder);
                    completedTrades.add(trade);
                    aggregatedOrder.changeAggregatedQuantity(order.getQuantity());

                    for (int j = 0; j < aggregatedOrder.getOrderDetails().size(); j++) {
                        int quantityTracker = order.getQuantity();
                        if (quantityTracker >= 0) {
                            quantityTracker -= aggregatedOrder.getOrderDetails().get(j).getDetailsQuantity();
                            aggregatedOrder.getOrderDetails().get(j).changeOrderQuantity(
                                    aggregatedOrder.getOrderDetails().get(j).getDetailsQuantity());
                        } else {
                            aggregatedOrder.getOrderDetails().get(j).changeOrderQuantity(quantityTracker);
                        }
                    }
                    order.changeQuantity(order.getQuantity());
                }


            }
        }
        if ( orderBook.size() == 0){
            orderBook.add(new AggregatedOrder(order));
        } else{
            for (int i=0;i<orderBook.size();i++) {
                if (Objects.equals(orderBook.get(i).getPrice(), order.getPrice()) && Objects.equals(orderBook.get(i).getAction(), order.getAction())) {

                    orderBook.get(i).changeAggregatedQuantity(-order.getQuantity());
                    orderBook.get(i).getOrderDetails().add(new OrderDetails(order.getAccount(), order.getQuantity()));

                } else {
                    orderBook.add(new AggregatedOrder(order));
                }
            }
        }

        orderBook.removeIf(element -> (element.getQuantity() == 0));

    }


}
