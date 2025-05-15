
import edu.cse231.models.Items.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemsInventoryTest {

    private ItemsInventory inventory;
    private Medication paracetamol;
    private Supplement vitaminC;
    private HealthDevice thermometer;

    @BeforeEach
    public void setUp() {
        inventory = new ItemsInventory();

        // Create test items
        paracetamol = new Medication(
                "P001",
                "Paracetamol 500mg",
                5.99,
                "500mg",
                "Tablet",
                "Paracetamol"
        );

        vitaminC = new Supplement(
                "S001",
                "Vitamin C",
                12.99,
                "Ascorbic Acid",
                "1000mg daily"
        );

        thermometer = new HealthDevice(
                "D001",
                "Digital Thermometer",
                24.99,
                "HealthTech",
                "DT-100"
        );
    }

    @Test
    public void testAddItemToCatalog() {
        inventory.addItemToCatalog(paracetamol);
        assertEquals(0, inventory.getStockLevel("P001"));

        // Test adding duplicate item
        try {
            inventory.addItemToCatalog(paracetamol);
            fail("Should have thrown IllegalArgumentException for duplicate item");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("already exists"));
        }
    }

    @Test
    public void testDeleteItemFromCatalog() {
        inventory.addItemToCatalog(paracetamol);
        inventory.addStock("P001", 10);

        inventory.deleteItemFromCatalog("P001");
        assertEquals(0, inventory.getStockLevel("P001"));
        assertNull(inventory.searchById("P001"));
    }

    @Test
    public void testStockManagement() {
        inventory.addItemToCatalog(paracetamol);

        // Test adding stock
        inventory.addStock("P001", 10);
        assertEquals(10, inventory.getStockLevel("P001"));

        // Test reducing stock
        try {
            inventory.reduceStock("P001", 5);
            assertEquals(5, inventory.getStockLevel("P001"));
        } catch (InsufficientStockException e) {
            fail("Should not throw InsufficientStockException");
        }

        // Test insufficient stock
        try {
            inventory.reduceStock("P001", 10);
            fail("Should have thrown InsufficientStockException");
        } catch (InsufficientStockException e) {
            assertTrue(e.getMessage().contains("Insufficient stock"));
        }

        // Test invalid quantity
        try {
            inventory.addStock("P001", -5);
            fail("Should have thrown IllegalArgumentException for negative quantity");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("must be positive"));
        }
    }

    @Test
    public void testSearchById() {
        inventory.addItemToCatalog(paracetamol);
        inventory.addItemToCatalog(vitaminC);

        Item found = inventory.searchById("P001");
        assertNotNull(found);
        assertEquals("Paracetamol 500mg", found.getName());

        // Test non-existent item
        assertNull(inventory.searchById("NONEXISTENT"));
    }

    @Test
    public void testSearchByName() {
        inventory.addItemToCatalog(paracetamol);
        inventory.addItemToCatalog(vitaminC);

        // Test exact match
        Item found = inventory.searchByName("Paracetamol 500mg");
        assertNotNull(found);
        assertEquals("P001", found.getId());

        // Test partial match
        found = inventory.searchByName("Paracetamol");
        assertNotNull(found);
        assertEquals("P001", found.getId());

        // Test case insensitive
        found = inventory.searchByName("paracetamol");
        assertNotNull(found);
        assertEquals("P001", found.getId());

        // Test non-existent item
        assertNull(inventory.searchByName("NONEXISTENT"));

        // Test null or empty query
        assertNull(inventory.searchByName(null));
        assertNull(inventory.searchByName(""));
        assertNull(inventory.searchByName("   "));
    }

    @Test
    public void testGetAllItems() {
        inventory.addItemToCatalog(paracetamol);
        inventory.addItemToCatalog(vitaminC);
        inventory.addItemToCatalog(thermometer);

        var allItems = inventory.getAllItems();
        assertEquals(3, allItems.size());

        // Verify items are copies by modifying each item and checking original remains unchanged
        for (Item item : allItems) {
            String originalName = item.getName();
            double originalPrice = item.getPrice();

            // Modify the copy
            item.setName("Modified " + originalName);
            item.setPrice(originalPrice + 10.0);

            // Get original item from inventory
            Item originalItem = inventory.searchById(item.getId());

            // Verify original item is unchanged
            assertEquals(originalName, originalItem.getName());
            assertEquals(originalPrice, originalItem.getPrice());

            // Verify the copy was modified
            assertEquals("Modified " + originalName, item.getName());
            assertEquals(originalPrice + 10.0, item.getPrice());
        }

        // Verify the list is unmodifiable
        assertThrows(UnsupportedOperationException.class, () -> {
            allItems.add(paracetamol);
        });
    }

    @Test
    public void testHasSufficientStock() {
        inventory.addItemToCatalog(paracetamol);
        inventory.addStock("P001", 10);

        assertTrue(inventory.hasSufficientStock("P001", 5));
        assertTrue(inventory.hasSufficientStock("P001", 10));
        assertFalse(inventory.hasSufficientStock("P001", 15));
        assertFalse(inventory.hasSufficientStock("NONEXISTENT", 1));
    }
}
