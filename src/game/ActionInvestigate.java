package game;

/**
 * The action that allows the player to investigate an item. Either on the ground or in the inventory.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionInvestigate implements IAction{
    private Game game;

    /**
     * Instantiates a new Action investigate.
     *
     * @param game the game
     */
    public ActionInvestigate(Game game){
        this.game = game;
    }
    public String getName(){
        return "investigate";
    }

    public String process(String[] args){
        if(args.length == 0){
            return "Investigate what?";
        } else if(args.length > 1){
            return "You can only investigate one thing at a time.";
        }

        String itemName = args[0];

        if (game.getInventory().hasItem(itemName)) {
            Item item = game.getInventory().getItem(itemName);
            item.setInvestigated(true);
            return item.getDescription();
        } else if (game.getWorld().getCurrentArea().hasItem(itemName) && !game.getWorld().getCurrentArea().getItem(itemName).isPickable()) {
            Item item = game.getWorld().getCurrentArea().getItem(itemName);
            item.setInvestigated(true);
            return item.getDescription();
        } else if (game.getWorld().getCurrentArea().hasItem(itemName) && game.getWorld().getCurrentArea().getItem(itemName).isPickable()) {
            return "You can't investigate that.";
        }

        return "You don't have " + itemName + " in your inventory nor is it in this area.";
    }
}
