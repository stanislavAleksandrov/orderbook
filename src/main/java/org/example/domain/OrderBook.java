package org.example.domain;

import org.example.domain.order.Order;

import java.util.*;

public class OrderBook {
    private String symbol;
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBook(String symbol) {
        this.symbol = symbol;
        this.buyOrders = new PriorityQueue<>((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        this.sellOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice));
    }

    public void addOrder(Order order) {
        if (order.isBuyOrder()) {
            buyOrders.offer(order);
        } else {
            sellOrders.offer(order);
        }
    }

    public Optional<Order> getNextBuyOrder() {
        return Optional.ofNullable(buyOrders.poll());
    }

    public Optional<Order> getNextSellOrder() {
        return Optional.ofNullable(sellOrders.poll());
    }
}
