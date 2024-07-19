package uta.cse3310;

import java.time.LocalDateTime;

public class Round {
  
    private int roundNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int score;
    private String status;

  
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.startTime = null;
        this.endTime = null;
        this.score = 0;
        this.status = "not started";
    }


    public void startRound() {
        
    }

    public void endRound() {

    }

    public int calculateScore() {
        return 0;
    }
    
    public int getRoundNumber() {
        return 0;
    }

    public void setRoundNumber(int roundNumber) {
       
    }

    public LocalDateTime getStartTime() {
        return null;
    }

    public void setStartTime(LocalDateTime startTime) {
    
    }

    public LocalDateTime getEndTime() {
        return null;
    }

    public void setEndTime(LocalDateTime endTime) {
      
    }

    public int getScore() {
        return 0;
    }

    public void setScore(int score) {
   
    }

    public String getStatus() {
        return null;
    }

    public void setStatus(String status) {
       
    }
}


