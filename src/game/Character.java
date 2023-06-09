package game;

import java.util.Map;

/**
 * This class defines the characters in the game.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class Character implements ICharacter {
    private String name;
    private boolean isMurderer;
    private Area currentArea;
    private Map<Area, String> chats;
    private String greeting;
    protected int chatCounter = 0;

    /**
     * Instantiates a new Character.
     *
     * @param name        the name
     * @param isMurderer  states if the character is actually the murderer
     * @param currentArea the current area
     * @param chats       Map of chats
     * @param greeting    the greeting
     */
    public Character(String name, Boolean isMurderer, Area currentArea, Map<Area, String> chats, String greeting) {
        this.name = name;
        this.isMurderer = isMurderer;
        this.currentArea = currentArea;
        this.chats = chats;
        this.greeting = greeting;
    }

    /**
     * Returns name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns greeting.
     *
     * @return the greeting
     */
    public String getGreeting() {
        return this.greeting;
    }

    /**
     * Is murderer boolean.
     *
     * @return the boolean
     */
    public boolean getIsMurderer() {
        return this.isMurderer;
    }

    /**
     * Returns current area.
     *
     * @return the current area
     */
    public Area getCurrentArea() {
        return this.currentArea;
    }

    /**
     * Sets current area.
     *
     * @param currentArea the current area
     */
    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public String getChat(Area area) {
        if (this.chats.containsKey(area)) {
            this.chatCounter++;
            return this.chats.get(area);
        }

        return this.greeting;
    }

    /**
     * Returns chat counter.
     *
     * @return the chat counter
     */
    public int getChatCounter() {
        return this.chatCounter;
    }

    /**
     * Returns number of possible chats
     *
     * @return number of possible chats in different areas
     */
    public int getChatsAmount() {
        return this.chats.size();
    }
}
