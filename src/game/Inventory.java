package game;

import java.util.*;
import static util.RemoveEndComma.removeEndComma;

/**
 * Class represents inventory of the player.
 * <p>
 *     Inventory is implemented as a HashMap of Items.
 *     It is possible to add, remove and get items from the inventory.
 *     It is also possible to check if the inventory contains an item.
 *     </p>
 *
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class Inventory {
    private Map<String, Item> items;

    /**
     * Instantiates a new Inventory.
     */
    public Inventory() {
        this.items = new HashMap<>();
    }

    /**
     * Adds item.
     *
     * @param item the item
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    /**
     * Removes item.
     *
     * @param itemName the item name
     */
    public void removeItem(String itemName) {
        items.remove(itemName);
    }

    /**
     * Returns true if the inventory contains the item.
     *
     * @param itemName the item name
     * @return the boolean
     */
    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    /**
     * Returns the item.
     *
     * @param itemName the item name
     * @return the item
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    /**
     * Returns the inventory as a string.
     *
     * @return the inventory string
     */
    public String getInventoryString() {
        String result = "You have: ";

        for (String i : items.keySet()) {
            result += i + ", ";
        }

        result = removeEndComma(result);

        return result;
    }

    /**
     * Returns true if the inventory is full.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return items.size() >= 5;
    }
}
