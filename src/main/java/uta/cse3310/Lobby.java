package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    public List<Player> players;
    private Boolean gameStatus;
    private int playerCount;
    private int lobbyId;
    private Leaderboard leaderboard;

    public Lobby() {
        this.players = new ArrayList<Player>();
        this.gameStatus = false;
        this.playerCount = 0;
        this.lobbyId = 0;
        this.leaderboard = new Leaderboard();
    }

    public Lobby(int lobbyId) {
        this.players = new ArrayList<Player>();
        this.gameStatus = false;
        this.playerCount = 0;
        this.lobbyId = lobbyId;
        this.leaderboard = new Leaderboard();
    }

    // allows for game to start once there are at least 2 players in lobby
    public void gameStart() {
        if(playerCount >= 2 && playerCount <= 4) {
            Gameplay G = new Gameplay(players);
            gameStatus = true;
        }
    }

    // shows in a list the current players in the lobby
    public void displayPlayers() {
        int playerNum = 1;
        for(Player p : players) {
            // print each name in command line
            System.out.println(playerNum + ". " + p.getPlayerName());
            playerNum++;
        }
    }

    // displays leaderboard on the side of lobby to show all players' scores from server
    public void displayLeaderboard() {
        int playerNum = 1;
        for(Player p : players) {
            // print each username and their respective score into command line
            System.out.println(playerNum + ". " + p.getPlayerName() + " Score: " + p.getPoints());
            playerNum++;
        }
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

    public void setPlayerCount() {
        playerCount = 0;
        for(Player p : players) {
            playerCount += 1;
        }
    }

    public Boolean getGameStatus() {
        return gameStatus;
    }
}