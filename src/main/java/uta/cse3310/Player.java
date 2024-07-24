public class Player {

    private String playerName;
    private String playerID;
    private int points;
    private boolean statusPlayer;

    public Player(String playerName, String playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
        this.points = 0;
        this.statusPlayer = false;
    }

    // Methods
    public String displayName() {
        return "Player Name: " + this.playerName;
    }

    public int displayScore() {
        return this.points;
    }

    public boolean getStatus() {
        return this.statusPlayer;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isStatusPlayer() {
        return this.statusPlayer;
    }

    public void setStatusPlayer(boolean statusPlayer) {
        this.statusPlayer = statusPlayer;
    }
}

