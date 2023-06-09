package game;

import java.util.*;

/**
 * Represents an item in the game.
 * <p>
 *     Item is represented by its name, description, pickability, area it unlocks, notes, alternative description,
 *     and droppability.
 *     Item can be picked up, dropped, used, combined with other items, and investigated.
 * </p>
 *
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class Item {
    private String name;
    private String description;
    private boolean isPickable;
    private Area unlocks;
    private List<String> notes;
    private  String altDescription;
    private boolean isInvestigated = false;
    private boolean isDroppable;


    /**
     * Instantiates a new Item.
     *
     * @param name           the name
     * @param description    the description
     * @param isPickable     the is pickable
     * @param unlocks        the unlocks
     * @param altDescription the alt description
     * @param isDroppable    the is droppable
     */
    public Item(String name, String description, boolean isPickable, Area unlocks, String altDescription, Boolean isDroppable) {
        this.name = name;
        this.description = description;
        this.isPickable = isPickable;
        this.unlocks = unlocks;
        this.notes = new ArrayList<>();
        this.altDescription = altDescription;
        this.isDroppable = isDroppable;
    }

    /**
     * Instantiates a new Item.
     *
     * @param name        the name
     * @param description the description
     */
    public Item(String name, String description) {
        this(name, description, true, null, null, true);
    }

    /**
     * Instantiates a new Item.
     *
     * @param name        the name
     * @param description the description
     * @param isPickable  the is pickable
     */
    public Item(String name, String description, boolean isPickable) {
        this(name, description, isPickable, null, null, true);
    }

    /**
     * Instantiates a new Item.
     *
     * @param name        the name
     * @param description the description
     * @param isPickable  the is pickable
     * @param unlocks     the unlocks
     */
    public Item (String name, String description, boolean isPickable, Area unlocks) {
        this(name, description, isPickable, unlocks, null, true);
    }

    /**
     * Instantiates a new Item.
     *
     * @param name           the name
     * @param description    the description
     * @param isPickable     the is pickable
     * @param unlocks        the unlocks
     * @param altDescription the alt description
     */
    public Item (String name, String description, boolean isPickable, Area unlocks, String altDescription) {
        this(name, description, isPickable, unlocks, altDescription, true);
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Returns true if the item is pickable.
     *
     * @return the boolean
     */
    public boolean isPickable() {
        return this.isPickable;
    }

    /**
     * Gets notes as a string.
     *
     * @return the notes string
     */
    public String getNotesString() {
        String result = "";

        for (int i = 0; i < notes.size(); i++) {
            result += i + 1 + ". " + notes.get(i) + "\n";
        }

        return result;
    }

    /**
     * Adds a note.
     *
     * @param note the note
     */
    public void addNote(String note) {
        if(this.name.equals("notepad")) {
            this.notes.add(note);
        }
    }

    /**
     * Gets alt description.
     *
     * @return the alt description
     */
    public String getAltDescription() {
        return this.altDescription;
    }

    /**
     * Is droppable boolean.
     *
     * @return the boolean
     */
    public boolean isDroppable() {
        return this.isDroppable;
    }

    /**
     * Returns the area that the item unlocks.
     *
     * @return the area the item unlocks
     */
    public Area getUnlocks() {
        return this.unlocks;
    }
}
