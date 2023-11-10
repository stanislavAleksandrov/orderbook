package org.example.domain.order;

public class LimitOrder extends AbstractOrder {
    public LimitOrder(String symbol, boolean isBuyOrder, double price, int quantity) {
        super(symbol, isBuyOrder, price, quantity);
    }
}
