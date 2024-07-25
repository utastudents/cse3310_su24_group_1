public class Player {
   
    private String playerName;
    private String playerID;
    private int points;
    private boolean statusPlayer;

    // Constructor
    public Player(String playerName, String playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.points = 0;
        this.statusPlayer = false; 
    }

    // Display the player's name
    public String displayName() {
        return playerName;
    }

    // Display the player's score
    public int displayScore() {
        return points;
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

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
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
}

