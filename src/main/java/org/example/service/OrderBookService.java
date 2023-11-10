package org.example.service;

import org.example.domain.OrderBook;
import org.example.domain.order.Order;

import java.util.Map;
import java.util.Optional;

public class OrderBookService {
    private final Map<String, OrderBook> books;

    public OrderBookService(Map<String, OrderBook> books) {
        this.books = books;
    }

    public void acceptOrder(Order order) {
        String symbol = order.getSymbol();
        OrderBook curBook = books.getOrDefault(symbol, new OrderBook(symbol));
        curBook.addOrder(order);
        books.put(symbol, curBook);
    }

    public Optional<Order> getNextBuyOrder(String symbol) {
        OrderBook book = books.get(symbol);
        return book != null ? book.getNextBuyOrder() : Optional.empty();
    }

    public Optional<Order> getNextSellOrder(String symbol) {
        OrderBook book = books.get(symbol);
        return book != null ? book.getNextSellOrder() : Optional.empty();
    }
}
