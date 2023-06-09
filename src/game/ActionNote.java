package game;

/**
 * The action adds a note to the notepad.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionNote implements IAction{
    private Game game;

    /**
     * Instantiates a new Action note.
     *
     * @param game the game
     */
    public ActionNote (Game game){
        this.game = game;
    }
    public String getName(){
        return "note";
    }

    public String process(String[] args) {
        if(!game.getInventory().hasItem("notepad")){
            return "You don't have a notepad.";
        }

        if (args.length == 0) {
            return "Note what?";
        }

        String note = "";

        for (String a : args) {
            note += a + " ";
        }

        game.getInventory().getItem("notepad").addNote(note);

        return "Added to notepad: " + note;
    }
}
