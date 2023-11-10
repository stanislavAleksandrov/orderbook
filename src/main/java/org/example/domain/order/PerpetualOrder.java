package org.example.domain.order;

import org.example.domain.order.Order;

import java.util.UUID;

public class PerpetualOrder implements Order {
    private final String orderId;
    private final String symbol;
    private final boolean isBuyOrder;
    private final double price;

    public PerpetualOrder(String symbol, boolean isBuyOrder, double price) {
        this.orderId = UUID.randomUUID().toString();
        this.symbol = symbol;
        this.isBuyOrder = isBuyOrder;
        this.price = price;
    }

    @Override
    public String getOrderId() {
        return this.orderId;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public boolean isBuyOrder() {
        return this.isBuyOrder;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getQuantity() {
        throw new IllegalArgumentException("PerpetualOrder doesn't have quantity");
    }
}
