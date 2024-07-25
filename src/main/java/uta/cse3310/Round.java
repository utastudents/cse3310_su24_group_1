package uta.cse3310;

import java.time.LocalDateTime;

public class Round {

    private int roundNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int score;
    private String status;

<<<<<<< HEAD
    // Constructor
=======
>>>>>>> new-branch
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.startTime = null;
        this.endTime = null;
        this.score = 0;
        this.status = "not started";
    }

<<<<<<< HEAD
    // Start the round
=======
    
>>>>>>> new-branch
    public void startRound() {
        this.startTime = LocalDateTime.now();
        this.status = "in progress";
    }

    // End the round
    public void endRound() {
        this.endTime = LocalDateTime.now();
<<<<<<< HEAD
        this.status = "finished";
        this.score = calculateScore();
=======
        this.status = "completed";
        this.score = calculateScore(); // Assuming score is calculated at the end of the round
>>>>>>> new-branch
    }

    // Calculate the score for the round (dummy implementation, adjust as needed)
    public int calculateScore() {
<<<<<<< HEAD
        // Example score calculation based on duration (seconds) - replace with actual logic
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).getSeconds();
=======
      
        if (this.startTime != null && this.endTime != null) {
            return (int) java.time.Duration.between(this.startTime, this.endTime).getSeconds();
>>>>>>> new-branch
        } else {
            return 0;
        }
    }

<<<<<<< HEAD
    // Getters and setters
    public int getRoundNumber() {
        return roundNumber;
=======
    public String getStatus() {
        return this.status;
    }

    public int getRoundNumber() {
        return this.roundNumber;
>>>>>>> new-branch
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public LocalDateTime getStartTime() {
<<<<<<< HEAD
        return startTime;
=======
        return this.startTime;
>>>>>>> new-branch
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
<<<<<<< HEAD
        return endTime;
=======
        return this.endTime;
>>>>>>> new-branch
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getScore() {
<<<<<<< HEAD
        return score;
=======
        return this.score;
>>>>>>> new-branch
    }

    public void setScore(int score) {
        this.score = score;
<<<<<<< HEAD
    }

    public String getStatus() {
        return status;
=======
>>>>>>> new-branch
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



