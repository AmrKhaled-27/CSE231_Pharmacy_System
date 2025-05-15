package edu.cse231.models.Orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.cse231.interfaces.Searchable;

public class OrderHistory implements Searchable<Order> {

    private final List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public Order searchById(String orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                // Create a copy of the found order
                Order copy = new Order(order.getId(), order.getCustomerName(), order.getDate());
                // Copy all order items
                for (OrderItem item : order.getItems()) {
                    copy.addOrderItem(item);
                }
                // Copy recipe and payment if they exist
                if (order.getRecipe() != null) {
                    copy.setRecipe(order.getRecipe());
                }
                if (order.getPayment() != null) {
                    copy.setPayment(order.getPayment());
                }
                return copy;
            }
        }
        return null;
    }

    @Override
    public Order searchByName(String customerNameQuery) {
        if (customerNameQuery == null || customerNameQuery.trim().isEmpty()) {
            return null;
        }
        String lowerCaseQuery = customerNameQuery.toLowerCase();
        for (Order order : orders) {
            if (order.getCustomerName().toLowerCase().contains(lowerCaseQuery)) {
                // Create a copy of the found order
                Order copy = new Order(order.getId(), order.getCustomerName(), order.getDate());
                // Copy all order items
                for (OrderItem item : order.getItems()) {
                    copy.addOrderItem(item);
                }
                // Copy recipe and payment if they exist
                if (order.getRecipe() != null) {
                    copy.setRecipe(order.getRecipe());
                }
                if (order.getPayment() != null) {
                    copy.setPayment(order.getPayment());
                }
                return copy;
            }
        }
        return null;
    }

    public List<Order> getAllOrders() {
        List<Order> copies = new ArrayList<>();
        for (Order order : orders) {
            // Create a new order with the same data
            Order copy = new Order(order.getId(), order.getCustomerName(), order.getDate());
            // Copy all order items
            for (OrderItem item : order.getItems()) {
                copy.addOrderItem(item);
            }
            // Copy recipe and payment if they exist
            if (order.getRecipe() != null) {
                copy.setRecipe(order.getRecipe());
            }
            if (order.getPayment() != null) {
                copy.setPayment(order.getPayment());
            }
            copies.add(copy);
        }
        return Collections.unmodifiableList(copies);
    }
}
