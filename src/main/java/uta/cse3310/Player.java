package uta.cse3310;

public class Player {

    private String playerName;
    private int playerID;
    private int points;
    private boolean statusPlayer;
    private String inventory;

    // Constructor
    public Player(String playerName, String playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.points = 0;
<<<<<<< HEAD
        this.statusPlayer = false; 
        this.inventory = "nothing";
=======
        this.statusPlayer = false;
>>>>>>> new-branch
    }

    // Display the player's name
    public String displayName() {
<<<<<<< HEAD
        return playerName;
=======
        return "Player Name: " + this.playerName;
>>>>>>> new-branch
    }

    // Display the player's score
    public int displayScore() {
<<<<<<< HEAD
        return points;
=======
        return this.points;
>>>>>>> new-branch
    }

    // Get the player's status
    public boolean getStatus() {
<<<<<<< HEAD
        return statusPlayer;
    }

    // Getters and setters
    public String getPlayerName() {
        return playerName;
=======
        return this.statusPlayer;
    }

    public String getPlayerName() {
        return this.playerName;
>>>>>>> new-branch
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerID() {
<<<<<<< HEAD
        return playerID;
=======
        return this.playerID;
>>>>>>> new-branch
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getPoints() {
<<<<<<< HEAD
        return points;
=======
        return this.points;
>>>>>>> new-branch
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isStatusPlayer() {
<<<<<<< HEAD
        return statusPlayer;
=======
        return this.statusPlayer;
>>>>>>> new-branch
    }

    public void setStatusPlayer(boolean statusPlayer) {
        this.statusPlayer = statusPlayer;
    }
}

