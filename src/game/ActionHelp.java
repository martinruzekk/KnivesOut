package game;

public class ActionHelp implements IAction {
    public String getName() {
        return "help";
    }

    public String process(String[] args) {
        return "You can type 'quit' to quit.";
    }
}
