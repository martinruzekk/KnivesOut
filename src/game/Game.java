package game;

import java.util.*;

/**
 * The main type of the game. It creates and stores links to
 * instances of the game world {@link GameWorld}
 * and the player's inventory {@link Inventory}.
 * It also creates and stores a list of actions and instances of the actions.
 *
 * @author Martin Růžek
 * @version 1.0, 2023-06-09
 */
public class Game {
    private boolean gameOver;
    private GameWorld world;
    private Set<IAction> actions;
    private Inventory inventory;

    /**
     * Class constructor, creates the game and the set of possible actions.
     * After the initiation, the game is ready to process commands.
     */
    public Game() {
        gameOver = false;
        this.inventory = new Inventory();
        this.world = new GameWorld(inventory);

        actions = new HashSet<>();
        actions.add(new ActionHelp());
        actions.add(new ActionTerminate(this));
        actions.add(new ActionGo(this));
        actions.add(new ActionLookAround(this));
        actions.add(new ActionExits(this));
        actions.add(new ActionInventory(this));
        actions.add(new ActionMurderer(this));
        actions.add(new ActionPick(this));
        actions.add(new ActionInvestigate(this));
        actions.add(new ActionChat(this));
        actions.add(new ActionNote(this));
        actions.add(new ActionReadNotes(this));
        actions.add(new ActionUnlock(this));
        actions.add(new ActionDrop(this));
    }

    /**
     * Is game over boolean. Returns {@code true}, if game is over; otherwise {@code false}.
     * This method is used by {@link ActionTerminate} and other implementations of {@link IAction}.
     * It doesn't matter if the game is over by winning, losing or by the command 'quit'.
     *
     * @return {@code true}, if game is over; otherwise {@code false}
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets game over.
     *
     * @param gameOver value setting the state of the game
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Returns link to map of the game world.
     *
     * @return game world
     */
    public GameWorld getWorld() {
        return world;
    }

    /**
     * Returns link to the player's inventory.
     *
     * @return player's inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * This method processes the command entered by the player.
     * It splits the command into words and tries to find the action
     * with the same name as the first word. If the action is found,
     * it is executed and the result is returned. If the action is not found,
     * the method returns a message about the unknown action.
     *
     * @param action text of the command entered by the player
     * @return text returned by the executed action
     */
    public String processAction(String action) {
        String[] words = action.split("[ \t]+");

        String actionName = words[0];
        String[] actionArgs = new String[words.length - 1];

        for (int i = 1; i < words.length; i++) {
            actionArgs[i - 1] = words[i];
        }

        String result = "Unknown action. Type 'help' for help.";

        for (IAction a : actions) {
            if (a.getName().equals(actionName)) {
                result = a.process(actionArgs);
            }
        }

        if(world.getGameState() != GameState.PLAYING) {
            gameOver = true;
        }

        return result;
    }

    /**
     * Returns the prologue of the game.
     *
     * @return the prologue
     */
    public String getPrologue() {
        return "\n\n"+
                "Welcome to the Knives Out game!" +
                "\n\n" +
                "Your name is Benoit Blanc. You are a private detective hired to solve the murder of "+
                "Harlan Thrombey. However you don't know who hired you. All you know is that you received" +
                " an anonymous letter and stack of money. " +
                "You arrived at the Thrombey mansion in Massachusetts just a few hours ago. The local police have already " +
                "ruled the case a suicide, but don't think it is that simple.\n" +
                "Your mission is to find out who the actual murderer of Harlan Thrombey is and figure out who hired you.\n"+
                "You can start by typing 'help' to see how you can proceed. \n" +
                "(You can use the 'help' command whenever you want during the play-through of the game)\n"+
                "Enjoy!\n\n\n"+
                "I'm at a parking lot. Lot of cars here, let's check the mansion."
                ;
    }

    /**
     * Returns the epilogue of the game.
     *
     * @return the epilogue
     */
    public String getEpilogue() {
        String result = "Thank you for playing. \n\n" +
                "(c) Martin Růžek, 2023";

        GameState state = world.getGameState();

        if (state == GameState.WON) {
            result = "\n\nYou have won the game. Congratulations! " +
                    "The murderer of Harlan Thrombey is indeed Ransom Drysdale. " +
                    "You are a true detective!"
                    + "\n\n"
                    + result;
            gameOver = true;
        } else if (state == GameState.LOST) {
            result = "\n\nYou have lost the game. "
                    + world.getMurderer().getName()
                    + " was not the murderer of Harlan Thrombey. "
                    + "Better luck next time!"
                    + "\n\n"
                    + result;
            gameOver = true;
        }

        return result;
    }
}
