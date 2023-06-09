package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Overall test of the play-through of the game.
 *
 * @author Martin Růžek
 * @version 1.0
 */
class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    /**
     * Test of successful play-through.
     */
    @Test
    void testSpeedRunSuccess() {
        speedRun();
        assertTrue(game.isGameOver());
    }

    /**
     * Test of failed play-through.
     */
    @Test
    void testSpeedRunFail() {
        speedRun();
        assertTrue(game.isGameOver());
    }

    /**
     * Fastest possible play-through of the game. (30 steps)
     */
    void speedRun() {
        game.processAction("go hall");
        game.processAction("go office");
        game.processAction("pick notepad");
        game.processAction("pick study_key");
        game.processAction("go hall");
        game.processAction("unlock study");
        game.processAction("go study");
        game.processAction("pick vials");
        game.processAction("go hall");
        game.processAction("go lounge");
        game.processAction("chat Walter");
        game.processAction("chat Linda");
        game.processAction("chat Richard");
        game.processAction("chat Joni");
        game.processAction("chat Meg");
        game.processAction("go hall");
        game.processAction("go balcony");
        game.processAction("chat Marta");
        game.processAction("chat Detective");
        game.processAction("go garden");
        game.processAction("investigate dogs");
        game.processAction("investigate footprints");
        game.processAction("go balcony");
        game.processAction("go hall");
        game.processAction("go living_room");
        game.processAction("pick report");
        game.processAction("chat Marta");
        game.processAction("go hall");
        game.processAction("go library");
        game.processAction("chat Jacob");
        game.processAction("murderer Marta");
    }
}