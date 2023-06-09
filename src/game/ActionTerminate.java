package game;

/**
 * The action quits the game.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionTerminate implements IAction {
    private Game game;

    /**
     * Instantiates a new Action terminate.
     *
     * @param game the game
     */
    public ActionTerminate(Game game) {
        this.game = game;
    }

    public String getName() {
        return "quit";
    }

    public String process(String[] args) {
        game.setGameOver(true);
        return "Game Over. Goodbye!";
    }
}
