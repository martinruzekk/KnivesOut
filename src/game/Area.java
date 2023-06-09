package game;

import java.util.*;
import static util.RemoveEndComma.removeEndComma;

/**
 * This class defines the areas in the game.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class Area {
    private String name;
    private String description;
    private Map<String, Area> exits;
    private Map<String, Item> items;
    private boolean isLocked;

    /**
     * Instantiates a new Area.
     *
     * @param name        the name
     * @param description the description
     */
    public Area(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
        this.isLocked = false;
    }

    /**
     * Instantiates a new Area.
     *
     * @param name        the name
     * @param description the description
     * @param isLocked    states if the area is locked
     */
    public Area(String name, String description, boolean isLocked) {
        this(name, description);
        this.isLocked = isLocked;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds exit to the area.
     *
     * @param exit the exit
     */
    public void addExit(Area exit) {
        exits.put(exit.getName(), exit);
    }

    /**
     * Returns all exits.
     *
     * @return the exits
     */
    public Set<Area> getExits() {
        return new HashSet<>(exits.values());
    }

    /**
     * Returns all exits as a string.
     *
     * @return the exits string
     */
    public String getExitsString() {
        String result = "";
        for (Area a : exits.values()) {
            result += a.getName();
            if (a.getIsLocked()) {
                result += " (locked)";
            }
            result += ", ";
        }
        return removeEndComma(result);
    }

    /**
     * Returns if the area has an exit in the given direction.
     *
     * @param exitName the exit name
     * @return the boolean
     */
    public boolean hasExit(String exitName) {
        return exits.containsKey(exitName);
    }

    /**
     * Returns an exit in the given direction.
     *
     * @param exitName the exit name
     * @return the exit
     */
    public Area getExit(String exitName) {
        return exits.get(exitName);
    }

    /**
     * Adds item to the area.
     *
     * @param item the item
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    /**
     * Returns all items in the area.
     *
     * @return the items
     */
    public Set<Item> getItems() {
        return new HashSet<>(items.values());
    }

    /**
     * Returns true if an item with the given name is in the area.
     *
     * @param itemName the item name
     * @return the boolean
     */
    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    /**
     * Returns an item with the given name.
     *
     * @param itemName the item name
     * @return the item
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    /**
     * Removes an item with the given name.
     *
     * @param itemName the item name
     * @return the item
     */
    public Item removeItem(String itemName) {
        return items.remove(itemName);
    }

    /**
     * Returns true if the area is locked.
     *
     * @return the boolean
     */
    public boolean getIsLocked() {
        return isLocked;
    }

    /**
     * Sets locked.
     *
     * @param isLocked the is locked
     */
    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
