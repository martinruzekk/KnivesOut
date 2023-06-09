package ui;

import game.Game;

import java.util.*;


/**
 * Represents text user interface of the game.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class TextUI {
    private Game game;
    private Scanner scanner;

    /**
     * Instantiates a new Text ui.
     *
     * @param game the game
     */
    public TextUI(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the game.
     */
    public void run() {
        System.out.println(game.getPrologue());

        while (!game.isGameOver()) {
            System.out.print("> ");
            String action = scanner.nextLine();
            System.out.println(game.processAction(action));
        }

        System.out.println(game.getEpilogue());
    }
}
