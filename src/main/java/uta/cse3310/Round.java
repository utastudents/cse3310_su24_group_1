package uta.cse3310;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Round {

    private int roundNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int score;
    private String status;
    private List<String> words;
    private String currentWord;
    private Random random;

    // Constructor
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.startTime = null;
        this.endTime = null;
        this.score = 0;
        this.status = "not started";
        this.random = new Random();
        loadWordsFromFile("src/filtered_words.txt");
    }

    // Load words from the file
    private void loadWordsFromFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                words = Files.lines(path).collect(Collectors.toList());
            } else {
                System.err.println("File not found: " + filePath);
                words = List.of(); // default to empty list if file not found
            }
        } catch (IOException e) {
            e.printStackTrace();
            words = List.of(); // default to empty list if file read fails
        }
    }

    // Start the round
    public void startRound() {
        this.startTime = LocalDateTime.now();
        this.status = "in progress";
        chooseRandomWord();
    }

    // Choose a random word from the list
    private void chooseRandomWord() {
        if (words != null && !words.isEmpty()) {
            currentWord = words.get(random.nextInt(words.size()));
            System.out.println("Chosen Word: " + currentWord); // For debugging purposes
        }
    }

    // Check user's guess
    public boolean checkGuess(String guess) {
        return guess.equalsIgnoreCase(currentWord);
    }

    // End the round
    public void endRound() {
        this.endTime = LocalDateTime.now();
        this.status = "completed";
        this.score = calculateScore(); // Assuming score is calculated at the end of the round
    }

    // Calculate score
    public int calculateScore() {
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).getSeconds();
        } else {
            return 0;
        }
    }

    // Getters and setters
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

    public String getCurrentWord() {
        return currentWord;
    }
}



