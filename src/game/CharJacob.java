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
        int marta = this.world.getSuspect("Marta").getChatCounter();
        int detective = this.world.getSuspect("Detective").getChatCounter();
        int linda = this.world.getSuspect("Linda").getChatCounter();
        int walt = this.world.getSuspect("Walter").getChatCounter();
        int richard = this.world.getSuspect("Richard").getChatCounter();
        int joni = this.world.getSuspect("Joni").getChatCounter();
        int meg = this.world.getSuspect("Meg").getChatCounter();

        if(area.getName().equals("library") && this.chatCounter == 0
                && marta == 2 && detective == 1 && linda == 1 && walt == 1 && richard == 1
                && this.world.getInventory().hasItem("vials") && this.world.getInventory().hasItem("report")) {

            return super.getChat(area);
        } else if(marta != 2 || detective != 1 || linda != 1 || walt != 1 || richard != 1
                || meg != 1 || joni != 1) {

            return "I should talk to the others first.";
        } else if (!this.world.getInventory().hasItem("vials")
                || !this.world.getInventory().hasItem("report")
                || !this.world.hasBeenInvestigated("dogs")
                || !this.world.hasBeenInvestigated("footprints")) {

            return "I should investigate everything properly first.";
        }

        return this.getGreeting();
    }
}
