import game.Game;
import ui.TextUI;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        TextUI ui = new TextUI(game);

        ui.run();
    }
}