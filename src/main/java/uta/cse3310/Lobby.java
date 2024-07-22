package uta.cse3310;

import java.util.List;

public class Lobby {
    List<Player> players;
    private int playerCount;
    private Leaderboard leaderboard;

    // allows for game to start once there are at least 2 players in lobby
    public void gameStart() {
        if(playerCount >= 2 && playerCount <= 4) {
            Gameplay g = new Gameplay();
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
}