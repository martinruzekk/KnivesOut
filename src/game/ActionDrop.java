package game;

/**
 * Action that drops an item from the inventory to the current area.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionDrop implements IAction{
    private Game game;

    /**
     * Instantiates a new Action drop.
     *
     * @param game the game
     */
    public ActionDrop(Game game){
        this.game = game;
    }
    public String getName(){
        return "drop";
    }

    public String process(String[] args){
        if(args.length < 1){
            return "Drop what?";
        }

        if(args.length > 1){
            return "You can only drop one item at a time.";
        }

        String itemName = args[0];
        Item item = game.getInventory().getItem(itemName);

        if(item == null){
            return "You don't have that item in your inventory.";
        }

        if (!item.isDroppable()) {
            return "You can't drop that item.";
        }

        Area currentArea = game.getWorld().getCurrentArea();
        currentArea.addItem(item);
        game.getInventory().removeItem(itemName);

        return "You dropped the " + itemName + " down.";
    }
}
