package game;


/**
 * The action that shows the exits of the current area.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionExits implements IAction{
    private Game game;

    /**
     * Instantiates a new Action exits.
     *
     * @param game the game
     */
    public ActionExits(Game game){
        this.game = game;
    }
    public String getName(){
        return "exits";
    }

    public String process(String[] args){
        if(args.length > 0){
            return "I'm not going anywhere. I'm just looking where I can go.";
        }

        Area currentArea = game.getWorld().getCurrentArea();

        return "You can go to: " + currentArea.getExitsString();
    }
}
