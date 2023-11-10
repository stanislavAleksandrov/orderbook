package org.example.domain;

import org.example.domain.order.Order;

import java.util.Optional;
import java.util.PriorityQueue;

public class OrderBook {
    private String symbol;
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBook(String symbol) {
        this.symbol = symbol;
        this.buyOrders = new PriorityQueue<>((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        this.sellOrders = new PriorityQueue<>(java.util.Comparator.comparingDouble(Order::getPrice));
    }

    public void addOrder(Order order) {
        if (order.isBuyOrder()) {
            matchSellOrders(order);
            if (order.getQuantity() > 0) {
                buyOrders.offer(order);
            }
        } else {
            matchBuyOrders(order);
            if (order.getQuantity() > 0) {
                sellOrders.offer(order);
            }
        }
    }

    public Optional<org.example.domain.order.Order> getNextBuyOrder() {
        return Optional.ofNullable(buyOrders.poll());
    }

    public Optional<org.example.domain.order.Order> getNextSellOrder() {
        return Optional.ofNullable(sellOrders.poll());
    }

    private void matchSellOrders(Order buyOrder) {
        while (!sellOrders.isEmpty()) {
            Order sellOrder = sellOrders.peek();
            if (sellOrder.getPrice() <= buyOrder.getPrice()) {
                int quantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                buyOrder.reduceQuantity(quantity);
                sellOrder.reduceQuantity(quantity);
                if (sellOrder.getQuantity() == 0) {
                    sellOrders.poll();
                }
                if (buyOrder.getQuantity() == 0) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    private void matchBuyOrders(Order sellOrder) {
        while (!buyOrders.isEmpty()) {
            Order buyOrder = buyOrders.peek();
            if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                int quantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                buyOrder.reduceQuantity(quantity);
                sellOrder.reduceQuantity(quantity);
                if (buyOrder.getQuantity() == 0) {
                    buyOrders.poll();
                }
                if (sellOrder.getQuantity() == 0) {
                    break;
                }
            } else {
                break;
            }
        }
    }
}
