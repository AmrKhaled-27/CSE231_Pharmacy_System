/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cse231.models.Orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cse231.models.Payments.Payment;
import edu.cse231.models.Recipies.Recipe;

/**
 *
 * @author Admin
 */
public class Order {

    private final String id;
    private final String customerName;
    private final Date date;
    private final List<OrderItem> items;
    private Recipe recipe; // Assume Recipe class is defined
    private Payment payment; // Assume Payment class is defined

    public Order(String id, String customerName, Date date) {
        this.id = id;
        this.customerName = customerName;
        this.date = new Date(date.getTime());
        this.items = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}
