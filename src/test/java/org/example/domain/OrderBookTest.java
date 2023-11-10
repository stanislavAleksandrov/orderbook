package org.example.domain;

import org.example.domain.order.LimitOrder;
import org.example.domain.order.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderBookTest {

    @Test
    void addOrderAndGetNextBuyOrder() {
        OrderBook orderBook = new OrderBook("UBER");

        Order buyOrder = new LimitOrder("UBER", true, 150.0, 10);
        orderBook.addOrder(buyOrder);

        Optional<Order> retrievedOrder = orderBook.getNextBuyOrder();

        assertTrue(retrievedOrder.isPresent());
        assertEquals(buyOrder, retrievedOrder.get());
    }

    @Test
    void testOrderMatching() {
        OrderBook orderBook = new OrderBook("UBER");

        orderBook.addOrder(new LimitOrder("UBER", true, 150.0, 5));
        orderBook.addOrder(new LimitOrder("UBER", true, 148.0, 7));

        orderBook.addOrder(new LimitOrder("UBER", false, 155.0, 8));
        orderBook.addOrder(new LimitOrder("UBER", false, 150.0, 5));

        Optional<Order> buyOrder = orderBook.getNextBuyOrder();
        Optional<Order> sellOrder = orderBook.getNextSellOrder();

        assertTrue(buyOrder.isPresent());
        assertTrue(sellOrder.isPresent());

        assertEquals(148, buyOrder.get().getPrice());
        assertEquals(155, sellOrder.get().getPrice());

        Optional<Order> buyOrder2 = orderBook.getNextBuyOrder();
        Optional<Order> sellOrder2 = orderBook.getNextSellOrder();

        assertFalse(buyOrder2.isPresent());
        assertFalse(sellOrder2.isPresent());
    }

    @Test
    void addOrderAndGetNextSellOrder() {
        OrderBook orderBook = new OrderBook("UBER");

        Order sellOrder = new LimitOrder("UBER", false, 155.0, 8);
        orderBook.addOrder(sellOrder);

        Optional<Order> retrievedOrder = orderBook.getNextSellOrder();

        assertTrue(retrievedOrder.isPresent());
        assertEquals(sellOrder, retrievedOrder.get());
    }

    @Test
    void testOrderSorting() {
        OrderBook orderBook = new OrderBook("UBER");

        orderBook.addOrder(new LimitOrder("UBER", true, 150.0, 10));
        orderBook.addOrder(new LimitOrder("UBER", true, 145.0, 8));
        orderBook.addOrder(new LimitOrder("UBER", true, 155.0, 5));

        orderBook.addOrder(new LimitOrder("UBER", false, 160.0, 12));
        orderBook.addOrder(new LimitOrder("UBER", false, 155.0, 6));
        orderBook.addOrder(new LimitOrder("UBER", false, 165.0, 9));

        assertEquals(155.0, orderBook.getNextBuyOrder().get().getPrice());
        assertEquals(150.0, orderBook.getNextBuyOrder().get().getPrice());
        assertEquals(145.0, orderBook.getNextBuyOrder().get().getPrice());

        assertEquals(155.0, orderBook.getNextSellOrder().get().getPrice());
        assertEquals(160.0, orderBook.getNextSellOrder().get().getPrice());
        assertEquals(165.0, orderBook.getNextSellOrder().get().getPrice());
    }

    @Test
    void getNextBuyOrderWhenEmpty() {
        OrderBook orderBook = new OrderBook("UBER");

        Optional<Order> retrievedOrder = orderBook.getNextBuyOrder();

        assertFalse(retrievedOrder.isPresent());
    }

    @Test
    void getNextSellOrderWhenEmpty() {
        OrderBook orderBook = new OrderBook("UBER");

        Optional<Order> retrievedOrder = orderBook.getNextSellOrder();

        assertFalse(retrievedOrder.isPresent());
    }
}
