package uta.cse3310;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Round {

    public int roundNumber;
    //public LocalDateTime startTime;
    //public LocalDateTime endTime;
    public int score;
    public String status;
    public String currentWord1;
    public String currentWord2;
    public String currentWord3;
    public List<Integer> index;
    public UserEvent userEvent;

    // Constructor
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        //this.startTime = null;
        //this.endTime = null;
        this.score = 0;
        this.status = "not started";
        this.currentWord1 = null;
        this.currentWord2 = null;
        this.currentWord3 = null;
        this.userEvent = new UserEvent();
    }

    public void setUserEvent(UserEvent UE) {
        userEvent = UE;
        //System.out.println(userEvent.userGuess);
        if(status.equals("in progress")) {
            checkChar();
        }
    }

    // Load words from the file
    private List<String> loadWordsFromFile(String filePath) {
        List<String> words = new ArrayList<String>();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                words = Files.lines(path).collect(Collectors.toList());
                System.out.println("Text file successfully retrieved");
            } else {
                System.err.println("File not found: " + filePath);
                //words = List.of(); // default to empty list if file not found
            }
        } catch (IOException e) {
            e.printStackTrace();
            //words = List.of(); // default to empty list if file read fails
        }
        return words;
    }

    // Start the round
    public void startRound() {
        //this.startTime = LocalDateTime.now();
        this.status = "in progress";
        chooseRandomWord();
    }

    // Choose a random word from the list
    public void chooseRandomWord() {
        this.currentWord1 = "apple"; // for testing purposes
        this.currentWord2 = "banana";
        this.currentWord3 = "cherries";
        /*List<String> words = loadWordsFromFile("src/filtered_words.txt");
        Random random = new Random();
        if (words != null && !words.isEmpty()) {
            int wordsToGuess = random.nextInt(3) + 1;
            if(wordsToGuess == 1) {
                this.currentWord1 = words.get(random.nextInt(words.size()));
                System.out.println("There is 1 word to guess: " + currentWord1);
            }
            else if(wordsToGuess == 2) {
                this.currentWord1 = words.get(random.nextInt(words.size()));
                this.currentWord2 = words.get(random.nextInt(words.size()));
                System.out.println("There are 2 words to guess: " + currentWord1 + ", " + currentWord2);
            }
            else if(wordsToGuess == 3) {
                this.currentWord1 = words.get(random.nextInt(words.size()));
                this.currentWord2 = words.get(random.nextInt(words.size()));
                this.currentWord3 = words.get(random.nextInt(words.size()));
                System.out.println("There are 3 words to guess: " + currentWord1 + ", " + currentWord2 + ", " + currentWord3);
            }
            System.out.println("Chosen Word 1: " + currentWord1); // For debugging purposes
            System.out.println("Chosen Word 2: " + currentWord2);
            System.out.println("Chosen Word 3: " + currentWord3);
            
        }*/
    }

    public void checkChar() {
        if(userEvent.userGuess.length() == 1 && userEvent.userGuess != null) {
            userEvent.charInput = userEvent.userGuess.charAt(0);
            System.out.println("Char: " + userEvent.charInput);
            if(currentWord1 != null) {
                this.index = new ArrayList<Integer>();
                for(int i = 0; i < currentWord1.length(); i++) {
                    if(userEvent.charInput == currentWord1.charAt(i)) {
                        index.add(i);
                    }
                }
                userEvent.validLetters1 = index;
            }

            if(currentWord2 != null) {
                this.index = new ArrayList<Integer>();
                for(int i = 0; i < currentWord2.length(); i++) {
                    if(userEvent.charInput == currentWord2.charAt(i)) {
                        index.add(i);
                    }
                }
                userEvent.validLetters2 = index;
            }

            if(currentWord3 != null) {
                this.index = new ArrayList<Integer>();
                for(int i = 0; i < currentWord3.length(); i++) {
                    if(userEvent.charInput == currentWord3.charAt(i)) {
                        index.add(i);
                    }
                }
                userEvent.validLetters3 = index;
            }
        }
        else if(userEvent.userGuess != null) {
            checkGuess();
        }
    }

    // Check user's guess
    public boolean checkGuess() {
        return userEvent.userGuess.equalsIgnoreCase(currentWord1);
    }

    // End the round
    public void endRound() {
        //this.endTime = LocalDateTime.now();
        this.status = "completed";
        //this.score = calculateScore(); // Assuming score is calculated at the end of the round
    }

    /*public int calculateScore() {
        // Example score calculation based on duration (seconds) - replace with actual logic
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).getSeconds();
        } else {
            return 0;
        }
    }*/

    // Getters and setters
    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    /*public LocalDateTime getStartTime() {
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
    }*/

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

    public String getCurrentWord1() {
        return currentWord1;
    }

    public String getCurrentWord2() {
        return currentWord2;
    }

    public String getCurrentWord3() {
        return currentWord3;
    }
}



