package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for action chat.
 */
class ActionChatTest {
    private Game game;
    private Character character;
    private Area area;
    private Area area2;

    @BeforeEach
    void setUp() {
        this.game = new Game();

        this.area = new Area("test", "test");
        Map<Area, String> chats = new HashMap<>();
        chats.put(area, "test chat");
        this.character = new Character("Test", false, area, chats, "test");
        this.game.getWorld().addSuspect(character);
        this.area2 = new Area("test2", "test2");
        this.game.getWorld().setCurrentArea(area2);
    }

    /**
     * Test for possible fails.
     */
    @Test
    void testActionChat() {
        assertEquals("Chat with who?", this.game.processAction("chat"));
        assertEquals("You can only chat one person at a time.", this.game.processAction("chat test test"));
        assertEquals("You need a notepad to chat with someone.", this.game.processAction("chat test"));
        this.game.getInventory().addItem(new Item("notepad", "test"));
        assertEquals("This character doesn't exist.", this.game.processAction("chat test666"));
        assertEquals("You can't chat with Test right now.", this.game.processAction("chat Test"));
    }

    /**
     * Test for possible successes.
     */
    @Test
    void testLogic() {
        this.game.getInventory().addItem(new Item("notepad", "test"));
        this.game.getWorld().setCurrentArea(area);
        assertEquals("test chat", this.game.processAction("chat Test"));
    }
}