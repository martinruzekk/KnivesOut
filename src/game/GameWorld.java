package game;

public class GameWorld {
    private Area currentArea;

    public GameWorld() {
        Area parkingLot = new Area("parking_lot", "You are in a parking lot.");
        Area mansion = new Area("mansion", "You are in a hall in the Thrombey mansion.");

        Area balcony = new Area("balcony", "You are on a balcony.");
        Area garden = new Area("garden", "You are in a garden.");
        Area backyardGate = new Area("backyard_gate", "You are at a backyard gate.");



        this.currentArea = parkingLot;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(Area area) {
        this.currentArea = area;
    }

}
