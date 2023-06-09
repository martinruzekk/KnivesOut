package game;

import java.util.Map;

/**
 * The type Character Jacob.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class CharJacob extends Character{
    private GameWorld world;

    /**
     * Instantiates a new Char jacob.
     *
     * @param name        the name
     * @param isMurderer  the is murderer
     * @param currentArea the current area
     * @param chats       the chats
     * @param greeting    the greeting
     * @param world       the world
     */
    public CharJacob(String name, Boolean isMurderer, Area currentArea, Map<Area, String> chats, String greeting, GameWorld world) {
        super(name, isMurderer, currentArea, chats, greeting);
        this.world = world;
    }

    @Override
    public String getChat(Area area) {
        if(area.getName().equals("library") && this.chatCounter == 0
                && this.world.isDialogFinished()
                && this.world.areAllItemsFound()) {
            return super.getChat(area);
        } else if(!this.world.isDialogFinished()) {
            return "I should talk to the others first.";
        } else if (!this.world.areAllItemsFound()) {
            return "I should investigate everything properly first.";
        }

        return this.getGreeting();
    }
}
