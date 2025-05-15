package edu.cse231.models.Pharmacy;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import edu.cse231.models.Items.InsufficientStockException;
import edu.cse231.models.Items.ItemsInventory;
import edu.cse231.models.Orders.Order;
import edu.cse231.models.Orders.OrderHistory;
import edu.cse231.models.Orders.OrderItem;
import edu.cse231.models.Payments.Payment;
import edu.cse231.models.Recipies.InvalidRecipeException;
import edu.cse231.models.Recipies.Recipe;
import edu.cse231.models.Recipies.RecipesInventory;

abstract class Pharmacy {

    private final ItemsInventory itemsInventory;
    private final OrderHistory orderHistory;
    private final RecipesInventory recipesInventory;
    private final String name;
    private final String location;

    public Pharmacy(String name, String location, ItemsInventory itemsInventory, OrderHistory orderHistory, RecipesInventory recipesInventory) {
        this.name = name;
        this.location = location;
        this.itemsInventory = itemsInventory;
        this.orderHistory = orderHistory;
        this.recipesInventory = recipesInventory;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Order placeOrder(String customerName, List<OrderItem> items, String recipeId, Payment payment)
            throws InsufficientStockException, InvalidRecipeException {

        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain items.");
        }
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null.");
        }

        Recipe associatedRecipe = null;
        if (recipeId != null && !recipeId.trim().isEmpty()) {
            associatedRecipe = recipesInventory.searchById(recipeId);
            if (associatedRecipe == null) {
                throw new InvalidRecipeException("Recipe with ID " + recipeId + " not found.");
            }
            if (!recipesInventory.isValidRecipe(recipeId)) {
                throw new InvalidRecipeException("Recipe with ID " + recipeId + " is not valid or active.");
            }
        }

        for (OrderItem orderItem : items) {
            if (itemsInventory.searchById(orderItem.getItem().getId()) == null) {
                throw new IllegalArgumentException("Item in order not found in inventory catalog: " + orderItem.getItem().getId());
            }
            if (!itemsInventory.hasSufficientStock(orderItem.getItem().getId(), orderItem.getQuantity())) {
                throw new InsufficientStockException("Insufficient stock for item: " + orderItem.getItem().getName() + " (ID: " + orderItem.getItem().getId() + ").");
            }
        }

        String orderId = UUID.randomUUID().toString();
        Date orderDate = new Date();

        Order newOrder = new Order(orderId, customerName, orderDate);
        for (OrderItem item : items) {
            newOrder.addOrderItem(item);
        }
        newOrder.setRecipe(associatedRecipe);

        // Verify payment amount matches order total
        if (payment.getAmount() != newOrder.getTotalAmount()) {
            throw new IllegalArgumentException("Payment amount does not match order total. Expected: "
                    + newOrder.getTotalAmount() + ", Provided: " + payment.getAmount());
        }

        // Process payment first
        if (!payment.process()) {
            throw new IllegalStateException("Payment processing failed for order: " + orderId);
        }

        try {
            for (OrderItem orderItem : items) {
                itemsInventory.reduceStock(orderItem.getItem().getId(), orderItem.getQuantity());
            }
        } catch (InsufficientStockException e) {
            // If stock reduction fails, we should refund the payment
            payment.refund();
            System.err.println("Error reducing stock after payment: " + e.getMessage());
            throw e;
        }

        // Set payment and add order to history
        newOrder.setPayment(payment);
        orderHistory.addOrder(newOrder);

        if (associatedRecipe != null) {
            recipesInventory.markRecipeFulfilled(associatedRecipe.getId());
        }

        return newOrder;
    }

    public ItemsInventory getItemsInventory() {
        return itemsInventory;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public RecipesInventory getRecipesInventory() {
        return recipesInventory;
    }
}
