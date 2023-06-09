package game;

import java.util.Map;

/**
 * The type for Character Marta.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class CharMarta extends Character{
    private GameWorld world;

    /**
     * Instantiates a new Char marta.
     *
     * @param name        the name
     * @param isMurderer  the is murderer
     * @param currentArea the current area
     * @param chats       the chats
     * @param greeting    the greeting
     * @param world       the world
     */
    public CharMarta(String name, Boolean isMurderer, Area currentArea, Map<Area, String> chats, String greeting, GameWorld world) {
        super(name, isMurderer, currentArea, chats, greeting);
        this.world = world;
    }

    @Override
    public String getChat(Area area) {
        String result = this.getGreeting();

        if(area.getName().equals("balcony")){
            this.setCurrentArea(world.getAllAreas().get("living_room"));
            result = super.getChat(area);
        }

        if (area.getName().equals("living_room")) {
            result = super.getChat(area);
             if(world.getInventory().hasItem("vials")) {
                 result += "\nBlanc (to himself): Marta said that she gives him 1 full vial of Toradol and half a vial of Morphine, but the vials I found in the study show otherwise.\n" +
                         "There is half a vial of Toradol and an empty vial of Morphine. That is very strange and very sus.\n";
             }
             this.setCurrentArea(world.getAllAreas().get("library"));
        }

        return result;
    }
}
