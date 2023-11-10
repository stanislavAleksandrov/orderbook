package org.example.domain.order;

public class MarketOrder extends AbstractOrder {
    public MarketOrder(String symbol, boolean isBuyOrder, int quantity) {
        super(symbol, isBuyOrder, 0.0, quantity);
    }
}
