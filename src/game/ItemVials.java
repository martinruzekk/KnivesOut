package game;

/**
 * Represents vials item.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ItemVials extends Item{
    private GameWorld world;

    /**
     * Instantiates a new Item vials.
     *
     * @param name           the name
     * @param description    the description
     * @param altDescription the alt description
     * @param world          the world
     */
    public ItemVials(String name, String description, String altDescription, GameWorld world) {
        super(name, description,true, null, altDescription);
        this.world = world;
    }

    @Override
    public String getAltDescription() {
        if(world.getSuspect("Marta").getChatCounter() == 2) {
            return super.getAltDescription();
        }

        return "";
    }
}
