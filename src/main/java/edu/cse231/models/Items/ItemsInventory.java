package edu.cse231.models.Items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cse231.interfaces.Searchable;

public class ItemsInventory implements Searchable<Item> {

    private final Map<String, Item> itemCatalog;
    private final Map<String, Integer> stockLevels;

    public ItemsInventory() {
        this.itemCatalog = new HashMap<>();
        this.stockLevels = new HashMap<>();
    }

    public void addItemToCatalog(Item item) {
        if (itemCatalog.containsKey(item.getId())) {
            throw new IllegalArgumentException("Item with ID " + item.getId() + " already exists.");
        }
        itemCatalog.put(item.getId(), item);
        stockLevels.put(item.getId(), 0);
    }

    public void deleteItemFromCatalog(String itemId) {
        itemCatalog.remove(itemId);
        stockLevels.remove(itemId);
    }

    public int getStockLevel(String itemId) {
        return stockLevels.getOrDefault(itemId, 0);
    }

    public void addStock(String itemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to add must be positive.");
        }
        if (!itemCatalog.containsKey(itemId)) {
            throw new IllegalArgumentException("Cannot add stock for non-existent item ID: " + itemId);
        }
        stockLevels.compute(itemId, (k, v) -> (v == null ? 0 : v) + quantity);
    }

    public void reduceStock(String itemId, int quantity) throws InsufficientStockException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to reduce must be positive.");
        }
        if (!itemCatalog.containsKey(itemId)) {
            throw new IllegalArgumentException("Cannot reduce stock for non-existent item ID: " + itemId);
        }

        int currentStock = getStockLevel(itemId);
        if (currentStock < quantity) {
            throw new InsufficientStockException("Insufficient stock for item ID: " + itemId + ". Requested: " + quantity + ", Available: " + currentStock);
        }

        stockLevels.put(itemId, currentStock - quantity);
    }

    public boolean hasSufficientStock(String itemId, int quantity) {
        return getStockLevel(itemId) >= quantity;
    }

    @Override
    public Item searchById(String itemId) {
        Item original = itemCatalog.get(itemId);
        return (original != null) ? original.copy() : null;
    }

    @Override
    public Item searchByName(String nameQuery) {
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            return null;
        }
        String lowerCaseQuery = nameQuery.toLowerCase();
        return itemCatalog.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(lowerCaseQuery))
                .findFirst()
                .map(Item::copy)
                .orElse(null);
    }

    public List<Item> getAllItems() {
        Collection<Item> allOriginalItems = itemCatalog.values();
        List<Item> copies = new ArrayList<>();
        for (Item item : allOriginalItems) {
            copies.add(item.copy());
        }
        return Collections.unmodifiableList(copies);
    }
}
