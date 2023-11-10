package org.example.domain.order;

import java.util.UUID;

public class AbstractOrder implements Order {
    private final String orderId;
    private final String symbol;
    private final boolean isBuyOrder;
    private final double price;
    private final int quantity;

    public AbstractOrder(String symbol, boolean isBuyOrder, double price, int quantity) {
        this.orderId = UUID.randomUUID().toString();
        this.symbol = symbol;
        this.isBuyOrder = isBuyOrder;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean isBuyOrder() {
        return isBuyOrder;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
