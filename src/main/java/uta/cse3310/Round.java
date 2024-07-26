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
        this.startTime = LocalDateTime.now();
        this.status = "in progress";
    }

   
    public void endRound() {
        this.endTime = LocalDateTime.now();
        this.status = "finished";
        this.score = calculateScore();
    }

   
    public int calculateScore() {
        
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).getSeconds();
        } else {
            return 0;
        }
    }

   
    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



