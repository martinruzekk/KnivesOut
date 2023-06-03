package game;

import java.util.*;

public class Area {
    private String name;
    private String description;
    private Set<Area> exits;

    public Area(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addExit(Area exit) {
        exits.add(exit);
    }

    public Set<Area> getExits() {
        return exits;
    }

    public boolean hasExit(String exitName) {
        for (Area exit : exits) {
            if (exit.getName().equals(exitName)) {
                return true;
            }
        }

        return false;
    }

    public Area getExit(String exitName) {
        for (Area exit : exits) {
            if (exit.getName().equals(exitName)) {
                return exit;
            }
        }

        return null;
    }
}
