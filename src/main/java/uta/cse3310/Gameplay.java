package uta.cse3310;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gameplay {

    public List<Player> players;
    public int currentPlayerIndex;
    public Wheel wheel;
    public Random random;
    public Scanner scanner;
    public Round round;
    public int roundNum;
    public UserEvent userEvent;

    public Gameplay(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.wheel = new Wheel();
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.roundNum = 1;
        this.userEvent = new UserEvent();
    }

    public void setUserEvent(UserEvent UE) {
        userEvent = UE;
        //System.out.println(userEvent.userGuess);
        if(round != null && round.getStatus().equals("in progress")) {
            round.setUserEvent(userEvent);
        }
    }

    public void playGame() {
        // Game loop for 3 rounds
        //for (int roundNum = 1; roundNum <= 3; roundNum++) {
            System.out.println("Round " + roundNum + " begins!");
            this.round = new Round(roundNum);
            
            round.startRound();
            round.setUserEvent(userEvent);
            // playRound();
        //}

        // Calculate the final scores and display the winner
        if(roundNum == 4) {
            Player winner = determineWinner();
            System.out.println("The winner is " + winner.getPlayerName() + " with " + winner.getPoints() + " points!");
        }
    }

    private void playRound() {
        // Each round continues until a condition is met (e.g., a puzzle is solved)
        boolean roundActive = true;
        while (roundActive) {
            Player currentPlayer = players.get(currentPlayerIndex);

            //round.startRound();

            // Simulate a player spinning the wheel
            String spinResult = wheel.getRandomItem();
            System.out.println(currentPlayer.getPlayerName() + " spins the wheel and lands on: " + spinResult);

            // Handle different spin results
            if (spinResult.equals("Bankrupt")) {
                currentPlayer.setPoints(0);
                System.out.println(currentPlayer.getPlayerName() + " went bankrupt and lost all points!");
            } else if (spinResult.equals("Free Spin")) {
                System.out.println(currentPlayer.getPlayerName() + " gets a free spin!");
                continue;
            } else {
                int points = Integer.parseInt(spinResult);
                currentPlayer.setPoints(currentPlayer.getPoints() + points);
                System.out.println(currentPlayer.getPlayerName() + " earns " + points + " points!");
            }

            // Prompt player to guess a letter or solve the puzzle
            System.out.print("Guess a letter or solve the puzzle (g for guess, s for solve): ");
            String choice = scanner.nextLine();

            if (choice.equals("g")) {
                System.out.print("Enter a letter: ");
                char guessedLetter = scanner.nextLine().charAt(0);
                // Assume checkGuess is a method to handle the guess and update the game state
                boolean correctGuess = checkGuess(guessedLetter);
                if (correctGuess) {
                    System.out.println("Correct guess!");
                } else {
                    System.out.println("Wrong guess.");
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Next player's turn
                }
            } else if (choice.equals("s")) {
                System.out.print("Enter your solution: ");
                String solution = scanner.nextLine();
                // Assume checkSolution is a method to handle the solution attempt
                boolean correctSolution = checkSolution(solution);
                if (correctSolution) {
                    System.out.println("Correct solution!");
                    roundActive = false; // End the round
                } else {
                    System.out.println("Wrong solution.");
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Next player's turn
                }
            }
        }
    }

    private boolean checkGuess(char guessedLetter) {
        // Implement the logic to check the guessed letter
        // Return true if the guess is correct, false otherwise
        return random.nextBoolean(); // Placeholder logic
    }

    private boolean checkSolution(String solution) {
        // Implement the logic to check the solution
        // Return true if the solution is correct, false otherwise
        return random.nextBoolean(); // Placeholder logic
    }

    private Player determineWinner() {
        Player winner = players.get(0);
        for (Player player : players) {
            if (player.getPoints() > winner.getPoints()) {
                winner = player;
            }
        }
        return winner;
    }

    public Round getRound() {
        return round;
    }
}
