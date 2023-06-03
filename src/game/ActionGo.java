package game;

public class ActionGo implements IAction{
    private Game game;

    public ActionGo(Game game) {
        this.game = game;
    }

    public String getName() {
        return "go";
    }

    public String process(String[] args) {
        if (args.length == 0) {
            return "You must specify a direction.";
        }

        String direction = args[0];
        Area currentArea = game.getWorld().getCurrentArea();

        if (!currentArea.hasExit(direction)) {
            return "There is no exit in that direction.";
        }

        Area nextArea = currentArea.getExit(direction);
        game.getWorld().setCurrentArea(nextArea);


        return "You went to " + direction + ". \n" + nextArea.getDescription() + "\n";
    }
}
