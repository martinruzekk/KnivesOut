package game;

import java.util.Map;

/**
 * The type for detective character.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class CharDetective extends Character{
    private GameWorld world;

    /**
     * Instantiates a new Char detective.
     *
     * @param name        the name
     * @param isMurderer  the is murderer
     * @param currentArea the current area
     * @param chats       the chats
     * @param greeting    the greeting
     * @param world       the world
     */
    public CharDetective(String name, Boolean isMurderer, Area currentArea, Map<Area, String> chats, String greeting, GameWorld world) {
        super(name, isMurderer, currentArea, chats, greeting);
        this.world = world;
    }

    @Override
    public String getChat(Area area) {
        String result = super.getChat(area);

        if(area.getName().equals("balcony")){
            this.setCurrentArea(world.getAllAreas().get("library"));
            return result;
        }

        return this.getGreeting();
    }
}
