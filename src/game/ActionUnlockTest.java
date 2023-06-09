package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link ActionUnlock} class.
 */
class ActionUnlockTest {
    private Game game;
    private Area currentArea;
    private Area nextArea;
    private Area unlockedArea;
    private Area fakeArea;


    @BeforeEach
    void setUp() {
        this.game = new Game();

        this.currentArea = new Area("hall", "hall");
        this.nextArea = new Area("office", "office", true);
        this.unlockedArea = new Area("lib", "lib", false);
        this.fakeArea = new Area("fake", "fake");

        this.game.getWorld().setCurrentArea(currentArea);

        currentArea.addExit(nextArea);
        currentArea.addExit(unlockedArea);

        game.getInventory().addItem(new Item("hall_key", "hall_key", true, fakeArea));
    }

    /**
     * Test for potential failure.
     */
    @Test
    void testActionUnlock() {
        assertTrue(game.processAction("unlock").contains("You must specify a direction. You can go to:"));
        assertEquals("There is no exit in that direction.", game.processAction("unlock a"));
        assertEquals("There is no exit in that direction.", game.processAction("unlock fake"));
        assertEquals("The area is not locked.", game.processAction("unlock lib"));
        assertEquals("You dont have the key to unlock this area.", game.processAction("unlock office"));
    }

    /**
     * Test for potential success.
     */
    @Test
    void testLogic() {
        game.getInventory().addItem(new Item("office_key", "office_key", true, nextArea));
        assertEquals("You unlocked office. \n", game.processAction("unlock office"));
        assertFalse(nextArea.getIsLocked());
    }
}