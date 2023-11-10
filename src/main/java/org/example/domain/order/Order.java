package org.example.domain.order;

public interface Order {
    String getOrderId();
    String getSymbol();
    boolean isBuyOrder();
    double getPrice();
    int getQuantity();
    void reduceQuantity(int count);
}
