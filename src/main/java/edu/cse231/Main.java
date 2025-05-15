package edu.cse231;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cse231.models.Items.ItemsInventory;
import edu.cse231.models.Items.Medication;
import edu.cse231.models.Orders.Order;
import edu.cse231.models.Orders.OrderHistory;
import edu.cse231.models.Orders.OrderItem;
import edu.cse231.models.Payments.CashPayment;
import edu.cse231.models.Pharmacy.PharmacyBranch;
import edu.cse231.models.Recipies.Recipe;
import edu.cse231.models.Recipies.RecipesInventory;

public class Main {

    public static void main(String[] args) {
        try {
            // Initialize inventories
            ItemsInventory itemsInventory = new ItemsInventory();
            OrderHistory orderHistory = new OrderHistory();
            RecipesInventory recipesInventory = new RecipesInventory();

            // Create a pharmacy branch
            PharmacyBranch pharmacy = new PharmacyBranch(
                    "Downtown Pharmacy",
                    "123 Main St",
                    itemsInventory,
                    orderHistory,
                    recipesInventory
            );

            // Add medications to inventory
            Medication paracetamol = new Medication(
                    "P001",
                    "Paracetamol 500mg",
                    5.99,
                    "500mg",
                    "Tablet",
                    "Paracetamol"
            );
            Medication ibuprofen = new Medication(
                    "I001",
                    "Ibuprofen 400mg",
                    7.99,
                    "400mg",
                    "Tablet",
                    "Ibuprofen"
            );

            itemsInventory.addItemToCatalog(paracetamol);
            itemsInventory.addItemToCatalog(ibuprofen);

            // Add stock
            itemsInventory.addStock("P001", 100);
            itemsInventory.addStock("I001", 50);

            // Create a recipe
            Recipe coldRecipe = new Recipe(
                    "R001",
                    "John Smith",
                    "Dr. Johnson",
                    new Date(),
                    new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000) // Valid for 30 days
            );
            recipesInventory.addRecipe(coldRecipe);

            // Create order items
            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(new OrderItem(paracetamol, 2));
            orderItems.add(new OrderItem(ibuprofen, 1));

            // Calculate total amount
            double totalAmount = orderItems.stream()
                    .mapToDouble(OrderItem::getSubtotal)
                    .sum();

            // Create payment
            CashPayment payment = new CashPayment(totalAmount, new Date());

            // Place an order with payment
            Order order = pharmacy.placeOrder("John Smith", orderItems, "R001", payment);
            System.out.println("Order placed successfully: " + order.getId());
            System.out.println("Payment status: " + payment.getStatus());

            // Display inventory status
            System.out.println("\nInventory Status:");
            System.out.println("Paracetamol: " + itemsInventory.getStockLevel("P001"));
            System.out.println("Ibuprofen: " + itemsInventory.getStockLevel("I001"));

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
