package game;

/**
 * The action that prints the items in player's inventory.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionInventory implements IAction{
    private Game game;

    /**
     * Instantiates a new Action inventory.
     *
     * @param game the game
     */
    public ActionInventory(Game game) {
        this.game = game;
    }

    public String getName() {
        return "inventory";
    }

    public String process(String[] args) {
        return game.getInventory().getInventoryString();
    }
}
