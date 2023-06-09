package game;

/**
 * The interface defines methods necessary for implementation of game character.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public interface ICharacter {
    /**
     * Returns text for the character in specified area.
     *
     * @param area the area
     * @return the chat
     */
    public String getChat(Area area);
}
