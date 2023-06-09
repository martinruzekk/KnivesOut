package game;

import java.util.Map;

/**
 * The type for Character Richard.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class CharRichard extends Character{
    private GameWorld world;

    /**
     * Instantiates a new Char richard.
     *
     * @param name        the name
     * @param isMurderer  the is murderer
     * @param currentArea the current area
     * @param chats       the chats
     * @param greeting    the greeting
     * @param world       the world
     */
    public CharRichard(String name, Boolean isMurderer, Area currentArea, Map<Area, String> chats, String greeting, GameWorld world) {
        super(name, isMurderer, currentArea, chats, greeting);
        this.world = world;
    }

    @Override
    public String getChat(Area area) {
        String result = super.getChat(area);

        if(area.getName().equals("lounge")){
            this.setCurrentArea(world.getAllAreas().get("library"));
            return result;
        }

        return this.getGreeting();
    }
}
