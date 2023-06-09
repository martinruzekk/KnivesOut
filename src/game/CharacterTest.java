package game;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Character class.
 */
class CharacterTest {
    @Test
    void testCharacter() {
        Area area = new Area("Test", "Test");
        Area area2 = new Area("Test2", "Test2");
        Map<Area, String> chats1 = new HashMap<>();
        chats1.put(area2, "Test");
        Character character = new Character("Test", false, area, chats1, "greeting");

        assertEquals("Test", character.getName());
        assertEquals(area, character.getCurrentArea());
        character.setCurrentArea(area2);
        assertEquals(area2, character.getCurrentArea());
        assertEquals("greeting", character.getChat(area));
        assertTrue(character.getChat(area2).contains("Test"));
    }
}