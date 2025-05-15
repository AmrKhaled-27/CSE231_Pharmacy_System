package edu.cse231.models.Pharmacy;

import edu.cse231.models.Items.ItemsInventory;
import edu.cse231.models.Orders.OrderHistory;
import edu.cse231.models.Recipies.RecipesInventory;

public class PharmacyBranch extends Pharmacy {

    public PharmacyBranch(String name, String location, ItemsInventory itemsInventory, OrderHistory orderHistory, RecipesInventory recipesInventory) {
        super(name, location, itemsInventory, orderHistory, recipesInventory);
    }
}
