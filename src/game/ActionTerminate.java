package game;

public class ActionTerminate implements IAction {
    public String getName() {
        return "quit";
    }

    public String process(String[] args) {
        // Game Over. - Game was terminated by setting gameOver to true.
        return "Game Over. Goodbye!";
    }
}
