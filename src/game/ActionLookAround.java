package game;

import java.util.Collection;
import static util.RemoveEndComma.removeEndComma;

/**
 * The action that allows the player to look around the area.
 * It returns a string that contains the name of the area, the items in the area and the exits from the area.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public class ActionLookAround implements IAction{
    private Game game;

    /**
     * Instantiates a new Action look around.
     *
     * @param game the game
     */
    public ActionLookAround(Game game) {
        this.game = game;

    }

    public String getName() {
        return "look";
    }

    public String process(String[] args) {
        if (args.length > 0) {
            return "I don't understand what you want to look at. I'm just looking around, not at anything in particular.";
        }

        Area currentArea = game.getWorld().getCurrentArea();
        Collection<Item> items = currentArea.getItems();

        String result = "You are in " + currentArea.getName() + ".\n";

        if (!items.isEmpty()) {
            result += "You see: ";

            for (Item i : items) {
                result += i.getName() + ", ";
            }

            result = removeEndComma(result);
            result += "\n";
        }

        if (game.getWorld().getCurrentArea().getExits().size() > 0) {
            result += "Possible exits: " + game.getWorld().getCurrentArea().getExitsString();
            result = removeEndComma(result);
        }

        if(game.getWorld().getSuspectsInArea(currentArea).size() > 0) {
            result += "\nPeople in the room: " + game.getWorld().getPeopleInAreaString(currentArea);
        }

        result = removeEndComma(result);

        return result;
    }
}
