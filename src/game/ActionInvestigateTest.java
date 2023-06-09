package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for action investigate.
 */
class ActionInvestigateTest {
    private Game game;
    private Area area;
    private Area area2;

    @BeforeEach
    void setUp() {
        this.game = new Game();
        this.area = new Area("test", "test");
        this.area2 = new Area("test2", "test2");

        game.getWorld().setCurrentArea(area);

        game.getInventory().addItem(new Item("item1", "item1", true));

        area.addItem(new Item("item2", "item2", true));
        area2.addItem(new Item("item3", "item3", true));
        area.addItem(new Item("item4", "item4", false));
    }

    /**
     * Test for possible fails.
     */
    @Test
    void testActionInvestigate() {
        assertEquals("Investigate what?", game.processAction("investigate"));
        assertEquals("You can only investigate one thing at a time.", game.processAction("investigate item1 item2"));
        assertEquals("You don't have item3 in your inventory nor is it in this area.", game.processAction("investigate item3"));
        assertEquals("You can't investigate that.", game.processAction("investigate item2"));
    }

    /**
     * Test for possible successes.
     */
    @Test
    void testLogic() {
        assertEquals("item1", game.processAction("investigate item1"));
        assertEquals("item4", game.processAction("investigate item4"));
    }
}