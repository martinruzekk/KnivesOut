package game;

/**
 * The action that allows the player to move to another area.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionGo implements IAction{
    private Game game;

    /**
     * Instantiates a new Action go.
     *
     * @param game the game
     */
    public ActionGo(Game game) {
        this.game = game;
    }

    public String getName() {
        return "go";
    }

    public String process(String[] args) {
        if (args.length < 1) {
            return "You must specify a direction. You can go to: " + game.getWorld().getCurrentArea().getExitsString();
        } else if (args.length > 1) {
            return "You can only go to one direction at a time.";
        }

        String direction = args[0];
        Area currentArea = game.getWorld().getCurrentArea();

        if (!currentArea.hasExit(direction)) {
            return "There is no exit in that direction.";
        }

        Area nextArea = currentArea.getExit(direction);

        if(nextArea.getIsLocked()) {
            return "The door is locked.";
        }

        game.getWorld().setCurrentArea(nextArea);


        return "You went to " + direction + ". \n" + nextArea.getDescription() + "\n";
    }
}
