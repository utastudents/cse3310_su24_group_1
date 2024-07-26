package uta.cse3310;

import java.util.List;

public class Lobby {
    public List<Player> players;
    private Boolean gameStatus;
    private int playerCount;
    private int lobbyId;
    private Leaderboard leaderboard;

    public Lobby() {
        this.players = null;
        this.gameStatus = false;
        this.playerCount = 0;
        this.lobbyId = 0;
        this.leaderboard = new Leaderboard();
    }

    // allows for game to start once there are at least 2 players in lobby
    public void gameStart() {
        if(playerCount >= 2 && playerCount <= 4) {
            Gameplay G = new Gameplay();
        }
    }

    // shows in a list the current players in the lobby
    public void displayPlayers() {
        for(Player p : players) {
            // print each name in command line
            System.out.println(p.getPlayerName());
        }
    }

    // displays leaderboard on the side of lobby to show all players' scores from server
    public void displayLeaderboard() {
        
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public Boolean getGameStatus() {
        return gameStatus;
    }
}