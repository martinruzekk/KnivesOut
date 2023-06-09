package game;

/**
 * The action unlocks the area in the given direction.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionUnlock implements IAction{
    private Game game;

    /**
     * Instantiates a new Action unlock.
     *
     * @param game the game
     */
    public ActionUnlock(Game game) {
        this.game = game;
    }

    public String getName() {
        return "unlock";
    }

    public String process(String[] args) {
        if (args.length < 1) {
            return "You must specify a direction. You can go to: " + game.getWorld().getCurrentArea().getExitsString();
        }

        String direction = args[0];
        Area currentArea = game.getWorld().getCurrentArea();

        if (!currentArea.hasExit(direction)) {
            return "There is no exit in that direction.";
        }

        Area nextArea = currentArea.getExit(direction);

        if (!nextArea.getIsLocked()) {
            return "The area is not locked.";
        }

        if(!game.getInventory().hasItem(direction + "_key")) {
            return "You dont have the key to unlock this area.";
        }

        if(!game.getInventory().getItem(direction + "_key").getUnlocks().equals(nextArea)) {
            return "You can't use this item to unlock this area.";
        }

        nextArea.setLocked(false);

        return "You unlocked " + direction + ". \n";
    }
}
