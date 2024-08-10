package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    public List<Player> players;
    public Boolean gameStatus;
    public int playerCount;
    public int lobbyId;
    public Leaderboard leaderboard;
    public Gameplay gameplay;
    public UserEvent userEvent;

    public Lobby() {
        this.players = new ArrayList<Player>();
        this.gameStatus = false;
        this.playerCount = 0;
        this.lobbyId = 0;
        this.leaderboard = new Leaderboard();
        this.userEvent = new UserEvent();
    }

    public Lobby(int lobbyId) {
        this.players = new ArrayList<Player>();
        this.gameStatus = false;
        this.playerCount = 0;
        this.lobbyId = lobbyId;
        this.leaderboard = new Leaderboard();
        this.userEvent = new UserEvent();
    }

    public void setUserEvent(UserEvent UE) {
        userEvent = UE;
        if(gameplay != null && gameStatus == true) {
            gameplay.setUserEvent(userEvent);
        }
        else if(UE.status.equals("start")) {
            gameStart();
        }
        else if(UE.status.equals("name")) {
            for(Player p : players) {
                if(p.getPlayerID() == UE.playerId) {
                    p.setPlayerName(UE.playerName);
                }
            }
        }
    }

    // allows for game to start once there are at least 2 players in lobby
    public void gameStart() {
        if((playerCount >= 2 && playerCount <= 4) && userEvent.status.equals("start")) {
            this.gameStatus = true;
            gameplay = new Gameplay(players);
            gameplay.setUserEvent(userEvent);
            gameplay.playGame();
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

    public Gameplay getGameplay() {
        return gameplay;
    }
}