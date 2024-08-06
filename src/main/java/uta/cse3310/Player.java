package uta.cse3310;

public class Player {

    private String playerName;
    private int playerID;
    private int points;
    private boolean statusPlayer;
    private String inventory;

    // Constructor
    public Player(String playerName, int playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.points = 0;
        this.statusPlayer = false; 
        this.inventory = "";
    }

    // Get the player's status
    public boolean getStatus() {
        return statusPlayer;
    }

    // Getters and setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isStatusPlayer() {
        return statusPlayer;
    }

    public void setStatusPlayer(boolean statusPlayer) {
        this.statusPlayer = statusPlayer;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getInventory() {
        return inventory;
    }
}

