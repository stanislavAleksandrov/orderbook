package org.example.service;

import org.example.domain.OrderBook;
import org.example.domain.order.LimitOrder;
import org.example.domain.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderBookServiceTest {

    private OrderBookService orderBookService;
    private Map<String, OrderBook> orderBooks;

    @BeforeEach
    void setUp() {
        orderBooks = new HashMap<>();
        orderBookService = new OrderBookService(orderBooks);
    }

    @Test
    void acceptOrder() {
        Order order = new LimitOrder("AAPL", true, 150.0, 10);

        orderBookService.acceptOrder(order);

        assertTrue(orderBooks.containsKey("AAPL"));
        OrderBook actualOrderBook = orderBooks.get("AAPL");
        assertEquals(order, actualOrderBook.getNextBuyOrder().get());
    }

    @Test
    void getNextBuyOrder() {
        OrderBook mockOrderBook = Mockito.mock(OrderBook.class);
        Order expectedOrder = new LimitOrder("AAPL", true, 150.0, 10);

        when(mockOrderBook.getNextBuyOrder()).thenReturn(Optional.of(expectedOrder));
        orderBooks.put("AAPL", mockOrderBook);

        Optional<Order> actualOrder = orderBookService.getNextBuyOrder("AAPL");

        assertTrue(actualOrder.isPresent());
        assertEquals(expectedOrder, actualOrder.get());
    }

    @Test
    void getNextBuyOrderWhenOrderBookNotFound() {
        Optional<Order> actualOrder = orderBookService.getNextBuyOrder("AAPL");

        assertFalse(actualOrder.isPresent());
    }

    @Test
    void getNextSellOrder() {
        OrderBook mockOrderBook = Mockito.mock(OrderBook.class);
        Order expectedOrder = new LimitOrder("AAPL", false, 155.0, 8);

        when(mockOrderBook.getNextSellOrder()).thenReturn(Optional.of(expectedOrder));
        orderBooks.put("AAPL", mockOrderBook);

        Optional<Order> actualOrder = orderBookService.getNextSellOrder("AAPL");

        assertTrue(actualOrder.isPresent());
        assertEquals(expectedOrder, actualOrder.get());
    }

    @Test
    void getNextSellOrderWhenOrderBookNotFound() {
        Optional<Order> actualOrder = orderBookService.getNextSellOrder("AAPL");

        assertFalse(actualOrder.isPresent());
    }
}
