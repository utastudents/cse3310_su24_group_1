package uta.cse3310;

public class Player {
   
    private String playerName;
    private int playerID;
    private int points;
    private boolean statusPlayer;
    private String inventory;

    public Player(String playerName, int playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.points = 0;
        this.statusPlayer = false; 
        this.inventory = "nothing";
    }

    // Methods
    public String displayName() {
        return null;
    }

    public int displayScore() {
        return 0;
    }

    public boolean getStatus() {
        return true;
    }

   
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
       
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
      
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
     
    }

    public boolean isStatusPlayer() {
        return true;
    }

    public void setStatusPlayer(boolean statusPlayer) {
       
    }
}

