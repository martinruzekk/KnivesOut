package game;

/**
 * The action that displays the help.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionHelp implements IAction {
    public String getName() {
        return "help";
    }

    public String process(String[] args) {
        return "Available commands:\n" +
                "help - displays this help\n" +
                "go <direction> - moves to the specified direction\n" +
                "look - displays the description of the current area\n" +
                "exits - displays the exits of the current area\n" +
                "unlock <area> - unlocks the specified area, but only if you have a key to it in your inventory\n" +
                "investigate <item> - displays the description of an item in current area or in your inventory\n"+
                "inventory - displays the items in the inventory\n" +
                "pick <item> - takes the specified item from the current area\n" +
                "drop <item> - drops the specified item to the current area\n" +
                "note <text> - adds the specified text to the notepad\n" +
                "read_notes - displays the contents of the notepad\n" +
                "chat <person> - talks to the specified person\n" +
                "murderer <person> - accuses the specified person of the murder\n" +
                "quit - quits the game\n";
    }
}
