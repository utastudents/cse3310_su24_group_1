package uta.cse3310;

public class Player {
   
    private String playerName;
    private String playerID;
    private int points;
    private boolean statusPlayer;
    private String[] inventory;

    public Player(String playerName, String playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.points = 0;
        this.statusPlayer = false; 
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
        return null;
    }

    public void setPlayerName(String playerName) {
       
    }

    public void getPlayerID() {
    
    }

    public void setPlayerID(String playerID) {
      
    }

    public int getPoints() {
        return 0;
    }

    public void setPoints(int points) {
     
    }

    public boolean isStatusPlayer() {
        return true;
    }

    public void setStatusPlayer(boolean statusPlayer) {
       
    }
}

