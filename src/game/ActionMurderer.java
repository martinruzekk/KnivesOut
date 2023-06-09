package game;

/**
 * The action sets the player's suspect of the murder.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionMurderer implements IAction{
    private Game game;

    /**
     * Instantiates a new Action murderer.
     *
     * @param game the game
     */
    public ActionMurderer(Game game) {
        this.game = game;
    }

    public String getName() {
        return "murderer";
    }

    public String process(String[] args) {
        if(args.length > 1) {
            return "There is only 1 true murderer. You can mark only a single killer.";
        } else if (args.length < 1) {
            return "You must specify the murderers name first.";
        } else if(!game.getWorld().isGuessUnlocked()) {
            return "I can't close the investigation yet. I haven't found all the evidence yet.";
        }

        String murderer = args[0];
        if (!game.getWorld().getSuspects().containsKey(murderer)) {
            return "You have marked unknown suspect. Please try again.";
        }

        game.getWorld().setMurderer(murderer);

        return "You have marked " + murderer + " as the murderer of Harlan Thrombey";
    }
}
