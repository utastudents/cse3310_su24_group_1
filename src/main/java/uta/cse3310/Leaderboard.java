package uta.cse3310;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Leaderboard {
    private final int MAX_SIZE = 5; // Maximum number of top scores
    private PriorityQueue<PlayerScore> scores;

    public Leaderboard() {
        // Create a priority queue with a custom comparator to keep the top scores
        scores = new PriorityQueue<>(MAX_SIZE, new Comparator<PlayerScore>() {
            @Override
            public int compare(PlayerScore p1, PlayerScore p2) {
                // Compare scores in descending order
                return Integer.compare(p2.getScore(), p1.getScore());
            }
        });
    }

    // Adds a new score to the leaderboard
    public void addScore(String playerName, int score) {
        PlayerScore newScore = new PlayerScore(playerName, score);
        if (scores.size() < MAX_SIZE) {
            scores.add(newScore);
        } else if (scores.peek().getScore() < score) {
            scores.poll(); // Remove the lowest score
            scores.add(newScore);
        }
    }

    // Returns a list of top scores
    public List<PlayerScore> getTopScores() {
        return new ArrayList<>(scores);
    }

    // Inner class to represent a player's score
    public static class PlayerScore {
        private String playerName;
        private int score;

        public PlayerScore(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return playerName + ": " + score;
        }
    }
}

