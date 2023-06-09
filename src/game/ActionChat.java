package game;

/**
 * This action allows the player to chat with a character.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionChat implements IAction{
    private Game game;

    /**
     * Instantiates a new Action chat.
     *
     * @param game the game
     */
    public ActionChat(Game game){
        this.game = game;
    }
    public String getName(){
        return "chat";
    }

    public String process(String[] args) {
        if (args.length < 1) {
            return "Chat with who?";
        } else if (args.length > 1) {
            return "You can only chat one person at a time.";
        } else if (!game.getInventory().hasItem("notepad")) {
            return "You need a notepad to chat with someone.";
        }

        String characterName = args[0];
        Character character = game.getWorld().getSuspect(characterName);

        if (!game.getWorld().doesCharacterExist(characterName)) {
            return "This character doesn't exist.";
        }

        Area currentArea = game.getWorld().getCurrentArea();

        if(!character.getCurrentArea().equals(currentArea)){
            return "You can't chat with " + characterName + " right now.";
        }

        return character.getChat(currentArea);
    }
}
