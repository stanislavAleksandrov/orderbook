package org.example.domain.order;

import java.util.UUID;

public class BracketedOrder implements Order {
    private final String orderId;
    private final Order primaryOrder;
    private final Order takeProfitOrder;
    private final Order stopLossOrder;

    public BracketedOrder(AbstractOrder primaryOrder, AbstractOrder takeProfitOrder, AbstractOrder stopLossOrder) {
        this.orderId = UUID.randomUUID().toString();
        this.primaryOrder = primaryOrder;
        this.takeProfitOrder = takeProfitOrder;
        this.stopLossOrder = stopLossOrder;
    }

    public Order getPrimaryOrder() {
        return primaryOrder;
    }

    public Order getTakeProfitOrder() {
        return takeProfitOrder;
    }

    public Order getStopLossOrder() {
        return stopLossOrder;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getSymbol() {
        return primaryOrder.getSymbol();
    }

    @Override
    public boolean isBuyOrder() {
        return primaryOrder.isBuyOrder();
    }

    @Override
    public double getPrice() {
        return primaryOrder.getPrice();
    }

    @Override
    public int getQuantity() {
        return primaryOrder.getQuantity();
    }
}
