package org.example.domain.order;

import org.example.domain.order.AbstractOrder;

public class TWAPOrder extends AbstractOrder {
    private final long executionStartTime;
    private final long durationInMillis;

    public TWAPOrder(String symbol, boolean isBuyOrder, double price, int quantity, long durationInMillis) {
        super(symbol, isBuyOrder, price, quantity);
        this.executionStartTime = System.currentTimeMillis();
        this.durationInMillis = durationInMillis;
    }

    public long getExecutionStartTime() {
        return executionStartTime;
    }

    public long getDurationInMillis() {
        return durationInMillis;
    }

}
