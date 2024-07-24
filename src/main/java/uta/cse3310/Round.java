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
        this.status = "completed";
        this.score = calculateScore(); // Assuming score is calculated at the end of the round
    }

    public int calculateScore() {
      
        if (this.startTime != null && this.endTime != null) {
            return (int) java.time.Duration.between(this.startTime, this.endTime).getSeconds();
        } else {
            return 0;
        }
    }

    public String getStatus() {
        return this.status;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


