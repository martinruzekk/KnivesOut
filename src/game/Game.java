package game;

import java.util.*;

public class Game {
    private boolean gameOver;
    private GameWorld world;
    private Set<IAction> actions;

    public Game() {
        gameOver = false;
        this.world = new GameWorld();

        actions = new HashSet<>();
        actions.add(new ActionHelp());
        actions.add(new ActionTerminate());
        actions.add(new ActionGo(this));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameWorld getWorld() {
        return world;
    }

    public String processAction(String action) {
        String[] words = action.split("[ \t]+");

        String actionName = words[0];
        String[] actionArgs = new String[words.length - 1];

        for (int i = 1; i < words.length; i++) {
            actionArgs[i - 1] = words[i];
        }

        for (IAction a : actions) {
            if (a.getName().equals(actionName)) {
                return a.process(actionArgs);
            }
        }

        return "Unknown action. Type 'help' for help.";
    }
}
