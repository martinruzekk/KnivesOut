package game;

/**
 * The action returns the notes from the notepad.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionReadNotes implements IAction{
    private Game game;

    /**
     * Instantiates a new Action read notes.
     *
     * @param game the game
     */
    public ActionReadNotes(Game game){
        this.game = game;
    }
    public String getName(){
        return "read_notes";
    }

    public String process(String[] args) {
        if(!game.getInventory().hasItem("notepad")){
            return "You don't have a notepad.";
        }

        if (args.length > 0) {
            return "You can't read that.";
        }

        return game.getInventory().getItem("notepad").getNotesString();
    }
}
