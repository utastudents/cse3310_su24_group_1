package uta.cse3310;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
// Need to implement functions that choose random words from filtered_words.txt and also
// checks with user guesses (may be implemented here or in Gameplay.java if better choice)

// Also need to have a way the wheel occurs whenever a valid event happens for it to occur
// example: user guesses a correct character, then they get a wheel spin

public class Round {

    private int roundNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int score;
    private String status;
    private String currentWord;
    private Random random;
    public List<String> words;

    // Constructor
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.startTime = null;
        this.endTime = null;
        this.score = 0;
        this.status = "not started";
        this.random = new Random();
        loadWordsFromFile("cse3310_su24_group_1/src/filtered_words.txt");
    }

    // Load words from the file
    private void loadWordsFromFile(String filePath) {
        try {
            words = Files.lines(Paths.get(filePath))
                         .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            words = List.of(); // default to empty list if file read fails
        }
    }

    // Start the round
    public void startRound() {
        this.startTime = LocalDateTime.now();
        this.status = "in progress";
        this.startTime = LocalDateTime.now();
        this.status = "in progress";
        chooseRandomWord();
    }

    // Choose a random word from the list
    public void chooseRandomWord() {
        if (words != null && !words.isEmpty()) {
            currentWord = words.get(random.nextInt(words.size()));
            System.out.println("Chosen Word: " + currentWord); // For debugging purposes
        }
    }

    // Check user's guess
    public boolean checkGuess(String guess) {
        return guess.equalsIgnoreCase(currentWord);
    }

    public void endRound() {
        this.endTime = LocalDateTime.now();
        this.status = "completed";
        this.score = calculateScore(); // Assuming score is calculated at the end of the round
        this.status = "completed";
        this.score = calculateScore(); // Assuming score is calculated at the end of the round
    }

    public int calculateScore() {
        // Example score calculation based on duration (seconds) - replace with actual logic
        // Example score calculation based on duration (seconds) - replace with actual logic
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).getSeconds();
        } else {
            return 0;
        }
    }

    // Getters and setters
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


