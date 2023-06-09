package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    @Test
    void testInventory() {
        Inventory inventory = new Inventory();
        Item item = new Item("Test", "Test");
        Item item2 = new Item("Test2", "Test2");
        Item item3 = new Item("Test3", "Test3");
        Item item4 = new Item("Test4", "Test4");
        Item item5 = new Item("Test5", "Test5");
        Item item6 = new Item("Test6", "Test6");

        assertEquals("You have:", inventory.getInventoryString());
        assertNull(inventory.getItem("Test"));
        assertFalse(inventory.hasItem("Test"));
        assertFalse(inventory.isFull());

        inventory.addItem(item);
        assertEquals("You have: Test", inventory.getInventoryString());
        assertEquals(item, inventory.getItem("Test"));
        assertTrue(inventory.hasItem("Test"));
        assertFalse(inventory.isFull());

        inventory.addItem(item2);
        assertEquals("You have: Test, Test2", inventory.getInventoryString());
        assertEquals(item2, inventory.getItem("Test2"));
        assertTrue(inventory.hasItem("Test2"));
        assertFalse(inventory.isFull());

        inventory.addItem(item3);
        assertEquals("You have: Test, Test2, Test3", inventory.getInventoryString());
        assertEquals(item3, inventory.getItem("Test3"));
        assertTrue(inventory.hasItem("Test3"));
        assertFalse(inventory.isFull());

        inventory.addItem(item4);
        assertEquals("You have: Test, Test2, Test3, Test4", inventory.getInventoryString());
        assertEquals(item4, inventory.getItem("Test4"));
        assertTrue(inventory.hasItem("Test4"));
        assertTrue(inventory.isFull());

        inventory.addItem(item5);
        assertEquals("You have: Test, Test2, Test3, Test4, Test5", inventory.getInventoryString());
        assertEquals(item5, inventory.getItem("Test5"));
        assertTrue(inventory.hasItem("Test5"));
        assertTrue(inventory.isFull());

        inventory.addItem(item6);
        assertEquals("You have: Test, Test2, Test3, Test4, Test5, Test6", inventory.getInventoryString());
        assertEquals(item6, inventory.getItem("Test6"));
        assertTrue(inventory.hasItem("Test6"));
        assertTrue(inventory.isFull());
    }
}