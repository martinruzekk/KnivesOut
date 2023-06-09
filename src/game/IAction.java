package game;

/**
 * The interface defines methods necessary for implementation of game command.
 * For each command that can be used in the game, there is a class that handles
 * its processing. The class must implement the methods defined by this interface.
 *
 * @author Martin Růžek
 * @version 1.0
 */
public interface IAction {
    /**
     * Method returns the name of the game command. It is a single word that the
     * player uses to identify the game command on the console, e.g. 'help',
     * 'end', 'go' etc.
     *
     * @return the name
     */
    String getName();

    /**
     * Method for processing the game command. The number of parameters (expected
     * elements in the array) depends on the specific command, e.g. commands
     * 'help' and 'end' have no parameters, the command for moving between
     * locations will probably have one parameter (target location), commands for
     * trading, using or combining items may have more parameters.
     * <p>
     *     The method must check the parameters (number, correctness), make changes
     *     to the game state and then return the text that will be printed to the
     *     console.
     *     <p>
     *
     *
     * @param args the args
     * @return the string
     */
    String process(String[] args);
}
