package game;

import java.util.Map;

/**
 * The type for Character Ransom.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class CharRansom extends Character{
    private GameWorld world;

    /**
     * Instantiates a new Char ransom.
     *
     * @param name        the name
     * @param isMurderer  the is murderer
     * @param currentArea the current area
     * @param chats       the chats
     * @param world       the world
     * @param greeting    the greeting
     */
    public CharRansom(String name, Boolean isMurderer, Area currentArea, Map<Area, String> chats, GameWorld world, String greeting) {
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
