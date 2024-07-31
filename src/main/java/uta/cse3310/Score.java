package uta.cse3310;

public class Score {
    private int score;
    //private int playerId;

    
    public Score()
    {
    	this.score =0;
    }
    // gets the score of the player
    public int getScore() {
        
        return score;
    }

    // updates score of player with the corresponding id
    public void updateScore(int score) {
       
        this.score = this.score +score;
    }
}