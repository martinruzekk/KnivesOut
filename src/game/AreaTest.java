package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Area class.
 *
 * @author Martin Růžek
 * @version 1.0
 */
class AreaTest {
    @Test
    void testArea() {
        Area area = new Area("Test", "Test");
        Area area2 = new Area("Test2", "Test2");

        assertFalse(area.hasExit(area2.getName()));
        assertFalse(area.hasExit(area.getName()));
        assertFalse(area2.hasExit(area.getName()));

        assertNull(area.getExit(area.getName()));
        assertNull(area.getExit(area2.getName()));
        assertNull(area2.getExit(area.getName()));

        assertTrue(area.getExits().isEmpty());

        area.addExit(area);
        area.addExit(area2);
        area2.addExit(area);

        assertNotNull(area.getExits());
        assertEquals(2,area.getExits().size());

        assertNotNull(area2.getExits());
        assertEquals(1,area2.getExits().size());

        assertTrue(area.hasExit(area2.getName()));
        assertTrue(area.hasExit(area.getName()));
        assertTrue(area2.hasExit(area.getName()));

        assertEquals(area, area.getExit(area.getName()));
        assertEquals(area2, area.getExit(area2.getName()));
        assertEquals(area, area2.getExit(area.getName()));

        assertFalse(area.hasExit("Test3"));

        assertTrue(area.getItems().isEmpty());

        area.addItem(new Item("TestItem", "TestItem"));
        assertTrue(area.hasItem("TestItem"));
        assertFalse(area.hasItem("TestItem2"));
        assertEquals("TestItem", area.getItem("TestItem").getName());
        assertNull(area.getItem("TestItem2"));


        area.addItem(new Item("TestItem2", "TestItem2"));
        assertTrue(area.hasItem("TestItem2"));
        assertEquals("TestItem2", area.getItem("TestItem2").getName());

        assertEquals(2,area.getItems().size());

        area.removeItem("TestItem");
        assertFalse(area.hasItem("TestItem"));
        assertEquals(1,area.getItems().size());
        area.removeItem("TestItem2");
        assertEquals(0,area.getItems().size());

        Area area3 = new Area("Test3", "Test3", true);
        assertTrue(area3.getIsLocked());
        area3.setLocked(false);
        assertFalse(area3.getIsLocked());
    }
}