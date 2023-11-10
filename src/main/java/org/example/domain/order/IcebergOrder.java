package org.example.domain.order;

public class IcebergOrder extends AbstractOrder {
    private final int visibleQuantity;

    public IcebergOrder(String symbol, boolean isBuyOrder, double price, int totalQuantity, int visibleQuantity) {
        super(symbol, isBuyOrder, price, totalQuantity);
        this.visibleQuantity = visibleQuantity;
    }

    public int getVisibleQuantity() {
        return visibleQuantity;
    }
}
