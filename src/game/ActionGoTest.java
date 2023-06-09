package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for action go.
 */
class ActionGoTest {
    private Game game;
    private Area area;
    private Area area2;
    private Area area3;


    @BeforeEach
    void setUp() {
        this.game = new Game();

        this.area = new Area("Test", "Test");
        this.area2 = new Area("Test2", "Test2");
        this.area3 = new Area("Test3", "Test3", true);

        this.area.addExit(this.area2);
        this.area2.addExit(this.area);
        this.area.addExit(this.area3);

        this.game.getWorld().setCurrentArea(area);
    }

    /**
     * Test for possible fails.
     */
    @Test
    void testActionGo() {
        assertTrue(game.processAction("go").contains("You must specify a direction. You can go to:"));
        assertEquals("Test", game.getWorld().getCurrentArea().getName());

        assertEquals("You can only go to one direction at a time.", game.processAction("go Test Test2"));
        assertEquals("Test", game.getWorld().getCurrentArea().getName());

        assertEquals("There is no exit in that direction.", game.processAction("go Test666"));
        assertEquals("Test", game.getWorld().getCurrentArea().getName());

        assertEquals("The door is locked.", game.processAction("go Test3"));
        assertEquals("Test", game.getWorld().getCurrentArea().getName());
    }

    /**
     * Test for possible successes.
     */
    @Test
    void testLogic() {
        assertTrue(game.processAction("go Test2").contains("You went to Test2"));
        assertEquals("Test2", game.getWorld().getCurrentArea().getName());
    }

}