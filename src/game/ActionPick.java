package game;

/**
 * The action picks up an item from the current area and puts it into the inventory.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionPick implements IAction{
    private Game game;

    /**
     * Instantiates a new Action pick.
     *
     * @param game the game
     */
    public ActionPick(Game game){
        this.game = game;
    }
    public String getName(){
        return "pick";
    }

    public String process(String[] args) {
        if (args.length == 0) {
            return "Pick what?";
        } else if (args.length > 1) {
            return "You can only pick one thing at a time.";
        }

        String itemName = args[0];

        Area currentArea = game.getWorld().getCurrentArea();
        if(!currentArea.hasItem(itemName)){
            return "There is no " + itemName + " here.";
        }

        Item item = game.getWorld().getCurrentArea().getItem(itemName);

        if(!item.isPickable()) {
            return "You can't pick that up.";
        }

        if(game.getInventory().isFull()) {
            return "Your inventory is full. You can't pick up " + itemName + ".";
        }

        game.getInventory().addItem(item);
        currentArea.removeItem(itemName);

        String result = "You picked up " + itemName + ". It's now in your inventory." +
                "\n" + item.getDescription();

        if (item.getAltDescription() != null) {
            return result + "\n" + item.getAltDescription();
        }

        return result;
    }
}
